package com.plum.cas.dao;

import com.plum.core.dao.BaseDao;

import java.io.Serializable;

/**
 *
 */
public interface AppDao<T, PK extends Serializable> extends BaseDao<T, PK> {

    Long findAppIdByAppKey(String appKey);

    T findAppByAppKey(String appKey);
}
