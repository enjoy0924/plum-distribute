package com.plum.cas.dao;

import com.plum.cas.entity.UserEntity;
import com.plum.core.dao.BaseDao;

import java.io.Serializable;


public interface UserDao<T, PK extends Serializable> extends BaseDao<T, PK> {

    UserEntity findByPrincipal(String principal);

}
