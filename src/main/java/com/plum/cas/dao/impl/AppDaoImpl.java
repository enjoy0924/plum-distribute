package com.plum.cas.dao.impl;

import com.plum.cas.dao.AppDao;
import com.plum.cas.entity.AppEntity;
import com.plum.core.dao.AbstractBaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AppDaoImpl extends AbstractBaseDao<AppEntity, Long>
        implements AppDao<AppEntity, Long> {

    private static final String  QueryByAppKeyHQL = "from AppEntity app where app.appKey=:appKey";



    public Long findAppIdByAppKey(String appKey) {

        AppEntity appEntity = findAppByAppKey(appKey);
        if (null != appEntity)
            return appEntity.getId();
        return null;
    }

    public AppEntity findAppByAppKey(String appKey) {
        Session session = getSessionFactory().getCurrentSession();
        if (null != session && session.isOpen()) {
            Query query = session.createQuery(QueryByAppKeyHQL);
            query.setParameter("appKey", appKey);
            List<AppEntity> ls = query.list();
            if (null == ls || ls.isEmpty())
                return null;
            return ls.get(0);
        }
        return null;
    }
}
