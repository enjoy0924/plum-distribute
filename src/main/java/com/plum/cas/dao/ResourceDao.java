package com.plum.cas.dao;

import com.plum.cas.entity.ResourceEntity;
import com.plum.core.dao.BaseDao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


public interface ResourceDao<T, PK extends Serializable> extends BaseDao<T, PK> {

    Set<String> findPermissionsByIds(Set<Long> resourceIds);

    List<ResourceEntity> findByPermissions(Set<String> permissions);
}
