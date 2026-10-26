package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	
	private final ProductService productService;

	public ProductRestController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	//Create Product
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product produdct)
	{
		Product savedProduct = productService.createProduct(produdct);
		return ResponseEntity.ok(savedProduct);
	}
	
	//Get All Products
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts()
	{
		List<Product> getProduct = productService.getAllProducts();
		return ResponseEntity.ok(getProduct);
	}
	
	//Get By Id
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id)
	{
		Product product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}
	
	//Update product
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id,@RequestBody Product productDetails)
	{
		Product updateProduct = productService.updateProduct(id,productDetails);
		if(updateProduct != null)
		{
			return ResponseEntity.ok(updateProduct);
		}
		return ResponseEntity.noContent().build();
	}
	
	//Delete Product
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id)
	{
		String message = productService.deleteProduct(id);
		if(message.equals("Product Deleted Successfully"))
		{
			return ResponseEntity.ok(message);
		}
		return ResponseEntity.noContent().build();
	}

}
