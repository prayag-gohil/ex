package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	@Override
	public Product createProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	@Override
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Integer id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product updateProduct(Integer id,Product productDetails) {
		Optional<Product> optinalProduct = productRepository.findById(id);
		if(optinalProduct.isPresent())
		{
			Product product = optinalProduct.get();
			product.setProductName(productDetails.getProductName());
			product.setCategory(productDetails.getCategory());
			product.setPrice(productDetails.getPrice());
			product.setQuantity(productDetails.getQuantity());
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public String deleteProduct(Integer id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent())
		{
			productRepository.deleteById(id);
			return "Product Deleted Successfully";
		}
		return "Product not found";
	}

}
