package com.plum.cas.dao.impl;

import com.plum.cas.dao.UserDao;
import com.plum.cas.entity.UserEntity;
import com.plum.core.dao.AbstractBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl extends AbstractBaseDao<UserEntity, Long> implements UserDao<UserEntity, Long> {

    /**
     * 目前暂时只根据用户名来登录，因为目前的方案只能确保用户名是唯一的，电话、邮箱都不是唯一的
     *
     * @param principal
     * @return
     */
    public UserEntity findByPrincipal(String principal) {

        String QueryByPricialHQL = "from UserEntity u where u.username=:username";
        Map<String, Object> params = new HashMap<>();
        params.put("username", principal);

        return findSingleByParameters(QueryByPricialHQL, params, false);

    }
}
