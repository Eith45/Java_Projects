package com.shyyko.dao.impl;

import com.shyyko.dao.FileDao;
import com.shyyko.dao.UserDao;
import com.shyyko.entity.FileEntity;
import com.shyyko.entity.UserEntity;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * User: shyykoserhiy
 */
@Repository
public class FileDaoImpl extends AbstractDaoImpl<FileEntity, Serializable> implements FileDao {
	public FileDaoImpl() {
		super(FileEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileEntity> listAllWithoutData() {
		return getCurrentSession().createCriteria(getEntityClass()).
				setProjection(getFilesWithoutDataProjection())
				.setResultTransformer(Transformers.aliasToBean(getEntityClass()))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileEntity> getFilesByNotesWithoutData(String notes) {
		return getCurrentSession().createCriteria(getEntityClass())
				.add(Restrictions.ilike("notes", notes, MatchMode.ANYWHERE))
				.setProjection(getFilesWithoutDataProjection())
				.setResultTransformer(Transformers.aliasToBean(getEntityClass()))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileEntity> getFilesByFilenameWithoutData(String filename) {
		return getCurrentSession().createCriteria(getEntityClass())
				.add(Restrictions.ilike("filename", filename, MatchMode.ANYWHERE))
				.setProjection(getFilesWithoutDataProjection())
				.setResultTransformer(Transformers.aliasToBean(getEntityClass()))
				.list();
	}

	private Projection
	getFilesWithoutDataProjection() {
		return Projections.projectionList()
				.add(Projections.property("id"), "id")
				.add(Projections.property("filename"), "filename")
				.add(Projections.property("notes"), "notes")
				.add(Projections.property("type"), "type");
	}
}
