package com.plum.cas.service;

import com.plum.cas.entity.SessionEntity;

import java.io.Serializable;


public interface HibernateSessionService {

    void update(SessionEntity sessionEntity);

    void delete(SessionEntity sessionEntity);

    void create(SessionEntity sessionEntity);

    SessionEntity findOne(Serializable sessionId, Class<SessionEntity> sessionEntityClass);
}
