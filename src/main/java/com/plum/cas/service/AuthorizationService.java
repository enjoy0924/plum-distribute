package com.plum.cas.service;

import com.plum.cas.dto.Authorization;
import com.plum.core.service.BaseService;
import java.util.Set;


public interface AuthorizationService extends BaseService<Authorization, Long> {

    /**
     * 根据AppKey和用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String appKey, String username);

    /**
     * 根据AppKey和用户名查找权限字符串
     * @param username
     * @return
     */
    public Set<String> findPermissions(String appKey, String username);


}
