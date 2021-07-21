package com.deals.date.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deals.date.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

// Find customer by payment id
	public Payment findByPaymentId(int paymentId);

	@Query("SELECT p.paymentId FROM Payment p WHERE p.email=:email")
	public List<Integer> findPaymentByEmail(@Param("email") String email);

	@Query("SELECT p FROM Payment p WHERE p.email=:email")
	public List<Payment> searchPaymentByEmail(@Param("email") String email);

	@Query("SELECT p FROM Payment p WHERE p.payDate=:date")
	public List<Payment> findPaymentByDate(@Param("date") LocalDate date);

}