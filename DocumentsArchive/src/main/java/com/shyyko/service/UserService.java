package com.shyyko.service;

import com.shyyko.entity.UserEntity;

import java.io.Serializable;

/**
 * User: shyykoserhiy
 */
public interface UserService {
    UserEntity findUserById(Serializable id);

    Serializable saveUser(UserEntity userEntity);

    UserEntity findUserByLogin(String name);

    void updateUser(UserEntity userEntity);
}
