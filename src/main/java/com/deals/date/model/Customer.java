package com.deals.date.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

//Creating entity class and table customer
@Entity
@Table(name = "customer")
public class Customer {

	// Creating column email and setting it as the primary key
	@Id
	@Column
	private String email;

	// Many to many relationship between customer and cart table
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "prodId"))
	private List<Product> prods = new ArrayList<>();

	// Creating column username
	@Column
	@NotBlank
	private String username;

	// Creating column password
	@Column
	@NotBlank
	private String password;

	// Creating column phoneNo
	@Column
	@NotBlank
	private String phoneNo;

	// Creating column address
	@Column
	@NotBlank
	private String address;

	// Generating getters and setters

	public String getEmail() {
		return email;
	}

	public List<Product> getProds() {
		return prods;
	}

	public void setProds(List<Product> prods) {
		this.prods = prods;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// Parameterized constructor
	public Customer(String email, @NotBlank String username, @NotBlank String password, @NotBlank String phoneNo,
			@NotBlank String address) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.phoneNo = phoneNo;
		this.address = address;
	}

	public Customer() {
	}

}