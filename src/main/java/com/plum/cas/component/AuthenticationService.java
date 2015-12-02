package com.plum.cas.component;

import org.apache.shiro.authc.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andy on 2015/11/13.
 */
public interface AuthenticationService {

    /**
     * log in action to validateToken a user.
     *
     * @param username
     * @param password
     */
    void authenticate(String username, String password, boolean rememberMe) throws AuthenticationException;

    /**
     * log out
     */
    void unauthenticate();

    /**
     *
     * @param httpServletRequest
     */
    void resolveIdentity(HttpServletRequest httpServletRequest);
}
