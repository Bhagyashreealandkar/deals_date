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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.deals.date.model.Product;
import com.deals.date.repository.ProductRepository;
import com.deals.date.service.ProductServiceImpl;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductServiceImpl service;
    
    @MockBean
    private ProductRepository repository;
    
    private Integer prodId;
    
    @Test
    public void testListProduct() 
        {
    	  when(repository.findAll()).thenReturn(Stream.of(new Product(1,"Chocolate Cake","Cake",350)).collect(Collectors.toList()));
    	  assertEquals(1,service.getProductList().size()); 
  	    }
    
    @Test
	public void testUpdateProduct() {
    	Product product=new Product();
    	product.setProdName("Chocolate Cake");
    	product.setProdType("Cakes");
    	product.setProdPrice(350);
		
		Mockito.when(repository.save(product)).thenReturn(product);
		 assertThat(service.updateProduct(product)).isEqualTo(product);
	
	}
    
//    @Test
//	public void testAddProduct() {
//    	Product product=new Product();
//    	product.setProdName("Chocolate Cake");
//    	product.setProdType("Cakes");
//    	product.setProdPrice(350);
//		
//		Mockito.when(repository.save(product)).thenReturn(product);
//		 assertThat(service.addProduct(product)).isEqualTo(product);
//	
//	}
    
    @Test
	public void testGetByIdProduct() {
    	Product product=new Product();
		product.setProdId(4);
		product.setProdName("Chocolate Cake");
    	product.setProdType("Cakes");
    	product.setProdPrice(350);
		
		Mockito.when(repository.getOne((int) 4)).thenReturn(product);
		Mockito.when(repository.existsById(product.getProdId())).thenReturn(false);
		assertFalse(repository.existsById(product.getProdId()));
	
	}
    

  }
    
    
     
    
