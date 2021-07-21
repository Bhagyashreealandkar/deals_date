package com.deals.date.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deals.date.model.Feedback;

//Implementing a feedbackRepository by extending JpaRepository for operations on Feedback Class
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	
	List<Feedback> findByCustId(String custId);

	List<Feedback> findByRating(String rating);

	Feedback findByFedId(int fedId);

	List<Feedback> findByFeedDate(LocalDate FeedDate);

}
