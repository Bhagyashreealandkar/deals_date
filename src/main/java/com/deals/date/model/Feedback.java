package com.deals.date.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

//Creating a bean Feedback and table feedback
@Entity
public class Feedback {

	
	// creating sequence for ID generation
	@SequenceGenerator(name = "feedbackIdGenerator", initialValue = 101, allocationSize = 1)

	// Using the sequence to generate values
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "feedbackIdGenerator")

	// Assigning Primary key
	@Id
	private int fedId;

	// Defining all the columns
	@Column
	@NotBlank
	private String custId;

	@Column
	@NotBlank
	private String message;

	@Column
	@NotBlank
	private String rating;

	@Column(name = "likes")
	private int like;

	@Column(name = "dislikes")
	private int dislike;
	
	@Column
	private LocalDate feedDate;

	// Implementing all the getters and setters method
	public int getFedId() {
		return fedId;
	}

	public void setFedId(int fedId) {
		this.fedId = fedId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	
	public LocalDate getfeedDate() {
		return feedDate;
	}

	public void setFeedDate(LocalDate feedDate) {
		this.feedDate = feedDate;
	}

}
