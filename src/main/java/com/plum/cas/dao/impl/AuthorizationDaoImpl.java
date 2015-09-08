package com.plum.cas.dao.impl;

import com.plum.cas.dao.AuthorizationDao;
import com.plum.cas.entity.AuthorizationEntity;
import com.plum.core.utils.ConvertUtil;
import com.plum.core.dao.AbstractBaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorizationDaoImpl extends AbstractBaseDao<AuthorizationEntity, Long>
        implements AuthorizationDao<AuthorizationEntity, Long>  {

    private static final String QueryRoleIdsByAppUserId = "from AuthorizationEntity where appId=:appId and userId=:userId";

    public AuthorizationEntity findByAppUser(Long appId, Long userId) {
        System.out.println("Unimplemented Method!");
        return null;
    }

    public List<Long> findRoleIdsByAppUserId(Long appId, Long userId) {

        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen())
        {
            Query query = session.createQuery(QueryRoleIdsByAppUserId);
            query.setParameter("appId", appId);
            query.setParameter("userId", userId);

            List<AuthorizationEntity> authorizationEntities = query.list();

            String roleIds = authorizationEntities.get(0).getRoleIds();

            return ConvertUtil.stringConvert2LongList(roleIds, ",");

        }

        return null;
    }
}
