package com.plum.register;


import com.plum.register.smsvalidate.SMSValidateService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by G_dragon on 2015/7/22.
 */
public class ValidateServiceFactory {

	private static String[] validateCodes = { "1","2", "3", "4", "5", "6", "7","8", "9", "0" };

    public static String GenerateSMSValidateCode(){
        List list = Arrays.asList(validateCodes); //
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 9);
        return result;
    }
}
