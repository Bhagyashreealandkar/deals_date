package com.deals.date.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
@Entity
public class Cart {
	@SequenceGenerator(name = "cartIdGenerator", initialValue = 6001, allocationSize = 1)
	// Using the sequence to generate values
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cartIdGenerator")
	@Id
	int Cartid;
	@Column
	String email;
	@Column
	int prodId;
	@Column
	int qty;
	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int cartid, String email, int prodId, int qty) {
		super();
		Cartid = cartid;
		this.email = email;
		this.prodId = prodId;
		this.qty = qty;
	}
	
	public int getCartid() {
		return Cartid;
	}
	public void setCartid(int cartid) {
		Cartid = cartid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}