package com.shyyko.service;

import com.shyyko.entity.FileEntity;

import java.io.Serializable;
import java.util.List;

/**
 */
public interface FileService {
	List<FileEntity> getAllFindEntitiesWithoutData();

	void save(FileEntity file);

	FileEntity getFileById(Serializable id);

	void delete(int id);

	List<FileEntity> getFilesByNotesWithoutData(String notes);

	List<FileEntity> getFilesByFilenameWithoutData(String notes);
}
