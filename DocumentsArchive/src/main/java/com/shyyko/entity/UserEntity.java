package com.shyyko.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 */
@javax.persistence.Table(name = "user", schema = "", catalog = "dee3f5bbe0acc45b58d586910ff762eda")
@Entity
public class UserEntity {
	private int id;
	private String username;
	private String password;
	private Role role;

	public UserEntity() {
	}

	public UserEntity(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@javax.persistence.Column(name = "id")
	@GeneratedValue
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@javax.persistence.Column(name = "username")
	@Basic
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@javax.persistence.Column(name = "password", length = 200)
	@Basic
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@javax.persistence.Column(name = "role", columnDefinition = "enum('ADMIN', 'USER')")
	@Enumerated(EnumType.STRING)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserEntity that = (UserEntity) o;

		if (id != that.id) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (role != null ? !role.equals(that.role) : that.role != null) return false;
		if (username != null ? !username.equals(that.username) : that.username != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		return result;
	}

	public static enum Role {
		USER, ADMIN
	}
}
