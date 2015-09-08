package com.plum.cas.dao.impl;

import com.plum.cas.dao.HibernateSessionDao;
import com.plum.cas.entity.SessionEntity;
import com.plum.core.dao.AbstractBaseDao;
import org.springframework.stereotype.Repository;


@Repository
public class HibernateSessionDaoImpl extends AbstractBaseDao<SessionEntity, String>
        implements HibernateSessionDao<SessionEntity, String> {
}
