package com.plum.cas.web.shiro.filter;

import com.plum.constant.CONST;
import com.plum.cas.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(CONST.CURRENT_USER, userService.findByPrincipal(username));
        return true;
    }
}
