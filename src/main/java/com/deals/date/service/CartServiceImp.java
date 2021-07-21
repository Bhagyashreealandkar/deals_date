package com.deals.date.service;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deals.date.model.Cart;
import com.deals.date.model.Customer;
import com.deals.date.model.Product;
import com.deals.date.repository.CartRepository;
import com.deals.date.repository.CustomerRepository;
import com.deals.date.repository.ProductRepository;

@Service
public class CartServiceImp {
	@Autowired
	CartRepository cart;
	@Autowired
	ProductRepository productRepo;
	
	public void addToCart(String email,int prodId) {
		List<Integer> productList=cart.findByEmail(email).stream().map(c1->c1.getProdId()).collect(Collectors.toList());
		Cart c=new Cart();
		c.setEmail(email);
		c.setProdId(prodId);
		c.setQty(1);
		if(productList.isEmpty())
			cart.save(c);
		else {
			for(int product:productList) {
			if(product==c.getProdId()) {
				updateQtyofProd(c);
				break;
			}
			else
				cart.save(c);
		}
	}
}
	public String removeProductFromCart(Cart c) {
		cart.removeProdFromCart(c.getEmail(), c.getProdId());
		return "DELETED SUCCCESSFULLY";
	}
	public String removeAllProdFromCart(String email) {
		cart.removeCart(email);
		return "CART DELETED SUCCESSFULLY";
	}
	public String updateQtyofProd(Cart c) {
		if(c.getQty()==0)
			removeProductFromCart(c);
		else
			cart.updateQtyOfProdInCart(c.getQty(), c.getEmail(), c.getProdId());
		return "UPDATE CART";
	}
	
	public String printCustomer(Cart c) {
		// To print List of Vegetable with customer email
		String product = "";
		if (!(c.getProdId()==0)) {

			Product v=productRepo.findByprodId(c.getProdId());	
				product = product + "ID: " + v.getProdId() + "\nName: " + v.getProdName() + "\nPrice: "
						+ v.getProdPrice() + "\n\n";

			return "Customer Email: " + c.getEmail() + "\n\nList of Product in Cart:\n" + product;
		} else
			return "Customer Email: " + c.getEmail() + "\nNo Product Found in Cart\n\n";
	}
	
	public List<Map<String, String>> viewAll() {
		List<Cart> listCart=cart.findAll();
		List<Map<String, String>> o=new ArrayList<>();
		for (Cart c : listCart) {
			Map<String,String> output = new HashMap<String, String>();
			output.put("Quantity",String.valueOf(c.getQty()));
			output.put( "email",c.getEmail());
			int vId=cart.searchByCartId(c.getCartid()).getProdId();
			Product v=productRepo.findByprodId(vId);
			output.put("ProductName",v.getProdName());
			output.put("ProductPrice", String.valueOf(v.getProdPrice()));
			o.add(output);
		}
		return o;
	}
	public List<Map<String, String>> viewByEmail(String email) {
		List<Cart> listCart=cart.findByEmail(email);
		List<Map<String, String>> o=new ArrayList<>();
		for (Cart c : listCart) {
			Map<String,String> output = new HashMap<String, String>();
			output.put("CartId", String.valueOf(c.getCartid()));
			output.put("Quantity",String.valueOf(c.getQty()));
			output.put( "email",c.getEmail());
			int pId=cart.searchByCartId(c.getCartid()).getProdId();
			Product v=productRepo.findByprodId(pId);
			output.put("ProductName",v.getProdName());
			output.put("ProductPrice", String.valueOf(v.getProdPrice()));
			o.add(output);
		}
		return o;
	}
	public void removeProdFromCart(String email,String productName) {
		int prodId=productRepo.findByProdName(productName).getProdId();
		cart.removeProdFromCart(email, prodId);
	}
	public void updateQtyOfProdInCart(String email,String productName,int qty) {
		int prodId=productRepo.findByProdName(productName).getProdId();
		if(qty!=0)
			cart.updateQtyOfProdInCart(qty, email, prodId);
		else
			removeProdFromCart(email, productName);
	}
	
//	public String addToCart(Cart c) {
//	List<Integer> productList=cart.findByEmail(c.getEmail()).stream().map(c1->c1.getProdId()).collect(Collectors.toList());
//	if(productList.isEmpty())
//		cart.save(c);
//	else {
//		for(int product:productList) {
//			if(product==c.getProdId()) {
//				updateQtyofProduct(c);
//				break;
//			}
//			else {
//				cart.save(c);
//			}
//		}
//	}
//	return "Added To Cart";
//}
}