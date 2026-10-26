package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/batch")
    public List<Product> addProduct(@RequestBody List<Product> products)
    {
        return productRepository.saveAll(products);
    }

    @GetMapping
    public Iterable<Product> showProduct()
    {
        return productRepository.findAll();
    }

    @GetMapping("/sorted")
    public List<Product> getSortedProducts(@RequestParam String sortBy)
    {
        return productRepository.findAll(Sort.by(sortBy));
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Integer productId,@RequestBody Product product)
    {
        product.setProductId(productId);
        return productRepository.save(product);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Integer productId)
    {
        productRepository.deleteById(productId);
        return "Product Deleted Successfully";
    }

}
