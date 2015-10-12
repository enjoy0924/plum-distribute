package com.plum.cas.api;

/**
 * Created by Andy on 2015/10/11.
 */

import com.plum.cas.dto.IdentityDTO;
import com.plum.cas.dto.PrincipalAndCredential;
import com.plum.cas.service.AuthorizationService;
import com.plum.constant.CONST;
import com.plum.core.dto.CommonResultDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * 用户登录
     */
    @RequestMapping(value="/login", method= RequestMethod.POST)
    @ResponseBody
    public CommonResultDTO login(HttpServletRequest request,@RequestBody PrincipalAndCredential principalAndCredential){
        CommonResultDTO result = new CommonResultDTO();

        IdentityDTO identityDTO = new IdentityDTO();

        System.out.println("http sessionId: " + request.getSession().getId());

        //String resultPageURL = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";
        String username = principalAndCredential.getPrincipal();
        String password = principalAndCredential.getCredential();
        String submitCode = principalAndCredential.getVerifyCode();
        String appKey = principalAndCredential.getAppKey();
        if(null == appKey){
            result.getResult().setCode(0x0007);
            result.getResult().setMsg("no specified app key");
            return result;
        }
        //获取HttpSession中的验证码
        String verifyCode = (String)request.getSession().getAttribute(CONST.KEY_VALIDATE_IMG_CODE);

        //获取用户请求表单中输入的验证码
        //String submitCode = WebUtils.getCleanParam(request, CONST.KEY_VALIDATE_IMG_CODE);
        //System.out.println("用户[" + username + "]登录时输入的验证码为[" + submitCode + "],HttpSession中的验证码为[" + verifyCode + "]");

        if(null != verifyCode){
            if (StringUtils.isEmpty(submitCode) || !verifyCode.equalsIgnoreCase(submitCode)){
                //request.setAttribute("message_login", "验证码不正确");
                identityDTO.setIsAuthenticated(false);
                result.getResult().setCode(0x0001);
                result.getResult().setMsg("verify code fail");
                return result;
            }
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if(null != principalAndCredential.isRememberMe() && principalAndCredential.isRememberMe())
            token.setRememberMe(true);

        //System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();

        if(!currentUser.isAuthenticated()){
            try {
                currentUser.login(token);
            }catch(UnknownAccountException uae){
//                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
                result.getResult().setCode(0x0002);
                result.getResult().setMsg("unknown account");
//                request.setAttribute("message_login", "未知账户");
            }catch(IncorrectCredentialsException ice){
                result.getResult().setCode(0x0003);
                result.getResult().setMsg("incorrect credential");
//                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
//                request.setAttribute("message_login", "密码不正确");
            }catch(LockedAccountException lae){
                result.getResult().setCode(0x0004);
                result.getResult().setMsg("locked account");
//                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
//                request.setAttribute("message_login", "账户已锁定");
            }catch(ExcessiveAttemptsException eae){
                result.getResult().setCode(0x0005);
                result.getResult().setMsg("excessive attempts");
//                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
//                request.setAttribute("message_login", "用户名或密码错误次数过多");
            }catch(AuthenticationException ae){
                result.getResult().setCode(0x0006);
                result.getResult().setMsg("authentication exception");
                //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
//                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
                ae.printStackTrace();
//                request.setAttribute("message_login", "用户名或密码不正确");
            }
        }

        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            identityDTO.setRoles(authorizationService.findRoles(appKey , username));
            identityDTO.setPermissions(authorizationService.findPermissions(appKey, username));
            result.setAttach(identityDTO);
        }else{
            token.clear();
        }
        return result;
    }


    /**
     * 用户登出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityUtils.getSubject().logout();
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    }

}
