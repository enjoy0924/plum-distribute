package com.plum.cas.dao.impl;

import com.plum.cas.dao.ResourceDao;
import com.plum.cas.entity.ResourceEntity;
import com.plum.core.dao.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ResourceDaoImpl extends AbstractBaseDao<ResourceEntity, Long>
        implements ResourceDao<ResourceEntity, Long> {


    public Set<String> findByIds(Set<Long> resourceIds) {
        return null;
    }


    public Set<String> findPermissionsByIds(Set<Long> resourceIds) {
        String QueryFindPermissionsByIdsHQL="from ResourceEntity where id in(:ids)";
        Map<String, Object> params = new HashMap<>();
        params.put("ids", resourceIds);

        Set<String> permissions= new HashSet<>();
        List<ResourceEntity> resourceEntityList = findMultiByParameters(QueryFindPermissionsByIdsHQL, params, false);
        if(null == resourceEntityList || resourceEntityList.isEmpty())
            return permissions;

        for(ResourceEntity resourceEntity: resourceEntityList){
            permissions.add(resourceEntity.getPermission());
        }
        return permissions;
    }

    public List<ResourceEntity> findByPermissions(Set<String> permissions) {

        String QueryFindResourcesByPermissionsHQL="from ResourceEntity where permission in(:permissions)";
        Map<String, Object> params = new HashMap<>();
        params.put("permissions", permissions);

        return findMultiByParameters(QueryFindResourcesByPermissionsHQL, params, false);
    }
}
