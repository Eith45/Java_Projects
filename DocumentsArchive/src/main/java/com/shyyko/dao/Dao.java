package com.shyyko.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * User: shyykoserhiy
 */
public interface Dao<Entity, Id extends Serializable> {
	Serializable saveEntity(Entity entity);

	Entity getEntityById(Id id);

	Session getCurrentSession();

	List<Entity> listAll();

	void updateEntity(Entity entity);

	void delete(Id id);
}

