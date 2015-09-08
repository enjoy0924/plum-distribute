package com.plum.cas.spring.service;

import com.plum.register.ValidateServiceFactory;
import com.plum.register.smsvalidate.SMSValidateService;
import com.plum.register.smsvalidate.impl.CCPSMSValidateServiceImpl;
import org.junit.Test;

/**
 * Created by Andy on 2015/8/20.
 */
public class SMSValidateTest0 extends AbstractTestBaseService {

    @Test
    public void SMSValidateTest(){

        SMSValidateService smsValidateService = applicationContext.getBean(CCPSMSValidateServiceImpl.class);

        String validateCode = ValidateServiceFactory.GenerateSMSValidateCode();

        smsValidateService.SendSMSValidateCode("18983188515", validateCode);
    }
}
