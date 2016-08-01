package com.shyyko.dao.impl;

import com.shyyko.dao.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * User: shyykoserhiy
 */
public abstract class AbstractDaoImpl<Entity, Id extends Serializable> implements Dao<Entity, Id> {

	private Class<Entity> entityClass;
	@Autowired
	private SessionFactory sessionFactory;

	public AbstractDaoImpl(Class<Entity> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable saveEntity(Entity entity) {
		return getCurrentSession().save(entity);
	}

	@Override
	public void updateEntity(Entity userEntity) {
		getCurrentSession().update(userEntity);
	}

	public Class<Entity> getEntityClass() {
		return entityClass;
	}

	@Override
	public Entity getEntityById(Id id) {
		return (Entity) getCurrentSession().get(getEntityClass(), id);
	}

	@Override
	public List<Entity> listAll() {
		return getCurrentSession().createCriteria(getEntityClass()).list();
	}

	@Override
	public void delete(Id id) {
		Entity entity = getEntityById(id);
		if (entity != null) {
			getCurrentSession().delete(entity);
		}
	}
}
