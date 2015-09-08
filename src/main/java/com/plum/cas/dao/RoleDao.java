package com.plum.cas.dao;

import com.plum.core.dao.BaseDao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface RoleDao<T, PK extends Serializable> extends BaseDao<T, PK> {

    Set<Long> findRolesByIds(List<Long> roleIds);

    Set<String> findRoleNamesByIds(List<Long> roleIds);

    Set<Long> findResourceIdsByRoleIds(List<Long> roleIds);

}
