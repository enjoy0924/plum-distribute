package com.plum.cas.component;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andy on 2015/11/13.
 */
@Component
public class DefaultAuthenticationService implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthenticationService.class);

    @Override
    public void authenticate(String username, String password, boolean rememberMe) throws AuthenticationException {

        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            token.setRememberMe(rememberMe);
            }catch(UnknownAccountException uae){
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
//                result.getResult().setCode(0x0002);
//                result.getResult().setMsg("unknown account");
//                request.setAttribute("message_login", "未知账户");
            }catch(IncorrectCredentialsException ice){
//                result.getResult().setCode(0x0003);
//                result.getResult().setMsg("incorrect credential");
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
//                request.setAttribute("message_login", "密码不正确");
            }catch(LockedAccountException lae){
//                result.getResult().setCode(0x0004);
//                result.getResult().setMsg("locked account");
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
//                request.setAttribute("message_login", "账户已锁定");
            }catch(ExcessiveAttemptsException eae){
//                result.getResult().setCode(0x0005);
//                result.getResult().setMsg("excessive attempts");
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
//                request.setAttribute("message_login", "用户名或密码错误次数过多");
            }catch(AuthenticationException ae){
//                result.getResult().setCode(0x0006);
//                result.getResult().setMsg("authentication exception");
                //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
                ae.printStackTrace();
//                request.setAttribute("message_login", "用户名或密码不正确");
            }
    }

    @Override
    public void unauthenticate() {

        Subject currentSubject = SecurityUtils.getSubject();

        if (currentSubject.isAuthenticated() || currentSubject.isRemembered()) {
            logger.info(String.format("User [%s] is logging out from the app.", currentSubject.getPrincipal()));
            currentSubject.logout();
        }
    }

    @Override
    public void resolveIdentity(HttpServletRequest httpServletRequest) {

//        Assert.notNull(httpServletRequest);
//
//        Subject currentUser = SecurityUtils.getSubject();
//
//        if (currentUser.isAuthenticated()) {
//            if (logger.isInfoEnabled()) {
//                logger.info("Logging out user {}", currentUser.getPrincipal().toString());
//            }
//            currentUser.logout();
//        }
//
//        AuthenticationToken httpRequestAuthcToken = new HttpRequestAuthenticationToken(httpServletRequest);
//
//        try {
//            currentUser.login(httpRequestAuthcToken);
//        } catch (Exception e) {
//            throw new AuthenticationException("Invalid SSO request.", e);
//        }
    }
}
