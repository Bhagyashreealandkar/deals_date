//package com.deals.date;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThat;
//
//import java.time.LocalDate;
//import java.util.*;
//import java.util.stream.Stream;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.deals.date.model.Feedback;
//import com.deals.date.repository.FeedbackRepository;
//import com.deals.date.service.FeedbackServiceImp;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class FeedBackTest {
//	
//	@MockBean
//	FeedbackRepository repository;
//	
//	@Autowired
//	FeedbackServiceImp service;
//	
//	@Test
//	public void testAddFeedback() {
//		Feedback feedback= new Feedback();
//		feedback.setCustId("alka@gmail.com");
//		feedback.setFedId(101);
//		feedback.setFeedDate(LocalDate.now());
//		feedback.setMessage("Great");
//		feedback.setRating("five");
//
//		Mockito.when(repository.save(feedback)).thenReturn(feedback);
//		assertThat (service.addFeedback(feedback)).isEqualTo(feedback);
//	}
//	
//}