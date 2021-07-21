package com.deals.date.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deals.date.model.Customer;
import com.deals.date.model.Feedback;
import com.deals.date.model.FeedbackRelation;
import com.deals.date.repository.CustomerRepository;
import com.deals.date.repository.FeedbackRelationRepository;
import com.deals.date.repository.FeedbackRepository;

@Service
public class FeedbackRelationServiceImp implements FeedbackRelationService {
	@Autowired
	FeedbackRelationRepository RealtionRepo;
	@Autowired 
	CustomerRepository CustRepo;
	@Autowired
	FeedbackRepository FeedRepo;

	@Override
	public FeedbackRelation addFeedbackRelation(Customer cust, Feedback feed, String comment) {
		FeedbackRelation relation=new FeedbackRelation();
		if(RealtionRepo.findByFeedAndCust(feed, cust)!=null) {
			if (comment=="like") {
				RealtionRepo.updateLikesAndDislikes(true, false, feed, cust);
				}
			else {
				RealtionRepo.updateLikesAndDislikes(false, true, feed, cust);
			}
			
		}
		else {
		relation.setCust(cust);
		relation.setFeed(feed);
		if (comment=="like") {
			relation.setLikes(true);
			relation.setDislikes(false);
			}
		else {
			relation.setLikes(false);
			relation.setDislikes(true);
		}
		return RealtionRepo.save(relation);
		}
		return relation;
	}

	@Override
	public FeedbackRelation updateFeedbackRelation(Customer cust, Feedback feed, String comment) {
		FeedbackRelation relation=new FeedbackRelation();
		relation.setCust(cust);
		relation.setFeed(feed);
		if (comment=="like") {
			relation.setLikes(false);
			}
		else {
			relation.setDislikes(false);
		}
		return RealtionRepo.save(relation);
	}

	@Override
	public List<FeedbackRelation> findByCustIdAndFedId(String custId) {
		Customer cust=CustRepo.findByEmail(custId);	
		return RealtionRepo.findByCust(cust);
		}

}
