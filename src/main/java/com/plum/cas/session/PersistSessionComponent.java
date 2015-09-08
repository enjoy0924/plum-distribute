package com.plum.cas.session;

import com.plum.cas.entity.SessionEntity;
import com.plum.cas.service.HibernateSessionService;
import com.plum.cas.utils.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


public class PersistSessionComponent extends CachingSessionDAO {

    @Autowired
    HibernateSessionService sessionService;

    @Override
    protected void doUpdate(Session session) {
        //如果会话过期/停止 没必要再更新了
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId((String) session.getId());
        sessionEntity.setSession(SerializableUtils.serialize(session));

        sessionService.update(sessionEntity);
    }

    @Override
    protected void doDelete(Session session) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId((String) session.getId());

        sessionService.delete(sessionEntity);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId((String) session.getId());
        sessionEntity.setSession(SerializableUtils.serialize(session));

        sessionService.create(sessionEntity);

        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        SessionEntity sessionEntity = (SessionEntity) sessionService.findOne(sessionId, SessionEntity.class);

        if(null == sessionEntity)
            return null;
        return SerializableUtils.deserialize(sessionEntity.getSession());
    }
}
