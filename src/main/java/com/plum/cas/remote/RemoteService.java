package com.plum.cas.remote;

import com.plum.cas.service.AuthorizationService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 远程Session服务，这里的Session是持久化到数据库的，如果需要使用Redius的话，可以实现CacheSessionDao
 */
public class RemoteService implements RemoteServiceInterface {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private SessionDAO sessionDAO;

    public Session getSession(String appKey, Serializable sessionId) {
        return sessionDAO.readSession(sessionId);
    }

    public Serializable createSession(Session session) {
        return sessionDAO.create(session);
    }

    public void updateSession(String appKey, Session session) {
        sessionDAO.update(session);
    }

    public void deleteSession(String appKey, Session session) {
        sessionDAO.delete(session);
    }

    public PermissionContext getPermissions(String appKey, String username) {
        PermissionContext permissionContext = new PermissionContext();
        permissionContext.setRoles(authorizationService.findRoles(appKey, username));
        permissionContext.setPermissions(authorizationService.findPermissions(appKey, username));
        return permissionContext;
    }
}
