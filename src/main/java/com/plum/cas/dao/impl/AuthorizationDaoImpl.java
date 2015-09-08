package com.plum.cas.dao.impl;

import com.plum.cas.dao.AuthorizationDao;
import com.plum.cas.entity.AuthorizationEntity;
import com.plum.core.utils.ConvertUtil;
import com.plum.core.dao.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorizationDaoImpl extends AbstractBaseDao<AuthorizationEntity, Long>
        implements AuthorizationDao<AuthorizationEntity, Long>  {

    public AuthorizationEntity findByAppUser(Long appId, Long userId) {
        System.out.println("Unimplemented Method!");
        return null;
    }

    public List<Long> findRoleIdsByAppUserId(Long appId, Long userId) {
        String QueryRoleIdsByAppUserIdHQL = "from AuthorizationEntity where appId=:appId and userId=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("appId", appId);
        params.put("userId", userId);

        List<AuthorizationEntity> authorizationEntities = findMultiByParameters(QueryRoleIdsByAppUserIdHQL, params, false);
        if(null == authorizationEntities || authorizationEntities.isEmpty()){
            return new ArrayList<>();
        }
        String roleIds = authorizationEntities.get(0).getRoleIds();

        return ConvertUtil.stringConvert2LongList(roleIds, ",");

    }
}
