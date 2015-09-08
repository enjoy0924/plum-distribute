package com.plum.cas.dao.impl;

import com.plum.cas.dao.HibernateSessionDao;
import com.plum.cas.entity.SessionEntity;
import com.plum.core.dao.AbstractBaseDao;

/**
 * Created by G_dragon on 2015/7/25.
 */
public class SessionDaoImpl extends AbstractBaseDao<SessionEntity, String>
        implements HibernateSessionDao<SessionEntity, String> {
}
