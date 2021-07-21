package com.deals.date.service;

import java.util.List;

import com.deals.date.model.Customer;
import com.deals.date.model.Feedback;
import com.deals.date.model.FeedbackRelation;

public interface FeedbackRelationService {
	FeedbackRelation addFeedbackRelation(Customer cust, Feedback feed, String comment);
	FeedbackRelation updateFeedbackRelation(Customer cust, Feedback feed, String comment);
	List<FeedbackRelation> findByCustIdAndFedId(String custId);

}
