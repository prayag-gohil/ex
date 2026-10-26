package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	//Add Product
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
		Product savedProduct = productRepository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}

	//Get All Products
	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct()
	{
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}
	
	//Paging and Sorting
	@GetMapping("/page")
	public ResponseEntity<Page<Product>> getProducts(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "productId") String sortBy)
	{
		Pageable pageable = PageRequest.of(page, size,Sort.by(sortBy));
		Page<Product> products = productRepository.findAll(pageable);
		return ResponseEntity.ok(products);
	}
	
	//Update Product
	/*
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id,@RequestBody Product productDetails)
	{
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent())
		{
			Product product = optionalProduct.get();
			product.setProductName(productDetails.getProductName());
			product.setCategory(productDetails.getCategory());
			product.setPrice(productDetails.getPrice());
			product.setQuantity(productDetails.getQuantity());
			Product updatedProduct = productRepository.save(product);
			return ResponseEntity.ok(updatedProduct);
		}
		return ResponseEntity.noContent().build();
	}
	*/
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(
	        @PathVariable Integer id,
	        @RequestBody Product productDetails) {

	    Optional<Product> optionalProduct =
	            productRepository.findById(id);

	    if (optionalProduct.isPresent()) {

	        Product product = optionalProduct.get();

	        if (productDetails.getProductName() != null) {
	            product.setProductName(productDetails.getProductName());
	        }

	        if (productDetails.getCategory() != null) {
	            product.setCategory(productDetails.getCategory());
	        }

	        if (productDetails.getPrice() != null) {
	            product.setPrice(productDetails.getPrice());
	        }

	        if (productDetails.getQuantity() != null) {
	            product.setQuantity(productDetails.getQuantity());
	        }

	        Product updatedProduct =
	                productRepository.save(product);

	        return ResponseEntity.ok(updatedProduct);
	    }

	    return ResponseEntity.notFound().build();
	}
	
	//Delete Product
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id)
	{
		if(productRepository.existsById(id))
		{
			productRepository.deleteById(id);
			return ResponseEntity.ok("Product Deleted Successfully");
		}
		return ResponseEntity.noContent().build();
	}

}
