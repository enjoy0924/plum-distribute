package com.plum.cas.service;

import com.plum.cas.dto.PrincipalAndCredential;
import com.plum.cas.dto.User;
import com.plum.core.service.BaseService;

import java.util.Map;


public interface UserService extends BaseService<User, Long> {


    void changePassword(PrincipalAndCredential principalAndCredential);

    Map<String, String> remoteValidateUser(PrincipalAndCredential principalAndCredential);

    void deleteUserByPricipal(String principal);

    void disableUserByPricipal(String principal);

    User findByPrincipal(String principal);

    void changePasswordById(Long id, String newPassword);

}
