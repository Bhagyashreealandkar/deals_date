package com.deals.date.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deals.date.exception.NotFoundException;
import com.deals.date.model.Admin;
import com.deals.date.model.Customer;
import com.deals.date.model.Feedback;
//import com.deals.date.model.Feedback;
import com.deals.date.repository.AdminRepository;
//import com.deals.date.repository.FeedbackRepository;
import com.deals.date.repository.FeedbackRepository;

//implementing CustomerService inteface
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepo;
	@Autowired
	FeedbackRepository feedbackRepo;

	// Add new admin
	@Override
	public Admin addAdmin(Admin a) {
		Admin a1 = new Admin();
		a1.setEmail(a.getEmail());
		a1.setPhoneNo(a.getPhoneNo());
		a1.setPassword(a.getPassword());
		return adminRepo.save(a1);
	}

	// View admin by email
	@Override
	public Admin viewAdmin(String email) {
		Admin admin = adminRepo.findByEmail(email);
		return admin;
	}

	// Delete customer
	@Override
	public String deleteAdmin(String email) {

		Admin myAdm = adminRepo.findByEmail(email);

		if (myAdm == null)
			return "Admin with email " + email + " not found";

		else {
			// myAdm.removeProducts();
			adminRepo.delete(myAdm);
			return "Admin deleted successfully!!";
		}
	}

	// View all admins
	@Override
	public List<Admin> viewAllAdmins() {
		List<Admin> alist = (List<Admin>) adminRepo.findAll();
		return alist;
	}
	
	// Update existing admin
	@Override
	public Admin updateAdmin(Admin a) {
		Admin adm = adminRepo.save(a);
		return adm;
	}

	// Update admin password
	@Override
	public String updateAdminPassword(String email, String phoneNo, String password) {
		Admin myAdmin = adminRepo.findByEmail(email);
		myAdmin.setPassword(password);
		myAdmin.setPhoneNo(phoneNo);
		adminRepo.save(myAdmin);
		return "Password updated Successfully!!";
	}

	// View view all feedbacks
	@Override
	public String viewAllFeedback() {
		String feedback ="feedback List";
		List<Feedback> flist = feedbackRepo.findAll();
		for (Feedback f : flist) {
			feedback = feedback + "\nfeedback Id: " + f.getFedId() + "\nfeedback Message: " + f.getMessage()
					+ "\nfeedback rating: " + f.getRating() +"\nCust Id: " + f.getCustId() + "\n\n";
		}
		return feedback;
	}

	// View feedbacks by id
	@Override
	public String viewFeedbackById(String email) {
		String feedback = "Customer: " + email + "\n\n";
		List<Feedback> flist = feedbackRepo.findByCustId(email);
		for (Feedback f : flist) {
			feedback = feedback + "\nfeedback Id: " + f.getFedId() + "\nfeedback Message: " + f.getMessage()
					+ "\nfeedback rating: " + f.getRating() +"\nCust Id: " + f.getCustId() +"\n\n";
		}
		return feedback;
	}
    
	//verify admin by password
	@Override
	public String verifyAdmin(String email, String password) throws NotFoundException {
		Admin admin=adminRepo.findByEmail(email);
		String checkpass=admin.getPassword();
		if(checkpass.equals(password)) {
			return "User verified";
		}
		else {
			throw new NotFoundException("Incorrect email or password"); 
		}
	}
	
    //verify admin by phoneNo
	@Override
	public String verifyPhoneNo(String email, String phoneNo) throws NotFoundException {
		Admin admin=adminRepo.findByEmail(email);
		String checkPhoneNo=admin.getPhoneNo();
		if(checkPhoneNo.equals(phoneNo)) {
			return "Phone number verified";
		}
		else {
			throw new NotFoundException("Email and Phone Number doesnot match");
		}
	}

	
}