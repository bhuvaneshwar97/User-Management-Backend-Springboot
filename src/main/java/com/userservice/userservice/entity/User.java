package com.userservice.userservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table(name="users")
@Entity
public class User {

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)",unique=true)
	@Id
	private String userId;
	
	@Column(name = "`firstName`",columnDefinition = "VARCHAR(100)")
	private String firstName;
	
	@Column(name = "`lastName`",columnDefinition = "VARCHAR(100)")
	private String lastName;
	
	@Column(name = "`dob`")
	private Date dob;
	
	@Column(name = "`gender`",columnDefinition = "VARCHAR(10)")
	private String gender;
	
	@Column(name = "`fullAddress`",columnDefinition = "VARCHAR(100)")
	private String fullAddress;
	
	@Column(name = "`mobile`",columnDefinition = "VARCHAR(15)")
	private long mobile;
	
	@NotBlank(message = "email can't be blank")
	@Email(message = "invalid email")
	@Column(name = "`email`", columnDefinition = "VARCHAR(100)",unique=true)
	private String email;
	
	@Column(name = "`status`")
	private boolean status;
	
	@Column(name = "`createdAt`")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "updatedAt")
	@UpdateTimestamp
	private Date updatedAt;
	
	 /**setters and getters */

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/** constructor with no arguments */
	
	public User() {
		super();
	}

	/** constructor with all arguments */
	
	public User(String userId, String firstName, String lastName, Date dob, String gender, String fullAddress,
			long mobile, @NotBlank(message = "email can't be blank") @Email(message = "invalid email") String email,
			boolean status, Date createdAt, Date updatedAt) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.fullAddress = fullAddress;
		this.mobile = mobile;
		this.email = email;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	/** To String method */

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gender=" + gender + ", fullAddress=" + fullAddress + ", mobile=" + mobile + ", email=" + email
				+ ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
