package com.shyyko.service.impl;

import com.shyyko.dao.UserDao;
import com.shyyko.entity.UserEntity;
import com.shyyko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * User: shyykoserhiy
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserEntity findUserById(Serializable id) {
		return userDao.getEntityById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Serializable saveUser(UserEntity userEntity) {
		return userDao.saveEntity(userEntity);
	}

	@Override
	public UserEntity findUserByLogin(String login) {
		return userDao.getEntityByLogin(login);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateUser(UserEntity userEntity) {
		userDao.updateEntity(userEntity);
	}
}
