package com.plum.cas.dao.impl;

import com.plum.cas.dao.ResourceDao;
import com.plum.cas.entity.ResourceEntity;
import com.plum.core.dao.AbstractBaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ResourceDaoImpl extends AbstractBaseDao<ResourceEntity, Long>
        implements ResourceDao<ResourceEntity, Long> {

    private static final String QueryFindPermissionsByIds="from ResourceEntity where id in(:ids)";
    private static final String QueryFindResourcesByPermissions="from ResourceEntity where permission in(:permissions)";

    public Set<String> findByIds(Set<Long> resourceIds) {
        return null;
    }


    public Set<String> findPermissionsByIds(Set<Long> resourceIds) {

        Set<String> permissions= new HashSet<String>();
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen())
        {
            Query query = session.createQuery(QueryFindPermissionsByIds);
            query.setParameterList("ids", resourceIds);

            List<ResourceEntity> resourceEntityList = query.list();
            for(ResourceEntity resourceEntity: resourceEntityList){
                permissions.add(resourceEntity.getPermission());
            }
        }

        return permissions;
    }

    public List<ResourceEntity> findByPermissions(Set<String> permissions) {

        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen())
        {
            Query query = session.createQuery(QueryFindResourcesByPermissions);
            query.setParameterList("permissions", permissions);

            return query.list();
        }

        return null;
    }
}
