package com.plum.cas.dto;


public class User {

    private Long    id;
    private Long    organizationId;
    private String  username;
    private String  phone;
    private String  email;
    private String  password;
    private String  salt;
    private Boolean locked = Boolean.FALSE;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

//    public String getLinkedPhone() {
//        return linkedPhone;
//    }
//
//    public void setLinkedPhone(String linkedPhone) {
//        this.linkedPhone = linkedPhone;
//    }

//    public String getAliasName() {
//        return aliasName;
//    }
//
//    public void setAliasName(String aliasName) {
//        this.aliasName = aliasName;
//    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
