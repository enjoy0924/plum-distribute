package com.plum.cas.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CAS_APP")
public class AppEntity implements Serializable {
    private Long    id;
    private String  name;
    private String  appKey;
    private String  appSecret;
    private Boolean available = Boolean.FALSE;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppEntity app = (AppEntity) o;

        if (id != null ? !id.equals(app.id) : app.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AppEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appKey='" + appKey + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", available=" + available +
                '}';
    }
}
