package com.deals.date.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deals.date.model.Customer;
import com.deals.date.model.Feedback;
import com.deals.date.repository.CustomerRepository;
import com.deals.date.repository.FeedbackRelationRepository;
import com.deals.date.repository.FeedbackRepository;

//Implementing the class as Service and implementing the interface
@Service
public class FeedbackServiceImp implements FeedbackService {
	// Autowiring the feedbackRepository
	@Autowired
	FeedbackRepository FeedbackRepo;
	
	@Autowired
	CustomerRepository CustRepo;
	
	@Autowired
	FeedbackRelationService RelationService;
	
	@Autowired
	FeedbackRelationRepository RelationRepo;

	// Method to add feedback into table
	@Override
	public Feedback addFeedback(Feedback f) {
		f.setFeedDate(LocalDate.now());
		return FeedbackRepo.save(f);
	}

	// Method to view feedbacks from table
	@Override
	public List<Feedback> viewFeedbackList() {
		return FeedbackRepo.findAll();
	}

	// Method to view feedbacks from table with specific custId
	@Override
	public List<Feedback> viewByCustId(String custId) {
		return FeedbackRepo.findByCustId(custId);
	}

	// Method to view feedbacks from table with specific rating
	@Override
	public List<Feedback> viewByRating(String rating) {
		return FeedbackRepo.findByRating(rating);
	}

	// Method to add like to a feedback
	@Override
	public String addLike(int fedId, String custId) {
		Feedback f = FeedbackRepo.findByFedId(fedId);
		Customer cust= CustRepo.findByEmail(custId);
		if(!RelationRepo.searchDislike(f, cust).isEmpty()) {
		if(RelationRepo.findDislike(f, cust)==true) {
			removeDislike(fedId, custId);
			}
		}
		RelationService.addFeedbackRelation(cust, f,"like");
		int like = f.getLike() + 1;
		f.setLike(like);
		FeedbackRepo.save(f);
		return "You liked the feedback";
	}

	// Method to add dislike to a feedback
	@Override
	public String addDislike(int fedId, String custId) {
		Feedback f = FeedbackRepo.findByFedId(fedId);
		Customer cust= CustRepo.findByEmail(custId);
		if(RelationRepo.findLike(f, cust)==true) {
			removeLike(fedId, custId);
			}
		RelationService.addFeedbackRelation(cust, f,"dislike");
		int like = f.getDislike() + 1;
		f.setDislike(like);
		FeedbackRepo.save(f);
		return "You disliked the feedback";
	}

	// Method to get the number of likes and dislikes for a particular feedback
	@Override
	public Feedback findLikeAndDislike(int fedId) {
		return FeedbackRepo.findByFedId(fedId);
	}

	// Method to remove like on feedback
	@Override
	public String removeLike(int fedId, String custId) {
		Feedback f = FeedbackRepo.findByFedId(fedId);
		Customer cust= CustRepo.findByEmail(custId);
		RelationRepo.updateLikesAndDislikes(false, RelationRepo.findByFeedAndCust(f, cust).isDislikes(), f, cust);
		int like = f.getLike() - 1;
		f.setLike(like);
		FeedbackRepo.save(f);
		return "Your like has been removed";

	}

	// Method to remove dislike on feedback
	@Override
	public String removeDislike(int fedId, String custId) {
		Feedback f = FeedbackRepo.findByFedId(fedId);
		Customer cust= CustRepo.findByEmail(custId);
		RelationRepo.updateLikesAndDislikes(RelationRepo.findByFeedAndCust(f, cust).isLikes(),false , f, cust);
		int like = f.getDislike() - 1;
		f.setDislike(like);
		FeedbackRepo.save(f);
		return "Your dislike has been removed";

	}
	
	//Method to get Feedback List by Date
	@Override
	public List<Feedback> viewByFeedDate(LocalDate FeedDate) {
		return FeedbackRepo.findByFeedDate(FeedDate);
	}

}
