package com.plum.cas.utils;

import com.plum.cas.dto.User;
import com.plum.core.utils.JacksonJsonUtil;
import org.junit.Test;

/**
 * Created by G_dragon on 2015/7/24.
 */
public class JsonUtilsTest {

    @Test
    public void JsonUtilsMapperTest(){

        User user = new User();
       // user.setAliasName("Andy");

        String str = null;

        try {
            str = JacksonJsonUtil.beanToJson(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(str);
    }

}
