package com.plum.cas.service.impl;

import com.plum.cas.dao.*;
import com.plum.cas.dto.Authorization;
import com.plum.cas.entity.AuthorizationEntity;
import com.plum.cas.entity.UserEntity;
import com.plum.cas.service.AuthorizationService;
import com.plum.core.page.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private AppDao appDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ResourceDao resourceDao;

    /**
     * 根据appKey值和username查找对应的角色列表
     *[测试完成]
     * @param appKey
     * @param username
     * @return
     */
    public Set<String> findRoles(String appKey, String username) {

        Long appId = appDao.findAppIdByAppKey(appKey);

        UserEntity userEntity = userDao.findByPrincipal(username);
        Long userId = userEntity.getId();

        List<Long> roleIds = authorizationDao.findRoleIdsByAppUserId(appId, userId);

        return roleDao.findRoleNamesByIds(roleIds);

    }

    /**
     * 根据appKey值和username查找对应的权限列表
     * [测试完成]
     * @param appKey
     * @param username
     * @return
     */
    public Set<String> findPermissions(String appKey, String username) {
        /**1.获取appId*/
        Long appId = appDao.findAppIdByAppKey(appKey);
        UserEntity userEntity = userDao.findByPrincipal(username);
        /**2.获取userId*/
        Long userId = userEntity.getId();

        /**3.获取用户绑定的角色ID集合*/
        List<Long> roleIds = authorizationDao.findRoleIdsByAppUserId(appId, userId);

        /**4.获取角色对应的资源ID集合*/
        Set<Long> resourceIds = roleDao.findResourceIdsByRoleIds(roleIds);

        /**5.根据资源ID获取资源权限*/
        return resourceDao.findPermissionsByIds(resourceIds);

    }

    public Authorization create(Authorization cube) {
        return null;
    }

    public Authorization modify(Authorization cube) {
        return null;
    }

    public Authorization delete(Long aLong) {
        return null;
    }

    public Authorization findByIndex(Long aLong) {
        return findOne(aLong);
    }

    public Authorization findOne(Long aLong) {
        AuthorizationEntity authorizationEntity = (AuthorizationEntity) authorizationDao.findOne(aLong, AuthorizationEntity.class);

        Authorization authorization = new Authorization();
        BeanUtils.copyProperties(authorizationEntity, authorization);

        return authorization;
    }

    public List<Authorization> findAll() {
        List<AuthorizationEntity> authorizationEntityList = authorizationDao.findAll("AuthorizationEntity");

        List<Authorization> authorizations = new ArrayList<Authorization>();
        for(AuthorizationEntity authorizationEntity : authorizationEntityList){
            Authorization authorization = new Authorization();
            BeanUtils.copyProperties(authorizationEntity, authorization);
            authorization.setRoleIdsStr(authorizationEntity.getRoleIds());
            authorizations.add(authorization);
        }

        return authorizations;
    }

    public List<Authorization> findAllByPage(PageUtils page) {
        return null;
    }
}
