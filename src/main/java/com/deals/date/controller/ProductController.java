package com.deals.date.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.deals.date.exception.IllegalFormatException;
import com.deals.date.exception.NotFoundException;
import com.deals.date.exception.ProductExistException;
import com.deals.date.model.Product;
import com.deals.date.service.ProductService;

//Defining a Rest Controller and mapping it to URL
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {
	// Autowiring service class to get all related methods
	@Autowired
	ProductService service;

	// Post method to add Product to the table
	@PostMapping("/addproduct")
	public ResponseEntity<String> addProduct(@Valid @RequestBody Product p, Errors errors)
			throws IllegalFormatException, ProductExistException {

		// Validating input
		if (errors.hasFieldErrors("prodName")) {
		throw new IllegalFormatException("Product name cannot be empty or Invalid input!!");
		}
		if (errors.hasFieldErrors("prodPrice")) {
			throw new IllegalFormatException("Product price cannot be empty!!");
		}			
		if (errors.hasFieldErrors("prodType")) {
			throw new IllegalFormatException("Product Type cannot be empty or Invalid input!!");
		}

		// Checking whether the product exists
		if (service.getProductByName(p.getProdName()) != null) {
			// throwing exception if Product exist
			throw new ProductExistException("Product already exists!!");
		} else {
			String response = service.addProduct(p);
			if (response == null) {
				return new ResponseEntity<String>("Product not added", HttpStatus.FORBIDDEN);
			} else {
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}
		}
	}

	// Get method to view all Product
	@GetMapping(value = "/product")
	public ResponseEntity<List<Product>> getProductList() throws NotFoundException {
		List<Product> getProductList = service.getProductList();
		if (getProductList.isEmpty())
			// throwing exception if Product list is not found
			throw new NotFoundException("There are no product yet!!");
		else
			return new ResponseEntity<List<Product>>(getProductList, HttpStatus.OK);
	}

	// Get method to view Product by ProdName
	@GetMapping(value = "/getProdByName/{prodName}")
	public ResponseEntity<Product> getProductByname(@PathVariable("prodName") String ProdName)
			throws NotFoundException {
		Product prod = service.getProductByName(ProdName);
		if (prod == null)
			// throwing exception if Product with given name is not found
			throw new NotFoundException("No Product with name : " + ProdName + " found!!");
		else
			return new ResponseEntity<Product>(prod, HttpStatus.OK);
	}

	@GetMapping(value = "/getProdByType/{prodType}")
	public ResponseEntity<List<Product>> getProductByType(@PathVariable("prodType") String ProdType)
			throws NotFoundException {
		List<Product> prod = service.getProductByType(ProdType);
		if (prod.isEmpty())
			throw new NotFoundException("No Product in type : " + ProdType + " found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Product by ProdPrice
	@GetMapping(value = "/getProdByPrice/{ProdPrice}")
	public ResponseEntity<List<Product>> getProductByPrice(@PathVariable("ProdPrice") Integer ProdPrice)
			throws NotFoundException {
		List<Product> prod = service.getProductByPrice(ProdPrice);
		if (prod.isEmpty())
			// throwing exception if Product with given name is not found
			throw new NotFoundException("No Product with price : " + ProdPrice + " found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Product greater than given price
	@GetMapping("/greaterThan/{price}")
	public ResponseEntity<List<Product>> greaterThanPrice(@PathVariable("price") int price) throws NotFoundException {
		List<Product> prod = service.greaterThanPrice(price);
		if (prod.isEmpty())
			// throw exception if no Product id greater than given price
			throw new NotFoundException("No Products are greater than : " + price + " found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Product less than given price
	@GetMapping("/lessThan/{price}")
	public ResponseEntity<List<Product>> lessThanPrice(@PathVariable("price") int price) throws NotFoundException {
		List<Product> prod = service.lessThanPrice(price);
		if (prod.isEmpty())
			// throw exception if no Product id less than given price
			throw new NotFoundException("No Products are less than : " + price + " found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products between two given prices
	@GetMapping("/getByProductPriceBetween/{prodPrice1}/{prodPrice2}")
	public ResponseEntity<List<Product>> getprodsByPriceBetween(@PathVariable("prodPrice1") Integer prodPrice1,
			@PathVariable("prodPrice2") Integer prodPrice2) throws NotFoundException {
		List<Product> prod = service.getProductByPriceBetween(prodPrice1, prodPrice2);
		if (prod == null)
			throw new NotFoundException("No Products are between " + prodPrice1 + " and " + prodPrice2 + " found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products between 200 to 399
	@GetMapping("/product/getByProductPriceBetween200to399")
	public ResponseEntity<List<Product>> getprodsByPriceBetweenFirst() throws NotFoundException {
		List<Product> prod = service.getProductByPriceBetweenFirst();
		if (prod == null)
			throw new NotFoundException("No Products are between 200 to 399 found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products between 400 to 599
	@GetMapping("/product/getByProductPriceBetween400to599")
	public ResponseEntity<List<Product>> getprodsByPriceBetweenSecond() throws NotFoundException {
		List<Product> prod = service.getProductByPriceBetweenSecond();
		if (prod == null)
			throw new NotFoundException("No Products are between 400 to 599 found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products less than 199
	@GetMapping("/product/getByProductPriceLessThan199")
	public ResponseEntity<List<Product>> getprodsByPriceLessthan() throws NotFoundException {
		List<Product> prod = service.getProductByPriceLessThan();
		if (prod == null)
			throw new NotFoundException("No Products are less than 199 found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products greater than 600
	@GetMapping("/product/getByProductPriceGreaterThan600")
	public ResponseEntity<List<Product>> getprodsByPricegreaterThan() throws NotFoundException {
		List<Product> prod = service.getProductByPriceGreaterThan();
		if (prod == null)
			throw new NotFoundException("No Products are greater than 600 found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products type Cakes
	@GetMapping("/product/getByProductTypeCakes")
	public ResponseEntity<List<Product>> getprodsByTypeCakes() throws NotFoundException {
		List<Product> prod = service.getProductByTypeCakes();
		if (prod == null)
			throw new NotFoundException("No Products type Cakes found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products type MobileCovers
	@GetMapping("/product/getByProductTypeMobileCovers")
	public ResponseEntity<List<Product>> getprodsByTypeMobileCovers() throws NotFoundException {
		List<Product> prod = service.getProductByTypeMobileCovers();
		if (prod == null)
			throw new NotFoundException("No Products type MobileCovers found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products type SoftToys
	@GetMapping("/product/getByProductTypeSoftToys")
	public ResponseEntity<List<Product>> getprodsByTypeSoftToys() throws NotFoundException {
		List<Product> prod = service.getProductByTypeSoftToys();
		if (prod == null)
			throw new NotFoundException("No Products type SoftToys found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products type Frames
	@GetMapping("/product/getByProductTypeFrames")
	public ResponseEntity<List<Product>> getprodsByTypeFrames() throws NotFoundException {
		List<Product> prod = service.getProductByTypeFrames();
		if (prod == null)
			throw new NotFoundException("No Products type Frames found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products type Mugs
	@GetMapping("/product/getByProductTypeMugs")
	public ResponseEntity<List<Product>> getprodsByTypeMugs() throws NotFoundException {
		List<Product> prod = service.getProductByTypeMugs();
		if (prod == null)
			throw new NotFoundException("No Products type Mugs found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Get method to view Products type GiftCards
	@GetMapping("/product/getByProductTypeGiftCards")
	public ResponseEntity<List<Product>> getprodsByTypeGiftCards() throws NotFoundException {
		List<Product> prod = service.getProductByTypeGiftCards();
		if (prod == null)
			throw new NotFoundException("No Products type GiftCards found!!");
		else
			return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	// Patch method to update Product price for given product name
	@PatchMapping("/updateProd/{prodName}/{prodPrice}")
	private String updateCustomerPhone(@PathVariable("prodName") String prodName,
			@PathVariable("prodPrice") int prodPrice) throws IllegalFormatException, NotFoundException {

		if (service.getProductByName(prodName) == null) {
			// throwing exception if Product is not found
			throw new NotFoundException("Product not found!!");
		} else
			return service.updateProductPrice(prodName, prodPrice);
	}

	// Put method to update all fields in the table
//		@PutMapping(value = "/product/{prodId}")
//		public String updateProduct(@RequestBody Product product) {
//			return service.updateProduct(product);
//		}

	@PutMapping("/product/{prodId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer prodId, @RequestBody Product product)
			throws NotFoundException {

		Product p = service.findProductById(prodId);
		p.setProdName(product.getProdName());
		p.setProdPrice(product.getProdPrice());
		p.setProdType(product.getProdType());
		return new ResponseEntity<>(service.updateProduct(p), HttpStatus.OK);

	}

	// Delete product by product name
	@DeleteMapping(value = "/deleteByName/{prodName}")
	public String deleteByName(@PathVariable("prodName") String prodName) throws NotFoundException {
		if (service.getProductByName(prodName) == null) {
			// throwing exception if Product is not found
			throw new NotFoundException("Product not found!!");
		} else
			return service.deleteByProdName(prodName);
	}

	// Delete method to delete product from table with given Id
	@DeleteMapping(value = "/product/{prodId}")
	public ResponseEntity<String> deleteById(@PathVariable("prodId") int id) throws NotFoundException {

		if (service.findProductById(id) == null) {
			// throwing exception if Product is not found
			throw new NotFoundException("Product not found!!");
		} else {
			String response = service.removeProduct(id);
			if (response == null) {
				return new ResponseEntity<String>("Product not deleted!", HttpStatus.FORBIDDEN);
			} else {
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}
		}
	}

	// Delete method to delete all product from table with given price
	@DeleteMapping(value = "/deleteByProdPrice/{prodPrice}")
	public ResponseEntity<String> deleteByPrice(@PathVariable("prodPrice") int prodPrice) {
		List<Product> response = service.deleteByProdPrice(prodPrice);
		if (response.isEmpty())
			return new ResponseEntity<String>("Product not found!", HttpStatus.FORBIDDEN);
		else
			return new ResponseEntity<String>("Product deleted", HttpStatus.OK);

	}

	// Delete method to delete all Product less than given price
	@DeleteMapping("/deleteLessThan/{price}")
	public ResponseEntity<String> deletelessThanPrice(@PathVariable("price") int price) {
		List<Product> response = service.deleteByProdPricelessThan(price);
		if (response.isEmpty())
			return new ResponseEntity<String>("Product not found!", HttpStatus.FORBIDDEN);
		else
			return new ResponseEntity<String>("Products deleted!", HttpStatus.OK);
	}

	// Delete method to delete all Product greater than given price
	@DeleteMapping("/deleteGreaterThan/{price}")
	public ResponseEntity<String> deleteGreaterThanPrice(@PathVariable("price") int price) {
		List<Product> response = service.deleteByProdPriceGreaterThan(price);
		if (response.isEmpty())
			return new ResponseEntity<String>("Product not found!", HttpStatus.FORBIDDEN);
		else
			return new ResponseEntity<String>("Products deleted!", HttpStatus.OK);
	}

}