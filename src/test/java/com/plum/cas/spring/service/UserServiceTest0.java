package com.plum.cas.spring.service;

import com.plum.cas.dto.User;
import com.plum.cas.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by G_dragon on 2015/7/29.
 */
public class UserServiceTest0 extends AbstractTestBaseService {

    @Autowired
    private UserService userService;

    @Test
    public void findUserTest(){
        User user = userService.findByPrincipal("andy");
    }

}
