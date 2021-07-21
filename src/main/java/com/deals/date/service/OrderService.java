package com.deals.date.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.deals.date.model.Order;


public interface OrderService {
	public List<Map<String, String>> viewOrder(String email);
	public List<Map<String, String>> findOrderByDate(LocalDate date);
	
	public List<Map<String, String>> viewAllOrders();
	public String cancelOrder(int orderId) ;
}
