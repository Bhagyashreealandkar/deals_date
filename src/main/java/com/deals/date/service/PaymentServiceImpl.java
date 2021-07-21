package com.deals.date.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deals.date.model.Cart;
import com.deals.date.model.Customer;
import com.deals.date.model.Order;
import com.deals.date.model.Payment;
import com.deals.date.model.Product;
import com.deals.date.repository.CartRepository;
import com.deals.date.repository.CustomerRepository;
import com.deals.date.repository.OrderRepository;
import com.deals.date.repository.PaymentRepository;
import com.deals.date.repository.ProductRepository;

////implementing CustomerService interface
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	CustomerService service;
	@Autowired
	CartServiceImp cartService;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	ProductRepository prodRepository;
	@Autowired
	PaymentRepository payment;
	@Autowired
	OrderRepository order;

// make new payment

	@Override
	public String makePayment(String email) {
		Payment pay = new Payment();
		LocalDate d = LocalDate.now();
		List<Integer> ListProdId = cartRepo.findByEmail(email).stream().map(p -> p.getProdId())
				.collect(Collectors.toList());

		int totalAmt = 0;
		for (Integer p : ListProdId) {
			Product v = prodRepository.findByprodId(p);
			int prodPrice = v.getProdPrice();
			int qty = cartRepo.findQtyFromCart(email, p);
			totalAmt = totalAmt + prodPrice * qty;
		}
		pay.setEmail(email);
		pay.setPayDate(d);
		pay.setTotalAmount(totalAmt);

		payment.save(pay);
		for (Integer p : ListProdId) {
			Order or = new Order();
			int qty = cartRepo.findQtyFromCart(email, p);
			or.setEmail(email);
			or.setProductId(p);
			or.setQty(qty);
			List<Integer> ListOfId = payment.findPaymentByEmail(email);
			int max = Collections.max(ListOfId);
			or.setPayId(max);
			or.setPayDate(d);
			order.save(or);
		}
		cartRepo.removeCart(email);
		return "Payment successful";
	}

	@Override
	public List<Map<String, String>> viewMyListOfProds(String email) {
		List<Cart> listCart = cartRepo.findByEmail(email);
		List<Map<String, String>> o = new ArrayList<>();
		for (Cart c : listCart) {
			Map<String, String> output = new HashMap<String, String>();
			output.put("Quantity", String.valueOf(c.getQty()));
			output.put("email", c.getEmail());
			int pId = cartRepo.searchByCartId(c.getCartid()).getProdId();
			Product p = prodRepository.findByprodId(pId);
			output.put("ProdName", p.getProdName());
			output.put("prodPrice", String.valueOf(p.getProdPrice()));
			o.add(output);
		}
		return o;
	}
}