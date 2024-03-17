package com.binary.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "_user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String email;

	private String password;

	@Comment("1->Not, 2->Yes")
	private int isEmailVerified;

	private LocalDateTime emailVerifiedAt;

	@Comment("1->Not, 2->Yes")
	private int confirmEmail;

	private String mobileNo;

	@Comment("1->Backend, 2->Frontend")
	private int userType;

	private String sessionId;

	@Comment("count")
	private int isPasswordChanged;

	private LocalDateTime passUpdateTime;

	@Comment("1->Block, 2->Unblock")
	private int blockStatusId;

	private LocalDateTime blockStatusTime;

	private int countryId;

	private int stateId;

	private int cityId;

	@Comment("1->Normal, 2->Facebook, 3->Gmail, 4->Github")
	private int socialsiteType;

	private String pincode;

	@Comment("1->Active, 2->In Active")
	private int isActive;

	private String image;

	private int isLogIn;

	private LocalDateTime createdAt;
	private int createdBy;
	private LocalDateTime updatedAt;
	private int updatedBy;

	private int deletedAt;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(Long id, String firstName, String lastName, String email, String password, int isEmailVerified,
			LocalDateTime emailVerifiedAt, int confirmEmail, String mobileNo, int userType, String sessionId,
			int isPasswordChanged, LocalDateTime passUpdateTime, int blockStatusId, LocalDateTime blockStatusTime,
			int countryId, int stateId, int cityId, int socialsiteType, String pincode, int isActive, String image,
			int isLogIn, LocalDateTime createdAt, int createdBy, LocalDateTime updatedAt, int updatedBy, int deletedAt,
			Collection<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isEmailVerified = isEmailVerified;
		this.emailVerifiedAt = emailVerifiedAt;
		this.confirmEmail = confirmEmail;
		this.mobileNo = mobileNo;
		this.userType = userType;
		this.sessionId = sessionId;
		this.isPasswordChanged = isPasswordChanged;
		this.passUpdateTime = passUpdateTime;
		this.blockStatusId = blockStatusId;
		this.blockStatusTime = blockStatusTime;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.socialsiteType = socialsiteType;
		this.pincode = pincode;
		this.isActive = isActive;
		this.image = image;
		this.isLogIn = isLogIn;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
		this.deletedAt = deletedAt;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(int isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public LocalDateTime getEmailVerifiedAt() {
		return emailVerifiedAt;
	}

	public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public int getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(int confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getIsPasswordChanged() {
		return isPasswordChanged;
	}

	public void setIsPasswordChanged(int isPasswordChanged) {
		this.isPasswordChanged = isPasswordChanged;
	}

	public LocalDateTime getPassUpdateTime() {
		return passUpdateTime;
	}

	public void setPassUpdateTime(LocalDateTime passUpdateTime) {
		this.passUpdateTime = passUpdateTime;
	}

	public int getBlockStatusId() {
		return blockStatusId;
	}

	public void setBlockStatusId(int blockStatusId) {
		this.blockStatusId = blockStatusId;
	}

	public LocalDateTime getBlockStatusTime() {
		return blockStatusTime;
	}

	public void setBlockStatusTime(LocalDateTime blockStatusTime) {
		this.blockStatusTime = blockStatusTime;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getSocialsiteType() {
		return socialsiteType;
	}

	public void setSocialsiteType(int socialsiteType) {
		this.socialsiteType = socialsiteType;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getIsLogIn() {
		return isLogIn;
	}

	public void setIsLogIn(int isLogIn) {
		this.isLogIn = isLogIn;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(int deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
