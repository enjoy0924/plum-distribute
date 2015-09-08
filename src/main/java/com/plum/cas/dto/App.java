package com.plum.cas.dto;


public class App {
    private Long    id;
    private String  name;
    private String  appKey;
    private String  appSecret;
    private Boolean available;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
