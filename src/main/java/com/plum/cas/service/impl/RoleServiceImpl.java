package com.plum.cas.service.impl;

import com.plum.cas.dao.ResourceDao;
import com.plum.cas.dao.RoleDao;
import com.plum.cas.dto.Role;
import com.plum.cas.entity.RoleEntity;
import com.plum.cas.service.RoleService;
import com.plum.core.queryfilter.PageSortFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ResourceDao resourceDao;

    /**
     * 根据角色ID集合查找对应的角色名称集合
     *
     * @param roleIds
     * @return
     */
    public Set<String> findRoles(Long[] roleIds) {

        return roleDao.findRolesByIds(Arrays.asList(roleIds));

    }

    /**
     * 根据角色ID集合查找角色对应的权限[名称]集合
     *
     * @param roleIds
     * @return
     */
    public Set<String> findPermissions(Long[] roleIds) {
        /** 通过角色的ID集合，查找对应的资源集合 */
        Set<Long> resourceIds =  roleDao.findResourceIdsByRoleIds(Arrays.asList(roleIds));

        return resourceDao.findPermissionsByIds(resourceIds);

    }

    public Role create(Role cube) {

        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(cube, roleEntity);

        roleDao.create(roleEntity);

        return null;
    }

    public Role modify(Role cube) {
        return null;
    }

    public Role delete(Long aLong) {
        return null;
    }

    public Role findByIndex(Long aLong) {

        return findOne(aLong);
    }

    public Role findOne(Long aLong) {
        RoleEntity roleEntity = (RoleEntity) roleDao.findOne(aLong, RoleEntity.class);
        Role role = new Role();
        BeanUtils.copyProperties(roleEntity, role);

        return role;
    }

    public List<Role> findAll() {
        List<RoleEntity> userEntityList = roleDao.findAll("RoleEntity");

        List<Role> roles = new ArrayList<Role>();
        for(RoleEntity userEntity : userEntityList){
            Role role = new Role();
            BeanUtils.copyProperties(userEntity, role);
            roles.add(role);
        }

        return roles;
    }

    public List<Role> findAllByPage(PageSortFilter page) {
        return null;
    }
}
