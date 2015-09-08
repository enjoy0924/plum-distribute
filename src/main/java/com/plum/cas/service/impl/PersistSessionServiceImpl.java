package com.plum.cas.service.impl;

import com.plum.cas.dao.HibernateSessionDao;
import com.plum.cas.entity.SessionEntity;
import com.plum.cas.utils.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
public class PersistSessionServiceImpl extends CachingSessionDAO {

    @Autowired
    HibernateSessionDao sessionDao;

    @Override
    protected void doUpdate(Session session) {
        //如果会话过期/停止 没必要再更新了
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId((String) session.getId());
        sessionEntity.setSession(SerializableUtils.serialize(session));

        sessionDao.update(sessionEntity);
    }

    @Override
    protected void doDelete(Session session) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId((String) session.getId());

        sessionDao.delete(sessionEntity);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId((String) session.getId());
        sessionEntity.setSession(SerializableUtils.serialize(session));

        sessionDao.create(sessionEntity);

        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        SessionEntity sessionEntity = (SessionEntity) sessionDao.findOne(sessionId, SessionEntity.class);

        if(null == sessionEntity)
            return null;
        return SerializableUtils.deserialize(sessionEntity.getSession());
    }
}
