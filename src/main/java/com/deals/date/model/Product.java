package com.deals.date.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//Creating a bean Products and Products Table
@Entity
@Table(name = "products")
public class Product {
// creating sequence for ID generation
	@SequenceGenerator(name = "productIdGenerator", initialValue = 1, allocationSize = 1)
// Assigning Primary key
	@Id
// Using the sequence to generate values
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodId;

	// validating column prodName
	@NotBlank
// validating products name using regular expression
	@Pattern(regexp = "^[a-zA-Z\\s]*$")
	@Column(name = "product_name")
	private String prodName;

	@Pattern(regexp = "^[a-zA-Z\\s]*$")
	@Column(name = "product_type")
	private String prodType;

	@NotNull
	@Column(name = "product_amount")
	private int prodPrice;

	public Product() {
	}

	public Product(int prodId, @NotBlank @Pattern(regexp = "^[a-zA-Z\\s]*$") String prodName,
			@Pattern(regexp = "^[a-zA-Z\\s]*$") String prodType, @NotNull int prodPrice) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodType = prodType;
		this.prodPrice = prodPrice;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

}