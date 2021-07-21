package com.deals.date.model;
//Creating entity class and table customer

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "admin")
public class Admin {

	// Creating column email and setting it as the primary key
	@Id
	@Column
	@NotBlank
	private String email;

	// Creating column username
	@Column
	@NotBlank
	private String phoneNo;

	// Creating column password
	@Column
	@NotBlank
	private String password;

	public Admin() {
	}

	// Generating getters and setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [email=" + email + ", phoneNo=" + phoneNo + ", password=" + password + "]";
	}

	public Admin(@NotBlank String email, @NotBlank String phoneNo, @NotBlank String password) {
		super();
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
	}
}