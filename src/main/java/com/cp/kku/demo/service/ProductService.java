package com.cp.kku.demo.service;

import com.cp.kku.demo.model.Product;
import com.cp.kku.demo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setId(id); // Ensure the ID is set for updating
            return productRepository.save(updatedProduct);
        }
        return null;  // Return null or throw an exception if the product is not found
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
