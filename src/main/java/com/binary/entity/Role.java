package com.binary.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@Comment("1->Admin, 2->User, 3->Editor")
	private int roleType;

	private String displayName;

	private int isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private LocalDateTime deletedAt;

	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private Collection<User> users = new HashSet<>();

	public Role() {
		super();
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(Long id, String name, int roleType, String displayName, int isActive, LocalDateTime deletedAt,
			Collection<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.roleType = roleType;
		this.displayName = displayName;
		this.isActive = isActive;
		this.deletedAt = deletedAt;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
