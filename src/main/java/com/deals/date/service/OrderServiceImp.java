package com.deals.date.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deals.date.model.Order;
//import com.deals.date.model.Payment;
import com.deals.date.model.Product;
import com.deals.date.repository.OrderRepository;
//import com.deals.date.repository.PaymentRepository;
import com.deals.date.repository.ProductRepository;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	OrderRepository orderRepo;
//	@Autowired
//	PaymentRepository payRepo;
	@Autowired
	ProductRepository productRepo;

	// view order
	@Override
	public List<Map<String, String>> viewOrder(String email) {
		List<Order> listOrder = orderRepo.findByemail(email);
		List<Map<String, String>> o = new ArrayList<>();
		for (Order O : listOrder) {
			Map<String, String> output = new HashMap<String, String>();
			output.put("orderId", String.valueOf(O.getOrderId()));
			output.put("email", O.getEmail());
			Product v = productRepo.findByprodId(O.getProductId());
			output.put("ProductName", v.getProdName());
			output.put("ProductPrice", String.valueOf(v.getProdPrice()));
			output.put("Quantity", String.valueOf(O.getQty()));
			output.put("payDate", O.getPayDate().toString());
			o.add(output);
		}
		return o;
	}

	// view order by date
	@Override
	public List<Map<String, String>> findOrderByDate(LocalDate date) {
		List<Order> listOrder = orderRepo.findOrderByDate(date);
		List<Map<String, String>> o = new ArrayList<>();
		for (Order O : listOrder) {
			Map<String, String> output = new HashMap<String, String>();
			output.put("orderId", String.valueOf(O.getOrderId()));
			output.put("email", O.getEmail());
			Product v = productRepo.findByprodId(O.getProductId());
			output.put("ProductName", v.getProdName());
			output.put("ProductPrice", String.valueOf(v.getProdPrice()));
			output.put("Quantity", String.valueOf(O.getQty()));
			output.put("payDate", O.getPayDate().toString());
			o.add(output);
		}
		return o;

	}

	@Override
	public List<Map<String, String>> viewAllOrders() {
		List<Order> listOrder = orderRepo.findAll();
		List<Map<String, String>> o = new ArrayList<>();
		for (Order O : listOrder) {
			Map<String, String> output = new HashMap<String, String>();
			output.put("orderId", String.valueOf(O.getOrderId()));
			output.put("email", O.getEmail());
			Product v = productRepo.findByprodId(O.getProductId());
			output.put("ProductName", v.getProdName());
			output.put("ProductPrice", String.valueOf(v.getProdPrice()));
			output.put("Quantity", String.valueOf(O.getQty()));
			output.put("payDate", O.getPayDate().toString());
			o.add(output);
		}
		return o;
	}

	@Override
	public String cancelOrder(int orderId){
		 Order o=orderRepo.findByOrderId(orderId);
		 LocalDate from =o.getPayDate();
	        LocalDate to =  LocalDate.now();

	        long result = ChronoUnit.DAYS.between(from, to);
	        System.out.println(result); 
	    	if(result<10) {
	   			orderRepo.deleteById(orderId);
	   			return "Order cancelled Successfully ";
	    	   }
	    	else {
	    		return null;
	    	}
	   	
	  
		
	}
//	public String viewOrder(String email) {
//		List<Order> order = orderRepo.findByemail(email);
//		List<Integer> pay = payRepo.findPaymentByEmail(email);
//		String oString = "Email: " + email;
//
//		for(Integer p:pay) {
//			oString=oString+"Email: "+payRepo.findByPaymentId(p).getEmail();
//			Payment p1 = null;
//		for(Order o:order) {
//			if(p==o.getPayId()) {
//				Vegetable v=vegRepo.findByvegId(o.getVegId());
//				oString=oString+"\nVegetable Info: [Vegetable name:"+v.getVegName()+
//						",Vegetable price:"+v.getVegPrice();
//			}
//			p1=payRepo.findByPaymentId(o.getPayId());
//		}
//		oString=oString+"\nPayment Info: [Payment date:"+p1.getPayDate()+
//				",Total Amount:"+p1.getTotalAmount()+"]\n";
//		}
//		return oString;
//	}

//	@Override
//	public String findOrderByDate(LocalDate date) {
//		List<Integer> pay=payRepo.findPaymentByDate(date).stream().map(pId->pId.getPaymentId()).collect(Collectors.toList());
//		List<Order> order=orderRepo.findAll();
//		String oString=""; 
//		for(Integer p:pay) {
//			oString=oString+"Email: "+payRepo.findByPaymentId(p).getEmail();
//			Payment p1 = null;
//		for(Order o:order) {
//			if(p==o.getPayId()) {
//				Vegetable v=vegRepo.findByvegId(o.getVegId());
//				oString=oString+"\nVegetable Info: [Vegetable name:"+v.getVegName()+
//						",Vegetable price:"+v.getVegPrice();
//			}
//			p1=payRepo.findByPaymentId(o.getPayId());
//		}
//		oString=oString+"\nPayment Info: [Payment date:"+p1.getPayDate()+
//				",Total Amount:"+p1.getTotalAmount()+"]\n";
//		}
//		return oString;
//	}
//
//	@Override
//	public String viewAllOrders() {
//		List<Integer> pay=payRepo.findAll().stream().map(pId->pId.getPaymentId()).collect(Collectors.toList());
//		List<Order> order=orderRepo.findAll();
//		String oString=""; 
//		for(Integer p:pay) {
//			oString=oString+"Email: "+payRepo.findByPaymentId(p).getEmail();
//			Payment p1 = null;
//		for(Order o:order) {
//			if(p==o.getPayId()) {
//				Vegetable v=vegRepo.findByvegId(o.getVegId());
//				oString=oString+"\nVegetable Info: [Vegetable name:"+v.getVegName()+
//						",Vegetable price:"+v.getVegPrice();
//			}
//			p1=payRepo.findByPaymentId(o.getPayId());
//		}
//		oString=oString+"\nPayment Info: [Payment date:"+p1.getPayDate()+
//				",Total Amount:"+p1.getTotalAmount()+"]\n";
//		}
//		return oString;
//	}
}
