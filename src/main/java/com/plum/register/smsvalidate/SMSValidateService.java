package com.plum.register.smsvalidate;

/**
 * Created by G_dragon on 2015/7/22.
 */
public interface SMSValidateService {

    /**
     * 是否已经初始化
     *
     * @return
     */
    boolean isInitialized();

    /**
     * 发送 SMS 短信
     * @param cellPhoneNo
     * @param validateCode
     * @return
     */
    Object SendSMSValidateCode(String cellPhoneNo, String validateCode);

}
