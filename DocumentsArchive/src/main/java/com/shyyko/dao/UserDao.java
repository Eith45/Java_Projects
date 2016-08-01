package com.shyyko.dao;

import com.shyyko.entity.UserEntity;

import java.io.Serializable;

/**
 * User: shyykoserhiy
 */
public interface UserDao extends Dao<UserEntity, Serializable> {
    UserEntity getEntityByLogin(String username);
}
