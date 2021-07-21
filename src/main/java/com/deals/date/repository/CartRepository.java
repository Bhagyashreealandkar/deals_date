package com.deals.date.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deals.date.model.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	List<Cart> findByEmail(String email);
	
	@Query("SELECT c FROM Cart c WHERE c.Cartid=:cartId")
	Cart searchByCartId(@Param("cartId") Integer cartId);
	@Transactional
	@Modifying
	@Query("DELETE FROM Cart c WHERE c.email=:email AND c.prodId=:prodId")
	void removeProdFromCart(@Param("email") String email,@Param("prodId") Integer prodId);
	@Transactional
	@Modifying
	@Query("DELETE FROM Cart c WHERE c.email=:email")
	void removeCart(@Param("email") String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE Cart c SET c.qty=:qty WHERE c.email=:email AND c.prodId=:prodId")
	void updateQtyOfProdInCart(@Param("qty") Integer qty,@Param("email") String email,@Param("prodId") Integer prodId);

	@Query("SELECT c.qty FROM Cart c WHERE c.email=:email AND c.prodId=:prodId")
	int findQtyFromCart(@Param("email") String email,@Param("prodId") Integer prodId);
}