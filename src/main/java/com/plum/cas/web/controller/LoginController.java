package com.plum.cas.web.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "1.不存在该用户";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "2.用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "3.其他错误：" + exceptionClassName;
        }

        System.out.println(error);

        model.addAttribute("error", error);
        return "login";
    }


}
