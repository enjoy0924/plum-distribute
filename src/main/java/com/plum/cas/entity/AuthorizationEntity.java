package com.plum.cas.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CAS_USER_APP_ROLES")
public class AuthorizationEntity implements Serializable {
    private Long id;
    private Long userId;
    private Long appId;
    private String roleIds;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name="app_id")
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Column(name="role_ids")
    public String getRoleIds() {
        return roleIds;
    }
    public void setRoleIds(String roleIds){
        this.roleIds = roleIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorizationEntity that = (AuthorizationEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AuthorizationEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", appId=" + appId +
                ", roleIds=" + roleIds +
                '}';
    }
}
