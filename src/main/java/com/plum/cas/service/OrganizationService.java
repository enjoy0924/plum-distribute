package com.plum.cas.service;

import com.plum.cas.dto.Organization;
import com.plum.core.service.BaseService;


public interface OrganizationService extends BaseService<Organization, Long> {

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
