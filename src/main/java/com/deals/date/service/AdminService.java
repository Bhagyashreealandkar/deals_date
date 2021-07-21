package com.deals.date.service;

import java.util.List;

import com.deals.date.exception.NotFoundException;
import com.deals.date.model.Admin;
import com.deals.date.model.Customer;

//Defining an interface for the services of the Admin class
public interface AdminService {
	
	// Defining all the methods for the operations on Admin class
	public Admin addAdmin(Admin a);

	public Admin viewAdmin(String email);

	public String deleteAdmin(String email);

	public List<Admin> viewAllAdmins();
	
	public Admin updateAdmin(Admin a);

	public String updateAdminPassword(String email, String phoneNo, String password);

	public String viewAllFeedback();

	public String viewFeedbackById(String email);
	
	public String verifyAdmin(String email,String password) throws NotFoundException;
	
	public String verifyPhoneNo(String email,String phoneNo) throws NotFoundException;
}