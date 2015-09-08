package com.plum.cas.service.impl;

import com.plum.cas.dao.ResourceDao;
import com.plum.cas.dto.Resource;
import com.plum.cas.entity.ResourceEntity;
import com.plum.cas.service.ResourceService;
import com.plum.core.filter.PageSortFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    /**
     * 根据资源的ID获取权限[名称]列表
     *
     * @param resourceIds
     * @return
     */
    public Set<String> findPermissions(Set<Long> resourceIds) {

        return resourceDao.findPermissionsByIds(resourceIds);

    }

    /**
     * 根据权限[名称]列表，查找所属的资源列表
     *
     * @param permissions
     * @return
     */
    public List<Resource> findMenus(Set<String> permissions) {

        List<ResourceEntity> resourceEntities = resourceDao.findByPermissions(permissions);

        if (null == resourceEntities || resourceEntities.isEmpty())
            return null;

        List<Resource> resourceList = new ArrayList<Resource>();
        for (ResourceEntity resourceEntity : resourceEntities)
        {
            Resource resource = new Resource();
            BeanUtils.copyProperties(resourceEntity, resource);
            resourceList.add(resource);
        }
        return resourceList;
    }

    public Resource create(Resource cube) {
        return null;
    }

    public Resource modify(Resource cube) {
        return null;
    }

    public Resource delete(Long aLong) {
        return null;
    }

    public Resource findByIndex(Long aLong) {
        return findOne(aLong);
    }

    public Resource findOne(Long aLong) {
        ResourceEntity resourceEntity = (ResourceEntity) resourceDao.findOne(aLong, ResourceEntity.class);
        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceEntity, resource);

        return resource;
    }

    public List<Resource> findAll() {
        List<ResourceEntity> userEntityList = resourceDao.findAll("ResourceEntity");

        List<Resource> resources = new ArrayList<Resource>();
        for(ResourceEntity userEntity : userEntityList){
            Resource resource = new Resource();
            BeanUtils.copyProperties(userEntity, resource);
            resources.add(resource);
        }

        return resources;
    }

    public List<Resource> findAllByPage(PageSortFilter page) {
        return null;
    }
}
