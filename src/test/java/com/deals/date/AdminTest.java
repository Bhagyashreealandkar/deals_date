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
public class AdminTest {

	// Autowiring AdminRepository repository
	@Autowired
	private AdminServiceImpl service;
	@MockBean
	private AdminRepository repository;

	private String email;

	// testing add admin method
//	@Test
//	public void addAdmin() {
//		Admin a = new Admin();
//		a.setEmail("abc@gmail.com");
//		a.setPassword("Abcd123");
//		a.setPhoneNo("8691928192");
//
//		Mockito.when(repository.save(a)).thenReturn(a);
//		assertThat(service.addAdmin(a)).isEqualTo(a);
//	}

	// testing get all admin method
	@Test
	public void viewAllAdmin() throws Exception {
		when(repository.findAll()).thenReturn(
				Stream.of(new Admin("abc@gmail.com", "Abcd345", "9876532456")).collect(Collectors.toList()));
		assertEquals(1, service.viewAllAdmins().size());
	}

	// testing get admin by email method
	@Test
	public void viewAdmin() {
		Admin a = new Admin();
		a.setEmail("abc@gmail.com");
		a.setPassword("Abcd123");
		a.setPhoneNo("8691928192");

		Mockito.when(repository.findByEmail((String) email)).thenReturn(a);
		Mockito.when(repository.existsById(a.getEmail())).thenReturn(false);
		assertFalse(repository.existsById(a.getEmail()));
	}

	// testing update admin method
	@Test
	public void updateAdmin() {
		Admin a = new Admin();
		a.setEmail("abc@gmail.com");
		a.setPassword("Abcd123");
		a.setPhoneNo("8691928192");

		Mockito.when(repository.save(a)).thenReturn(a);
		assertThat(service.updateAdmin(a)).isEqualTo(a);

	}
	
	

//	@Test
//	public void deleteCustomer()
//	{
//		Admin a = new Admin();
//		Mockito.when(repository.findByEmail((String) email)).thenReturn(a);
//		Mockito.when(repository.findByEmail(a.getEmail())).thenReturn(false);
//		assertFalse(repository.existsById(a.getEmail()));		
//	}
	

}