package com.hsm.bootjdbc.service;

import com.hsm.bootjdbc.entity.po.UserPO;

import java.util.List;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/8/4 14:55
 */
public interface UserService {

    /**
     * 新增一个用户
     *
     * @param name
     * @param age
     */
    int create(String name, Integer age);

    /**
     * 根据name查询用户
     *
     * @param name
     * @return
     */
    List<UserPO> getByName(String name);

    /**
     * 根据name删除用户
     *
     * @param name
     */
    int deleteByName(String name);

    /**
     * 获取用户总量
     */
    int getAllUsers();

    /**
     * 删除所有用户
     */
    int deleteAllUsers();
}
