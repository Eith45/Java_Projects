package com.shyyko.service.impl;

import com.shyyko.dao.FileDao;
import com.shyyko.entity.FileEntity;
import com.shyyko.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 */
@Service("fileService")
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao fileDao;

	@Override
	public List<FileEntity> getAllFindEntitiesWithoutData() {
		return fileDao.listAllWithoutData();
	}

	@Transactional(readOnly = false)
	@Override
	public void save(FileEntity file) {
		fileDao.saveEntity(file);
	}

	@Override
	public FileEntity getFileById(Serializable id) {
		return fileDao.getEntityById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(int id) {
		fileDao.delete(id);
	}

	@Override
	public List<FileEntity> getFilesByNotesWithoutData(String notes) {
		return fileDao.getFilesByNotesWithoutData(notes);
	}

	@Override
	public List<FileEntity> getFilesByFilenameWithoutData(String filename) {
		return fileDao.getFilesByFilenameWithoutData(filename);
	}
}