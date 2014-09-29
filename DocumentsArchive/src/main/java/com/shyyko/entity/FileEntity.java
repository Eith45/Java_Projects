package com.shyyko.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

/**
 */
@javax.persistence.Table(name = "file", schema = "", catalog = "dee3f5bbe0acc45b58d586910ff762eda")
@Entity
public class FileEntity {
	private int id;
	private String filename;
	private String notes;
	private String type;
	private byte[] file;

	@javax.persistence.Column(name = "id")
	@GeneratedValue
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@javax.persistence.Column(name = "filename")
	@Basic
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@javax.persistence.Column(name = "notes")
	@Basic
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@javax.persistence.Column(name = "type")
	@Basic
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@javax.persistence.Column(name = "file", columnDefinition = "LONGBLOB")
	@Basic
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FileEntity that = (FileEntity) o;

		if (id != that.id) return false;
		if (!Arrays.equals(file, that.file)) return false;
		if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
		if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
		if (type != null ? !type.equals(that.type) : that.type != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (filename != null ? filename.hashCode() : 0);
		result = 31 * result + (notes != null ? notes.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (file != null ? Arrays.hashCode(file) : 0);
		return result;
	}
}
