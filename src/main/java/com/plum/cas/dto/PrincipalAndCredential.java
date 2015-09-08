package com.plum.cas.dto;


public class PrincipalAndCredential {
    private ModPwdType type;
    private String principal;
    private String newCredential;
    private String credential;


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
