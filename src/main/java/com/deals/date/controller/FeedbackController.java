package com.deals.date.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deals.date.exception.IllegalFormatException;
import com.deals.date.exception.NotFoundException;
import com.deals.date.model.Feedback;
import com.deals.date.model.FeedbackRelation;
import com.deals.date.repository.FeedbackRepository;
import com.deals.date.service.CustomerService;
import com.deals.date.service.FeedbackRelationService;
import com.deals.date.service.FeedbackService;
@CrossOrigin(origins = "*")

//Defining a Rest Controller and mapping it to URL
@RestController
@RequestMapping("/Feedback")
public class FeedbackController {

	// pattern for validating the input
	String custIdPattern = "[a-zA-Z0-9+_.-]+@(.+)$";
	String ratingPattern = "([1-4][.][0-9]|1|2|3|4|5)$";
	String fedIdPattern = "[1-9][0-9][0-9]";
	String DatePattern = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";

	// Autowiring the service classes of feedback and customer for further use
	@Autowired
	FeedbackService service;
	@Autowired
	FeedbackRelationService relService;
	@Autowired
	CustomerService custService;
	@Autowired
	FeedbackRepository repo;

	// Post method to add feedback to the table
	@PostMapping("/addFeedback")
	private ResponseEntity<String> addFeedback(@RequestBody @Valid Feedback f, Errors errors)
			throws IllegalFormatException, NotFoundException {

		// Validating input
		if (errors.hasFieldErrors("message")) {
			throw new IllegalFormatException("Message cannot be empty!!");
		}
		if (errors.hasFieldErrors("custId")) {
			throw new IllegalFormatException("custId cannot be empty!!");
		}
		if (errors.hasFieldErrors("rating")) {
			throw new IllegalFormatException("rating cannot be empty!!");
		}

		// Validating using pattern
		if (!f.getCustId().matches(custIdPattern)) {
			throw new IllegalFormatException("Customer Id format is not valid!!");
		}

		if (!f.getRating().matches(ratingPattern)) {
			throw new IllegalFormatException("Rating must be between 0.0 to 5.0");
		}

		// Checking whether the Customer exists
		if (custService.viewCustomer(f.getCustId()) == null) {
			// throwing exception if customer is not found
			throw new NotFoundException("The given Customer Id is Not Registered");
		} else {
			Feedback response = service.addFeedback(f);
			if (response == null) {
				return new ResponseEntity<String>("Feedback not added", HttpStatus.FORBIDDEN);
			}

			else {
				return new ResponseEntity<String>("Feedback added successfully!!", HttpStatus.OK);
			}
		}
	}

	// Get method to view all feedbacks
	@GetMapping("/viewAllFeedbacks")
	private ResponseEntity<?> viewAllFeedback() throws NotFoundException {
		List<Feedback> feedbackList = service.viewFeedbackList();

		// Checking whether feedbacks are present in table
		if (feedbackList.isEmpty()) {
			// Throwing exception if no feedbacks are there
			throw new NotFoundException("There are no Feedbacks yet.");
		}

		else {
			return new ResponseEntity<List<Feedback>>(feedbackList, HttpStatus.OK);
		}
	}

	// Get method to view feedback by custId
	@GetMapping("/viewByCustId/{custId}")
	private ResponseEntity<?> viewByCustId(@PathVariable("custId") String custId)
			throws IllegalFormatException, NotFoundException {

		// Validating the input
		if (custId.matches(custIdPattern)) {
			List<Feedback> feedbackList = service.viewByCustId(custId);

			// Checking whether feedbacks with the specified custId is present
			if (feedbackList.isEmpty()) {
				// Throwing exception if no feedback is found
				throw new NotFoundException("No feedbacks with the given Customer Id is found");
			}

			else {
				return new ResponseEntity<List<Feedback>>(feedbackList, HttpStatus.OK);
			}
		} else {
			throw new IllegalFormatException("Customer Id is not in correct format");
		}
	}

	// Get method to view feedback by rating
	@GetMapping("/viewByRating/{rating}")
	private ResponseEntity<?> viewByRating(@PathVariable("rating") String rating)
			throws IllegalFormatException, NotFoundException {
		if (rating.matches(ratingPattern)) {

			List<Feedback> feedbackList = service.viewByRating(rating);

			// Checking whether feedbacks with the specified rating is present
			if (feedbackList.isEmpty()) {
				// Throwing exception if no feedback is found
				throw new NotFoundException("No feedbacks with the given rating is found");
			}

			else {
				return new ResponseEntity<List<Feedback>>(feedbackList, HttpStatus.OK);
			}
		}

		else {
			// Throwing exception if the input is invalid
			throw new IllegalFormatException("Rating must be between 0.0 to 5.0");
		}

	}

	// Put method to add like to a feedback
	@PutMapping("/addLike/{fedId}/{custId}")
	private String addLike(@PathVariable("fedId") int fedId, @PathVariable("custId") String custId) throws NotFoundException, IllegalFormatException {

		// Validating input
		if (String.valueOf(fedId).matches(fedIdPattern)) {
			if(custId.matches(custIdPattern)) {
				if (custService.viewCustomer(custId) != null) {
					// Checking existence of fedId
					Feedback feed = repo.findByFedId(fedId);

					// If fedId not found throwing exception
					if (feed == null) {
						throw new NotFoundException("fedId is not present in table");
					}

					else {
						return service.addLike(fedId, custId);
					}
				}
				else {
					throw new NotFoundException("The given custId is not registered");
				}
			}
				
			else {
				throw new IllegalFormatException("fedId is not in correct format");
			}
		}

		// If format is wrong throwing exception
		else {
			throw new IllegalFormatException("fedId is not in correct format");
		}
	}

	// Put method to add dislike to a feedback
	@PutMapping("/addDislike/{fedId}/{custId}")
	private ResponseEntity<String> addDislike(@PathVariable("fedId") int fedId, @PathVariable("custId") String custId)
			throws NotFoundException, IllegalFormatException {

		// Validating input
		if (String.valueOf(fedId).matches(fedIdPattern)) {
			if(custId.matches(custIdPattern)) {
				if (custService.viewCustomer(custId) != null) {
					// Checking existence of fedId
					Feedback feed = repo.findByFedId(fedId);

					// If fedId not found throwing exception
					if (feed == null) {
						throw new NotFoundException("fedId is not present in table");
					}

					else {
						String result = service.addDislike(fedId,custId);
						return new ResponseEntity<String>(result, HttpStatus.OK);
					}
				}
				else {
					throw new NotFoundException("The given custId is not registered");
				}
			}
			else {
				throw new IllegalFormatException("CustId is not in correct format");
			}
		}

		// Throwing exception if format is wrong
		else {
			throw new IllegalFormatException("fedId is not in correct format");
		}
	}

	// Get method to show counts of likes and dislikes for a feedback
	@GetMapping("/getLikeAndDislike/{fedId}")
	private ResponseEntity<?> getLikeAndDislike(@PathVariable("fedId") int fedId)
			throws NotFoundException, IllegalFormatException {
		// Validating input
		if (String.valueOf(fedId).matches(fedIdPattern)) {
			// Checking existence of fedId
			Feedback feed = repo.findByFedId(fedId);
			// If fedId not found throwing exception
			if (feed == null) {
				throw new NotFoundException("fedId is not present in table");
			} else {
				Feedback result = service.findLikeAndDislike(fedId);
				String res = "The Likes and Dislikes count for fedId: " + fedId + ": \nLike: " + result.getLike()
						+ "\nDislike: " + result.getDislike();
				return new ResponseEntity<String>(res, HttpStatus.OK);
			}
		}
		// Throwing exception if format is wrong
		else {
			throw new IllegalFormatException("fedId is not in correct format");
		}
	}

	// Put method to remove like
	@PutMapping("/removeLike/{fedId}/{custId}")
	private String removeLike(@PathVariable("fedId") int fedId, @PathVariable("custId") String custId) throws NotFoundException, IllegalFormatException {

		// Validating input
		if (String.valueOf(fedId).matches(fedIdPattern)) {
			if(custId.matches(custIdPattern)) {
				if (custService.viewCustomer(custId) != null) {
					// Checking existence of fedId
					Feedback feed = repo.findByFedId(fedId);

					// If fedId not found throwing exception
					if (feed == null) {
						throw new NotFoundException("fedId is not present in table");
					}

					else {
						return service.removeLike(fedId,custId);
					}
				}
				else {
					throw new NotFoundException("The given custId is not registered");
				}
			}
			else {
				throw new IllegalFormatException("cuatId is not in correct format");
			}
			
		}

		// Throwing exception if format is wrong
		else {
			throw new IllegalFormatException("fedId is not in correct format");
		}
	}

	// Put method to remove a dislike
	@PutMapping("/removeDislike/{fedId}/{custId}")
	private ResponseEntity<String> removeDislike(@PathVariable("fedId") int fedId, @PathVariable("custId") String custId)
			throws NotFoundException, IllegalFormatException {

		// Validating input
		if (String.valueOf(fedId).matches(fedIdPattern)) {
			if(custId.matches(custIdPattern)) {
				if (custService.viewCustomer(custId) != null) {
					// Checking existence of fedId
					Feedback feed = repo.findByFedId(fedId);

					// If fedId not found throwing exception
					if (feed == null) {
						throw new NotFoundException("fedId is not present in table");
					}

					else {
						String result = service.removeDislike(fedId, custId);
						return new ResponseEntity<String>(result, HttpStatus.OK);
					}
				}
				else {
					throw new NotFoundException("The given custId is not registered");
				}
			}
			else {
				throw new IllegalFormatException("custId is not in correct format");
			}

			
		}

		// Throwing exception if format is wrong
		else {
			throw new IllegalFormatException("fedId is not in correct format");
		}
	}
	
	//Get method to view Feedback by date
	@GetMapping("/getFeedbackByDate/{FeedDate}")
	private ResponseEntity<?> getFeedbackByDate(@PathVariable("FeedDate") String FeedDate)
			throws NotFoundException, IllegalFormatException {
		//Checking Date Format
		if(FeedDate.matches(DatePattern)) {
			//converting string to LocalDate
			LocalDate date1=LocalDate.parse(FeedDate);  
			List<Feedback> feedbackList=service.viewByFeedDate(date1);
			if(feedbackList.isEmpty()) {
				//Throw Exception if no feedback with given date is present
				throw new NotFoundException("No Feedback on the given Date is present");
			}
			else {
				return new ResponseEntity<List<Feedback>>(feedbackList, HttpStatus.OK);
			}
		}
		else {
			//Throwing Exception if Date is not in correct format
			throw new IllegalFormatException("Date is not in correct Format");
		}
		
	}
	
	//Get method to view Feedback by date
		@GetMapping("/getFeedbackRelationByCustId/{custId}")
		private ResponseEntity<?> getFeedRelationByCustId(@PathVariable("custId") String custId) throws NotFoundException{
			List<FeedbackRelation> feedRel=relService.findByCustIdAndFedId(custId);
			if(feedRel==null) {
				throw new NotFoundException("No Feedback is given by the given customer");
			}
			else {
				return new ResponseEntity<List<FeedbackRelation>>(feedRel, HttpStatus.OK);
			}
		}
	
}
