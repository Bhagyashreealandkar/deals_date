package com.deals.date.service;

import java.util.List;
import com.deals.date.model.Product;

//Defining an interface for the services of the Product class
public interface ProductService {

	// Defining all the methods for the operations on Product class
		public String addProduct(Product p);

		public List<Product> getProductList();

		public Product findProductById(int id);

		public Product getProductByName(String prodName);

		public List<Product> getProductByPrice(int prodPrice);

		public List<Product> greaterThanPrice(int price);

		public List<Product> getProductByType(String prod_type);
		
		public List<Product> lessThanPrice(int price);

		public Product updateProduct(Product prod);

		public List<Product> getProductByPriceBetween(int prodPrice1, int prodPrice2);
		
		public List<Product> getProductByPriceBetweenFirst();
		
		public List<Product> getProductByPriceBetweenSecond();
		
		public List<Product> getProductByPriceLessThan();
		
		public List<Product> getProductByPriceGreaterThan();
		
		public List<Product> getProductByTypeCakes();
		
		public List<Product> getProductByTypeMobileCovers();
		
		public List<Product> getProductByTypeSoftToys();
		
		public List<Product> getProductByTypeFrames();
		
		public List<Product> getProductByTypeMugs();
		
		public List<Product> getProductByTypeGiftCards();


		public String updateProductPrice(String name, int price);

		public String removeProduct(int id);

		public String deleteByProdName(String prodName);
		
		public List<Product> deleteByProdPrice(int prodPrice);
		
		public List<Product> deleteByProdPricelessThan(int prodPrice);
		
		public List<Product> deleteByProdPriceGreaterThan(int prodPrice);



		

	

		
		
}