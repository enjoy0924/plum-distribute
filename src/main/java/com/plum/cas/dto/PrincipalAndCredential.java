package com.plum.cas.dto;


public class PrincipalAndCredential {
    private String principal;    //ID, username/phone/email
    private String credential;   //Credential, password/
    private Boolean rememberMe;  //Remember Me?

    private String verifyCode;   //verify code, maybe it's not necessary
    private String newCredential;
    private ModPwdType type;
    private String appKey;

    public Boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }


    public enum ModPwdType {
        LOGIN,
        FORGETPWD
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public ModPwdType getType() {
        return type;
    }
    public void setType(ModPwdType type) {
        this.type = type;
    }

    public String getNewCredential() {
        return newCredential;
    }
    public void setNewCredential(String newCredential) {
        this.newCredential = newCredential;
    }
}
