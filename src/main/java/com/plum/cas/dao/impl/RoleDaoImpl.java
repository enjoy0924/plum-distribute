package com.plum.cas.dao.impl;

import com.plum.cas.dao.RoleDao;
import com.plum.cas.entity.RoleEntity;
import com.plum.core.utils.ConvertUtil;
import com.plum.core.dao.AbstractBaseDao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDaoImpl extends AbstractBaseDao<RoleEntity, Long>
        implements RoleDao<RoleEntity, Long> {

    private static final String QueryRolesByIds = "from RoleEntity where id in(:ids)";

    private List<RoleEntity> findRoleEntityByIds(List<Long> roleIds){
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()){
            Query query = session.createQuery(QueryRolesByIds);
            query.setParameterList("ids", roleIds);
            return (List<RoleEntity>)query.list();
        }

        return null;
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
