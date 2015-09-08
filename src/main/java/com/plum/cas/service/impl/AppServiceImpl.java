package com.plum.cas.service.impl;

import com.plum.cas.dao.AppDao;
import com.plum.cas.dto.App;
import com.plum.cas.entity.AppEntity;
import com.plum.cas.service.AppService;
import com.plum.core.queryfilter.PageSortFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;

    /**
     * 根据AppKey获取AppId
     *
     * @param appKey
     * @return
     */
    public Long findAppIdByAppKey(String appKey) {

        AppEntity appEntity = (AppEntity) appDao.findAppByAppKey(appKey);

        if (null==appEntity)
            return null;

        return appEntity.getId();

    }

    public App create(App cube) {

        AppEntity appEntity = new AppEntity();
        BeanUtils.copyProperties(cube, appEntity);

        appDao.create(appEntity);

        return null;
    }

    public App modify(App cube) {
        return null;
    }

    public App delete(Long aLong) {
        return null;
    }

    public App findByIndex(Long aLong) {
        return findOne(aLong);
    }

    public App findOne(Long aLong) {

        AppEntity appEntity = (AppEntity) appDao.findOne(aLong, AppEntity.class);
        App app = new App();
        BeanUtils.copyProperties(appEntity, app);

        return app;

    }

    public List<App> findAll() {

        List<AppEntity> appEntities = appDao.findAll("AppEntity");
        if(null == appEntities || appEntities.isEmpty())
            return null;

        List<App> apps = new ArrayList<App>();
        for(AppEntity appEntity : appEntities){
            App app = new App();
            BeanUtils.copyProperties(appEntity, app);
            apps.add(app);
        }

        return apps;
    }

    public List<App> findAllByPage(PageSortFilter page) {
        return null;
    }
}
