package com.plum.cas.dao;

import com.plum.cas.entity.AuthorizationEntity;
import com.plum.core.dao.BaseDao;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public interface AuthorizationDao<T, PK extends Serializable> extends BaseDao<T, PK> {

    public AuthorizationEntity findByAppUser(Long appId, Long userId);

    List<Long> findRoleIdsByAppUserId(Long appId, Long userId);
}
