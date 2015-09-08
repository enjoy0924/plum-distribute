package com.plum.cas.dao.impl;

import com.plum.cas.dao.RoleDao;
import com.plum.cas.entity.RoleEntity;
import com.plum.core.utils.ConvertUtil;
import com.plum.core.dao.AbstractBaseDao;

import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class RoleDaoImpl extends AbstractBaseDao<RoleEntity, Long>
        implements RoleDao<RoleEntity, Long> {

    private List<RoleEntity> findRoleEntityByIds(List<Long> roleIds){

        String QueryRolesByIdsHQL = "from RoleEntity where id in(:ids)";
        Map<String, Object> params = new HashMap<>();
        params.put("ids", roleIds);

        return findMultiByParameters(QueryRolesByIdsHQL, params, false);
    }

    public Set<Long> findRolesByIds(List<Long> roleIds) {

        return null;
    }

    public Set<String> findRoleNamesByIds(List<Long> roleIds) {
        Set<String> roleNames = new HashSet<String>();

        List<RoleEntity> roleEntities = findRoleEntityByIds(roleIds);
        if (null == roleEntities || roleEntities.isEmpty())
            return roleNames;

        for(RoleEntity roleEntity : roleEntities){
            roleNames.add(roleEntity.getRole());
        }

        return roleNames;
    }

    public Set<Long> findResourceIdsByRoleIds(List<Long> roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();

        List<RoleEntity> roleEntities = findRoleEntityByIds(roleIds);
        if (null == roleEntities || roleEntities.isEmpty())
            return resourceIds;

        for(RoleEntity roleEntity : roleEntities){

            List<Long> ls = ConvertUtil.stringConvert2LongList(roleEntity.getResourceIds(), ",");
            for(Long resourceId : ls)
                resourceIds.add(resourceId);
        }

        return resourceIds;
    }
}
