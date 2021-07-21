package com.deals.date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.deals.date.model.Admin;
import com.deals.date.model.Customer;
import com.deals.date.repository.AdminRepository;
import com.deals.date.repository.CustomerRepository;
import com.deals.date.service.AdminServiceImpl;
import com.deals.date.service.CustomerServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

	// Autowiring CustomerRepository repository
	@Autowired
	private CustomerServiceImp service;
	@MockBean
	private CustomerRepository repository;

	private String email;

//	// testing add customer method
//	@Test
//	public void addCustomer() {
//		Customer c = new Customer();
//		c.setEmail("abc@gmail.com");
//		c.setPassword("Abcd123");
//		c.setAddress("Mumbai");
//		c.setPhoneNo("8691928192");
//		c.setUsername("Abcdef");
//
//		Mockito.when(repository.save(c)).thenReturn(c);
//		assertThat(service.addCustomer(c)).isEqualTo(c);
//	}

	// testing get all customer method
	@Test
	public void viewAllCustomer() throws Exception {
		when(repository.findAll())
				.thenReturn(Stream.of(new Customer("abc@gmail.com", "Ashu", "Abcd345", "9876532456", "mumbai"))
						.collect(Collectors.toList()));
		assertEquals(1, service.viewAllCustomers().size());
	}

	// testing get customer by email method
	@Test
	public void viewCustomer() {
		Customer c = new Customer();
		c.setEmail("abc@gmail.com");
		c.setPassword("Abcd123");
		c.setAddress("Mumbai");
		c.setPhoneNo("8691928192");
		c.setUsername("Abcdef");

		Mockito.when(repository.findByEmail((String) email)).thenReturn(c);
		Mockito.when(repository.existsById(c.getEmail())).thenReturn(false);
		assertFalse(repository.existsById(c.getEmail()));

	}

	// testing update customer method
	@Test
	public void updateCustomer() {
		Customer c = new Customer();
		c.setEmail("abc@gmail.com");
		c.setPassword("Abcd123");
		c.setAddress("Mumbai");
		c.setPhoneNo("8691928192");
		c.setUsername("Abcdef");

		Mockito.when(repository.save(c)).thenReturn(c);

		assertThat(service.updateCustomer(c)).isEqualTo(c);
	}
	
//	// testing update customer method
//		@Test
//		public void updateCustomerByPhone() {
//			Customer cp = new Customer();
//			cp.setEmail("abc@gmail.com");
//			cp.setPassword("Abcd123");
//			cp.setAddress("Mumbai");
//			cp.setPhoneNo("8691928192");
//			cp.setUsername("Abc123");
//
//			Mockito.when(repository.save(cp)).thenReturn(cp);
//
//			assertThat(service.updateCustomerPhone("abc@gmail.com", "8691928193").isEqualTo(cp);
//		}

//	@Test
//	public void deleteCustomer()
//	{
//		Customer c= new Customer();
//		
//		Mockito.when(repository.findAll((long) 4)).thenReturn(c);
//		Mockito.when(repository.findByEmail(c.getEmail())).thenReturn(false);
//		assertFalse(repository.existsById(c.getTutorId()));
//		
//	}

}