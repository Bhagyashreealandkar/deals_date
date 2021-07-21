package com.deals.date.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deals.date.model.Customer;
import com.deals.date.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
	
	List<Order> findByemail(String email);
	Order findByOrderId(int payId);
//	List<Order> findOrderByDate(LocalDate date);
	
	@Query("SELECT o FROM Order o WHERE o.payDate=:date")
	List<Order> findOrderByDate(LocalDate date);
}
