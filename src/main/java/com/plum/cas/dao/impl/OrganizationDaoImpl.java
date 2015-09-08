package com.plum.cas.dao.impl;

import com.plum.cas.dao.OrganizationDao;
import com.plum.cas.entity.OrganizationEntity;
import com.plum.core.dao.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrganizationDaoImpl extends AbstractBaseDao<OrganizationEntity, Long>
        implements OrganizationDao<OrganizationEntity, Long> {

    public List<OrganizationEntity> findAllWithExclude(OrganizationEntity excludeOraganization) {
        System.out.println("Unimplemented Method!");
        return null;
    }

    public void move(OrganizationEntity source, OrganizationEntity target) {
        System.out.println("Unimplemented Method!");
    }
}
