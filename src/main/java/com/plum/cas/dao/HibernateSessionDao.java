package com.plum.cas.dao;

import com.plum.core.dao.BaseDao;

import java.io.Serializable;


public interface HibernateSessionDao<T, PK extends Serializable> extends BaseDao<T, PK> {
}
