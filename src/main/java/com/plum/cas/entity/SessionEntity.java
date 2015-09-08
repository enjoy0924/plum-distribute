package com.plum.cas.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="CAS_SESSIONS")
public class SessionEntity implements Serializable {
    private String id;
    private String session;

    @Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }
}
