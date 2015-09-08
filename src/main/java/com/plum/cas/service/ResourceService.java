package com.plum.cas.service;

import com.plum.cas.dto.Resource;
import com.plum.core.service.BaseService;

import java.util.List;
import java.util.Set;


public interface ResourceService extends BaseService<Resource, Long> {


    Set<String> findPermissions(Set<Long> resourceIds);

    List<Resource> findMenus(Set<String> permissions);
}
