package com.deals.date.service;

import java.time.LocalDate;
import java.util.List;

import com.deals.date.model.Feedback;

//Defining an interface for the services of the feedback class
public interface FeedbackService {

	// Defining all the methods for the operations on feedback class
	public Feedback addFeedback(Feedback f);

	public List<Feedback> viewFeedbackList();

	public List<Feedback> viewByCustId(String custId);

	public List<Feedback> viewByRating(String rating);
	
	public List<Feedback> viewByFeedDate(LocalDate FeedDate);
	
	public String addLike(int fedId, String custId);

	public String addDislike(int fedId, String custId);

	public Feedback findLikeAndDislike(int fedId);

	public String removeLike(int fedId, String custId);

	public String removeDislike(int fedId, String custId);

}
