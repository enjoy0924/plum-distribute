package com.plum.cas.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * 登录之后，返回给前端的用户信息
 *
 * Created by Andy on 2015/10/11.
 */
public class IdentityDTO {
    boolean isAuthenticated = false;
    Set<String> roles;
    Set<String> permissions;

    public Set<String> getRoles() {
        if(null == roles){
            roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        if(null == permissions){
            permissions = new HashSet<>();
        }
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}
