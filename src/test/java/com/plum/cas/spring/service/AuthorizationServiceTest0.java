package com.plum.cas.spring.service;

import com.plum.cas.service.AuthorizationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by G_dragon on 2015/7/30.
 */
public class AuthorizationServiceTest0 extends AbstractTestBaseService {

    @Autowired
    private AuthorizationService authorizationService;

    @Test
    public void testFindPermissionsByAppKeyAndUsername(){

        String username="admin";
        String appKey = "645ba612-370a-43a8-a8e0-993e7a590cf0";

        Set<String> permissions = authorizationService.findPermissions(appKey, username);

        for(String permission: permissions){
            System.out.println(permission);
        }

    }

    @Test
    public void testFindRolesByAppKeyAndUsername(){

        String username="admin";
        String appKey = "645ba612-370a-43a8-a8e0-993e7a590cf0";

        Set<String> roles = authorizationService.findRoles(appKey, username);

        for(String role: roles){
            System.out.println(role);
        }

    }

}
