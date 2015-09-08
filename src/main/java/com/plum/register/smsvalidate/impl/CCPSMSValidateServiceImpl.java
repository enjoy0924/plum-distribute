package com.plum.register.smsvalidate.impl;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.plum.core.common.AbstractLifeBean;
import com.plum.register.smsvalidate.SMSValidateService;
import org.springframework.context.ApplicationEvent;

/**
 * Created by G_dragon on 2015/7/22.
 */
public class CCPSMSValidateServiceImpl extends AbstractLifeBean implements SMSValidateService {

    private CCPRestSDK restAPI = new CCPRestSDK();

    private String serverUrl;

    private String serverPort;

    private String sid;

    private String token;

    private String appId;

    private String templateId;

    private String expiredTime;

    private boolean isInitialized = false;


    private void initialize() {

        restAPI.init(serverUrl, serverPort);
        restAPI.setAccount(sid, token);
        restAPI.setAppId(appId);

        isInitialized = true;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public Object SendSMSValidateCode(String cellPhoneNo, String validateCode) {

        HashMap<String, Object> result = restAPI.sendTemplateSMS(cellPhoneNo, templateId ,new String[]{validateCode, expiredTime});

        System.out.println("SDKTestGetSubAccounts result=" + result);

        if("000000".equals(result.get("statusCode"))){
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            System.out.println("=" + result.get("statusCode") +" = "+result.get("statusMsg"));
        }

        return result;

    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public void setIsInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }

    @Override
    protected void onBootstrap(ApplicationEvent event) {
        initialize();
    }

    @Override
    protected void onShutdown(ApplicationEvent event) {

    }
}
