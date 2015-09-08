package com.plum.cas.service.impl;

import com.plum.cas.dao.OrganizationDao;
import com.plum.cas.dto.Organization;
import com.plum.cas.entity.OrganizationEntity;
import com.plum.cas.service.OrganizationService;
import com.plum.core.page.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    public Object findAllWithExclude(Organization excludeOraganization) {

        return null;
    }

    public void move(Organization source, Organization target) {

    }

    public Organization create(Organization cube) {
        return null;
    }

    public Organization modify(Organization cube) {
        return null;
    }

    public Organization delete(Long aLong) {
        return null;
    }

    public Organization findByIndex(Long aLong) {

        return findOne(aLong);
    }

    public Organization findOne(Long aLong) {

        OrganizationEntity organizationEntity = (OrganizationEntity) organizationDao.findOne(aLong, OrganizationEntity.class);

        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationEntity, organization);

        return organization;
    }

    public List<Organization> findAll() {
        List<OrganizationEntity> organizationEntityList = organizationDao.findAll("OrganizationEntity");

        List<Organization> organizations = new ArrayList<Organization>();
        for(OrganizationEntity organizationEntity : organizationEntityList){
            Organization organization = new Organization();
            BeanUtils.copyProperties(organizationEntity, organization);
            organizations.add(organization);
        }

        return organizations;
    }

    public List<Organization> findAllByPage(PageUtils page) {
        return null;
    }
}
