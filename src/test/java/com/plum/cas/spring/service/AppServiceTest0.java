package com.plum.cas.spring.service;

import com.plum.cas.service.AppService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by G_dragon on 2015/7/30.
 */
public class AppServiceTest0 extends AbstractTestBaseService {

    @Autowired
    private AppService appService;

    @Test
    public void testAppService(){
        String appKey = "645ba612-370a-43a8-a8e0-993e7a590cf0";
        Long appId = appService.findAppIdByAppKey(appKey);
        System.out.println(appId);
    }


}
