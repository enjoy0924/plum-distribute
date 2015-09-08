package com.plum.cas.dao;

import com.plum.cas.entity.OrganizationEntity;
import com.plum.core.dao.BaseDao;

import java.io.Serializable;
import java.util.List;

public interface OrganizationDao<T, PK extends Serializable> extends BaseDao<T, PK> {

    List<OrganizationEntity> findAllWithExclude(OrganizationEntity excludeOraganization);

    void move(OrganizationEntity source, OrganizationEntity target);
}
