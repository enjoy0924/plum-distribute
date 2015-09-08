package com.plum.cas.dao.impl;

import com.plum.cas.dao.AppDao;
import com.plum.cas.entity.AppEntity;
import com.plum.core.dao.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class AppDaoImpl extends AbstractBaseDao<AppEntity, Long>
        implements AppDao<AppEntity, Long> {

    public Long findAppIdByAppKey(String appKey) {

        AppEntity appEntity = findAppByAppKey(appKey);
        if (null != appEntity)
            return appEntity.getId();
        return null;
    }

    public AppEntity findAppByAppKey(String appKey) {
        String  QueryByAppKeyHQL = "from AppEntity app where app.appKey=:appKey";
        Map<String, Object> params = new HashMap<>();
        params.put("appKey", appKey);

        return findSingleByParameters(QueryByAppKeyHQL, params, false);

    }
}
