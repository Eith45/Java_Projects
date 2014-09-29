package com.shyyko.dao;

import com.shyyko.entity.FileEntity;
import com.shyyko.entity.UserEntity;

import java.io.Serializable;
import java.util.List;

/**
 * User: shyykoserhiy
 */
public interface FileDao extends Dao<FileEntity, Serializable> {
	List<FileEntity> listAllWithoutData();

	List<FileEntity> getFilesByNotesWithoutData(String notes);

	List<FileEntity> getFilesByFilenameWithoutData(String filename);
}
