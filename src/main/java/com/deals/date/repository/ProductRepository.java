package com.deals.date.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.deals.date.model.Product;

//Implementing a ProductRepository by extending JpaRepository for operations on Vegetable Class
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

			public List<Product> findByprodType(String prodType);
			
			Product findByProdName(String prodName);
			
			List<Product> findByProdPrice(int prodPrice);

			List<Product> findByProdPriceGreaterThan(int prodPrice);

			List<Product> findByProdPriceLessThan(int prodPrice);

			@Query("select a from Product a where a.prodPrice > ?1 and a.prodPrice < ?2")
			List<Product> findByProductPriceBetween(int prodPrice1,int prodPrice2);
			
			@Query("select a from Product a where a.prodPrice > 200  and a.prodPrice < 399")
			List<Product> findByProductPriceBetweenFirst();
			
			
			@Query("select a from Product a where a.prodPrice > 400 and a.prodPrice < 599")
			List<Product> findByProductPriceBetweenSecond();
			
			@Query("select a from Product a where a.prodPrice < 199")
			List<Product> findByProductPriceLessThan();
			
			@Query("select a from Product a where a.prodPrice > 600")
			List<Product> findByProductPriceGreaterThan();
			
			@Query("select a from Product a where a.prodType = 'Cakes'")
			List<Product> findByProductTypeCakes();
			
			@Query("select a from Product a where a.prodType = 'MobileCovers'")
			List<Product> findByProductTypeMobileCovers();
			
			@Query("select a from Product a where a.prodType = 'SoftToys'")
			List<Product> findByProductTypeSoftToys();
			
			@Query("select a from Product a where a.prodType = 'Frames'")
			List<Product> findByProductTypeFrames();
			
			@Query("select a from Product a where a.prodType = 'Mugs'")
			List<Product> findByProductTypeMugs();
			
			@Query("select a from Product a where a.prodType = 'GiftCards'")
			List<Product> findByProductTypeGiftCards();

			Product findByprodId(int id);

			List<Product> deleteByProdPrice(int ProdPrice);
			
			List<Product> deleteByProdPriceLessThan(int prodPrice);
			
			List<Product> deleteByProdPriceGreaterThan(int prodPrice);



			
			
			

			
}