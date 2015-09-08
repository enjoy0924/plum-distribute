package com.plum.register.controller;

import com.plum.constant.CONST;
import com.plum.register.ValidateServiceFactory;
import com.plum.register.smsvalidate.SMSValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andy on 2015/8/27.
 */
@Controller
@RequestMapping("/validate")
public class validateController {

    @Autowired
    private SMSValidateService smsValidateService;

    /**
     * 进行短信验证码验证
     *
     * @param request
     * @param code
     * @return
     */
    @RequestMapping("/smscode")
    @ResponseBody
    public Object validateCode(HttpServletRequest request, String code){

        String smsValidateCodeRef = (String) request.getSession().getAttribute(CONST.KEY_VALIDATE_SMS_CODE);

        //无法进行验证
        if(null == smsValidateCodeRef || smsValidateCodeRef.isEmpty())
            return false;

        //验证
        if(smsValidateCodeRef.equalsIgnoreCase(code))
            return true;

        return false;
    }

    /**
     * 进行图片验证码验证
     *
     * @param request
     * @param code
     * @return
     */
    @RequestMapping("/imgcode")
    @ResponseBody
    public Object validateImgCode(HttpServletRequest request, String code){

        String imageValidateCodeRef = (String) request.getSession().getAttribute(CONST.KEY_VALIDATE_IMG_CODE);

        //无法进行验证
        if(null == imageValidateCodeRef || imageValidateCodeRef.isEmpty())
            return false;

        //验证
        if(imageValidateCodeRef.equalsIgnoreCase(code))
            return true;

        return false;
    }

    /**
     * 获取短信验证码
     *
     * @param request
     * @param phone
     * @return
     */
    @RequestMapping("/getsms")
    @ResponseBody
    public Object getSMSCode(HttpServletRequest request, String phone){

        String validateCode = ValidateServiceFactory.GenerateSMSValidateCode();

        request.getSession().setAttribute(CONST.KEY_VALIDATE_SMS_CODE, validateCode);

        return smsValidateService.SendSMSValidateCode(phone, validateCode);
    }


}
