package com.deals.date.service;

import java.util.List;
import java.util.Map;



import com.deals.date.model.Payment;



//Defining an interface for the services of the Payment class
public interface PaymentService {



// Defining all the methods for the operations on Payment class



public String makePayment(String email);
public List<Map<String, String>> viewMyListOfProds(String email);




}

