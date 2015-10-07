package com.plum.cas.service.impl;

import com.plum.cas.dao.HibernateSessionDao;
import com.plum.cas.entity.SessionEntity;
import com.plum.cas.service.HibernateSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Service
public class HibernateSessionServiceImpl implements HibernateSessionService {

    @Autowired
    private HibernateSessionDao hibernateSessionDao;

    public void update(SessionEntity sessionEntity) {

        SessionEntity sessionOld = (SessionEntity) hibernateSessionDao.findOne(sessionEntity.getId(), SessionEntity.class);

        if(null == sessionOld){
            return;
        }
        sessionOld.setSession(sessionEntity.getSession());

        hibernateSessionDao.update(sessionOld);
    }

    public void delete(SessionEntity sessionEntity) {
        hibernateSessionDao.delete(sessionEntity);
    }

    public void create(SessionEntity sessionEntity) {
        hibernateSessionDao.create(sessionEntity);
    }

    public SessionEntity findOne(Serializable sessionId, Class<SessionEntity> sessionEntityClass) {
        return (SessionEntity) hibernateSessionDao.findOne(sessionId, sessionEntityClass);
    }
}
