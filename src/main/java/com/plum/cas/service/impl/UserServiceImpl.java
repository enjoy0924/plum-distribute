package com.plum.cas.service.impl;

import com.plum.cas.dao.UserDao;
import com.plum.cas.dto.PrincipalAndCredential;
import com.plum.cas.dto.User;
import com.plum.cas.entity.UserEntity;
import com.plum.cas.service.UserService;
import com.plum.cas.utils.PasswordHelper;
import com.plum.core.queryfilter.PageSortFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * Created by G_dragon on 2015/7/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 这里需要对如下情况做处理
     * 账号注册，过来的username会是 13883092453_S， 13883092453_T 或者是 13883092453_P
     *
     * 如果发现该账户已经注册过，说明该手机号已经注册过，允许继续注册，但是需要在登录名里面增加后缀，
     * 返回给客户端
     *
     * @param username
     * @return
     */
    private String generatePrincipal(String username){

        String[] usrRole = username.split("_");
        if (null==usrRole || usrRole.length !=2)
            return null;

        if (usrRole[1].equals("S") || usrRole[1].equals("T") || usrRole[1].equals("P"))
        {
            User user = findByPrincipal(username);
            if(null != user){
                /**
                 * 以前已经有人注册过该账号，这里需要调用生成策略，
                 */
            }

            return username;
        }

        /** 解析不正确直接返回null */
        return null;
    }

    /**
     * 修改密码
     *
     * @param principalAndCredential
     */
    public void changePassword(PrincipalAndCredential principalAndCredential) {
        UserEntity userEntity = userDao.findByPrincipal(principalAndCredential.getPrincipal());
        if (null != userEntity)
        {
            User user = new User();
            user.setPassword(principalAndCredential.getNewCredential());
            user.setSalt(userEntity.getSalt());
            passwordHelper.encryptPassword(user);

            userEntity.setPassword(user.getPassword());
            userDao.update(userEntity);
        }
    }

    public Map<String, String> remoteValidateUser(PrincipalAndCredential principalAndCredential) {

        String username = principalAndCredential.getPrincipal();
        Map<String, String> result = new HashMap<String, String>();



        User user = findByPrincipal(username);
        if(user == null) {
            result.put("404", "UnknownAccountException");//没找到帐号
        }

        if(Boolean.TRUE.equals(user.getLocked())) {
            result.put("403", "LockedAccountException"); //帐号锁定禁止访问
        }

        User userToValidate = new User();
        userToValidate.setSalt(user.getSalt());
        userToValidate.setPassword(principalAndCredential.getCredential());
        userToValidate.setUsername(username);

        passwordHelper.encryptPassword(user);

        if (userToValidate.getPassword().equals(user.getPassword()))
            result.put("200", "OK");
        else
            result.put("403", "smsvalidate failed");

        return result;
    }

    /**
     * 删除一个用户
     * @param principal
     */
    public void deleteUserByPricipal(String principal) {
        UserEntity userEntity = userDao.findByPrincipal(principal);
        if (null != userEntity)
        {
            userDao.delete(userEntity);
        }
    }

    public void disableUserByPricipal(String principal) {

    }

    /**
     * 根据pricipal查找用户
     *
     * @param principal
     * @return
     */
    public User findByPrincipal(String principal) {

        UserEntity userEntity = userDao.findByPrincipal(principal);
        if (null == userEntity)
            return null;

        User user = new User();
        user.setEmail(userEntity.getEmail());
        //user.setAliasName(userEntity.getAliasname());
        user.setPassword(userEntity.getPassword());
        user.setLocked(userEntity.getLocked());
        user.setId(userEntity.getId());
        user.setPhone(userEntity.getPhone());
        user.setSalt(userEntity.getSalt());
        user.setUsername(userEntity.getUsername());

        return user;
    }

    public void changePasswordById(Long id, String newPassword) {

    }

    /**
     * 创建一个用户
     *
     * @param cube
     * @return
     */
    public User create(User cube) {

        passwordHelper.encryptPassword(cube);

        String username = cube.getUsername();

        /** 这里需要对用户名进行一个处理 */
        username = generatePrincipal(username);
        cube.setUsername(username);

        UserEntity userEntity = new UserEntity();
        userEntity.setPhone(cube.getPhone());
       // userEntity.setLinkedPhone(cube.getLinkedPhone());
        userEntity.setEmail(cube.getEmail());
        userEntity.setSalt(cube.getSalt());
        userEntity.setUsername(cube.getUsername());
        userEntity.setPassword(cube.getPassword());
        userEntity.setOrganizationId(cube.getOrganizationId());
       // userEntity.setAliasname(cube.getAliasName());

        userDao.create(userEntity);

        cube.setId(userEntity.getId());

        return cube;
    }

    /**
     * 预留一个接口，因为不确定更改的内容，目前可以确定密码是会更改的，所以实现changePassword
     *
     * @param user
     * @return
     */
    public User modify(User user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userDao.update(userEntity);

        return user;
    }

    /**
     * 删除一个用户，这个接口估计用不到，因为这个ID前端无法知道，也就没有办法传入
     *
     * @param aLong
     * @return
     */
    public User delete(Long aLong) {
        return null;
    }

    public User findByIndex(Long aLong) {
        return findOne(aLong);
    }

    public User findOne(Long aLong) {
        UserEntity userEntity = (UserEntity) userDao.findOne(aLong, UserEntity.class);
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);

        return user;
    }

    public List<User> findAll() {
        List<UserEntity> userEntityList = userDao.findAll("UserEntity");

        List<User> users = new ArrayList<User>();
        for(UserEntity userEntity : userEntityList){
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            users.add(user);
        }

        return users;
    }

    public List<User> findAllByPage(PageSortFilter page) {
        return null;
    }
}
