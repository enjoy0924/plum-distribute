package com.plum.core.dto;

/**
 * 这个类里面包含了所有的注册和登录相关的信息
 *
 * Created by Andy on 2015/10/7.
 */
public class AuthValidateDTO {
    private String cellphone;
    private String email;
    private String username;
    private String password;

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
