package com.shyyko.dao.impl;

import com.shyyko.dao.UserDao;
import com.shyyko.entity.UserEntity;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * User: shyykoserhiy
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<UserEntity, Serializable> implements UserDao {
    public UserDaoImpl() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity getEntityByLogin(String username) {
        List list = getCurrentSession().createCriteria(getEntityClass()).add(Restrictions.eq("username", username)).list();
        if (list.size() > 0) {
            return (UserEntity) list.get(0);
        }
        return null;
    }
}
