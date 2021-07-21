package com.deals.date.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OrderTable")
public class Order {
	@SequenceGenerator(name = "orderIdGenerator", initialValue = 8001, allocationSize = 1)
	// Using the sequence to generate values
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "orderIdGenerator")
	@Id
	int orderId;
	
	@Column
	String email;
	
	@Column
	int qty;
	
	@Column
	int productId;
	
	@Column
	int payId;

	@Column
	private LocalDate payDate;
	
	public Order( String email, int qty, int productId, int payId,LocalDate payDate) {
		super();
		this.email = email;
		this.qty = qty;
		this.productId = productId;
		this.payId = payId;
		this.payDate = payDate;
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getPayId() {
		return payId;
	}
	public void setPayId(int payId) {
		this.payId = payId;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}
	
}
