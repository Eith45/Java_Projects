package com.shyyko.model.form;

import org.springframework.web.multipart.MultipartFile;

/**
 */
public class FileUploadForm {

	private MultipartFile file;

	private String notes;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
