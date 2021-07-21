package com.deals.date.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class FeedbackRelation {
	// creating sequence for ID generation
	@SequenceGenerator(name = "feedbackRelationIdGenerator", initialValue = 501, allocationSize = 1)

	// Using the sequence to generate values
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "feedbackRelationIdGenerator")

	// Assigning Primary key
	@Id
	private int realtionId;
	
	@Column
	private boolean likes;
	
	@Column
	private boolean dislikes;
	
	//Generating all getters and setters
	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public Feedback getFeed() {
		return feed;
	}

	public void setFeed(Feedback feed) {
		this.feed = feed;
	}

	@OneToOne
	private Customer cust;
	
	@OneToOne
	private Feedback feed;

	public int getRealtionId() {
		return realtionId;
	}

	public void setRealtionId(int realtionId) {
		this.realtionId = realtionId;
	}

	public boolean isLikes() {
		return likes;
	}

	public void setLikes(boolean likes) {
		this.likes = likes;
	}

	public boolean isDislikes() {
		return dislikes;
	}

	public void setDislikes(boolean dislikes) {
		this.dislikes = dislikes;
	}

}
