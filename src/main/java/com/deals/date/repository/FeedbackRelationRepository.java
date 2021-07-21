package com.deals.date.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deals.date.model.Customer;
import com.deals.date.model.Feedback;
import com.deals.date.model.FeedbackRelation;

@Repository
public interface FeedbackRelationRepository extends CrudRepository<FeedbackRelation, Integer> {
	
	@Query("Select f from FeedbackRelation f where f.cust=:cust")
	List<FeedbackRelation> findByCust(@Param("cust") Customer cust);
	
	@Query("Select f.dislikes from FeedbackRelation f where f.feed=:feed and f.cust=:cust")
	boolean findDislike(@Param("feed")Feedback feed, @Param("cust") Customer cust);
	
	@Query("Select f.dislikes from FeedbackRelation f where f.feed=:feed and f.cust=:cust")
	List<?> searchDislike(@Param("feed")Feedback feed, @Param("cust") Customer cust);
	
	@Query("Select f.likes from FeedbackRelation f where f.feed=:feed and f.cust=:cust")
	boolean findLike(@Param("feed") Feedback feed, @Param("cust") Customer cust);
	
	@Query("Select f from FeedbackRelation f where f.feed=:feed and f.cust=:cust")
	FeedbackRelation findByFeedAndCust(@Param("feed") Feedback feed, @Param("cust") Customer cust);
	
	@Transactional
	@Modifying
	@Query("Update FeedbackRelation f Set f.likes=:likes, f.dislikes=:dislikes where f.feed=:feed and f.cust=:cust")
	void updateLikesAndDislikes(@Param("likes") boolean likes, @Param("dislikes") boolean dislikes,@Param("feed") Feedback feed, @Param("cust") Customer cust);
}
