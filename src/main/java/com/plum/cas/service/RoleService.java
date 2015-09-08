package com.plum.cas.service;

import com.plum.cas.dto.Role;
import com.plum.core.service.BaseService;
import java.util.Set;

public interface RoleService extends BaseService<Role, Long> {

    Set<String> findRoles(Long[] roleIds);

    Set<String> findPermissions(Long[] roleIds);
}
