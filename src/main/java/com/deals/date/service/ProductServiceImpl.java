package com.deals.date.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.deals.date.model.Product;
import com.deals.date.repository.ProductRepository;
import com.deals.date.service.ProductService;
import com.deals.date.model.Product;


//Implementing the class as ProductsServiceImpl and implementing the interface
@Service
public class ProductServiceImpl implements ProductService {


	// Autowiring the ProductsRepository
	@Autowired
	ProductRepository productRepo;
	
	   // Method to add Products into table
		@Override
		public String addProduct(Product p) {
			 productRepo.save(p);		
			return p.getProdName() + " added successfully!!";
		}
		
	   // Method to view all Product from table
		@Override
		public List<Product> getProductList() {
			return (List<Product>) productRepo.findAll();
		}
	
		// Method to view Product by name from table
		@Override
		public Product getProductByName(String prodName) {
			return productRepo.findByProdName(prodName);
		}
	
		@Override
		public List<Product> getProductByPrice(int prodPrice) {
			return productRepo.findByProdPrice(prodPrice);
		}
		
		// Method to view Products greater than given price
		@Override
		public List<Product> greaterThanPrice(int price) {
			return productRepo.findByProdPriceGreaterThan(price);
		}
		
		// Method to view Products less than given price
		@Override
		public List<Product> lessThanPrice(int price) {
			return productRepo.findByProdPriceLessThan(price);
		}

		// Method to view Products by Id from table
		@Override
		public Product findProductById(int id) {
			return productRepo.findByprodId(id);
		}

		@Override
		public List<Product> getProductByType(String prodType) {
			return productRepo.findByprodType(prodType);

		}
		
		// Method to view products between given price
		public List<Product> getProductByPriceBetween(int prodPrice1, int prodPrice2) {
			return productRepo.findByProductPriceBetween(prodPrice1, prodPrice2).isEmpty() ? null
					: productRepo.findByProductPriceBetween(prodPrice1, prodPrice2);
		}

		// Method to view products between given price
		public List<Product> getProductByPriceBetweenFirst() {
			return productRepo.findByProductPriceBetweenFirst().isEmpty() ? null
					: productRepo.findByProductPriceBetweenFirst();
		}

		// Method to view Product between given price
		public List<Product> getProductByPriceBetweenSecond() {
			return productRepo.findByProductPriceBetweenSecond().isEmpty() ? null
					: productRepo.findByProductPriceBetweenSecond();
		}

		// Method to view Product between given price
		public List<Product> getProductByPriceLessThan() {
			return productRepo.findByProductPriceLessThan().isEmpty() ? null
					: productRepo.findByProductPriceLessThan();
		}

		// Method to view Product between given price
		public List<Product> getProductByPriceGreaterThan() {
			return productRepo.findByProductPriceGreaterThan().isEmpty() ? null
					: productRepo.findByProductPriceGreaterThan();
		}
		
		@Override
		public List<Product> getProductByTypeCakes() {
			return productRepo.findByProductTypeCakes().isEmpty() ? null
					: productRepo.findByProductTypeCakes();
		}
		
		@Override
		public List<Product> getProductByTypeMobileCovers() {
			return productRepo.findByProductTypeMobileCovers().isEmpty() ? null
					: productRepo.findByProductTypeMobileCovers();
		}

		@Override
		public List<Product> getProductByTypeSoftToys() {
			return productRepo.findByProductTypeSoftToys().isEmpty() ? null
					: productRepo.findByProductTypeSoftToys();
		}

		@Override
		public List<Product> getProductByTypeFrames() {
			return productRepo.findByProductTypeFrames().isEmpty() ? null
					: productRepo.findByProductTypeFrames();
		}

		@Override
		public List<Product> getProductByTypeMugs() {
			return productRepo.findByProductTypeMugs().isEmpty() ? null
					: productRepo.findByProductTypeMugs();
		}

		
		@Override
		public List<Product> getProductByTypeGiftCards() {
			return productRepo.findByProductTypeGiftCards().isEmpty() ? null
					: productRepo.findByProductTypeGiftCards();
		}


		// Method to update Product from table
		@Override
		public Product updateProduct(Product prod) {
			Product p = productRepo.save(prod);
			return p;
			}
		
		// Method to update Product price in table for given Product name
		@Override
		public String updateProductPrice(String name, int price) {
			Product prod = productRepo.findByProdName(name);
			prod.setProdPrice(price);
			productRepo.save(prod);
			return "Product price updated Successfully!!";
		}

        
		// Method to delete Product from table
	
		@Override
		public String removeProduct(int id) {
			Product prod = productRepo.findById(id).get();

			if (prod == null)
				return "Product with email " + id + " not found";

			else {
				productRepo.delete(prod);
				return "product deleted successfully!!";
			}
		}

		// Method to delete Product by Product name
		@Override
		public String deleteByProdName(String prodName) {
			Product prod = productRepo.findByProdName(prodName);

			if (prod == null)
				return "Product with email " + prodName + " not found";

			else {
				productRepo.delete(prod);
				return "Product deleted successfully!!";
			}
		}

		// Method to delete Product by Product price
		@Override
		public List<Product> deleteByProdPrice(int prodPrice) {
			List<Product> prod = productRepo.deleteByProdPrice(prodPrice);
			return prod;
		}

		// Method to delete Product which is less than given price
		@Override
		public List<Product> deleteByProdPricelessThan(int prodPrice) {
			return productRepo.deleteByProdPriceLessThan(prodPrice);

		}

		// Method to delete Product which is greater than given price
		@Override
		public List<Product> deleteByProdPriceGreaterThan(int prodPrice) {
			return productRepo.deleteByProdPriceGreaterThan(prodPrice);
		}

		
		

		

}