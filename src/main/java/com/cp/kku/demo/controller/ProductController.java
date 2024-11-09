package com.cp.kku.demo.controller;

import com.cp.kku.demo.model.Product;
import com.cp.kku.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Show all products
//    @GetMapping
//    public String listProducts(Model model) {
//        List<Product> products = productService.getAllProducts();
//        model.addAttribute("products", products);
//        return "listProducts";  // View for listing all products
//    }

    @GetMapping
    public String getProductList(@RequestParam(value = "search", required = false) String searchTerm, Model model) {
        List<Product> products;

        if (searchTerm != null && !searchTerm.isEmpty()) {
            products = productService.searchProducts(searchTerm);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("searchTerm", searchTerm);  // ส่งค่าคำค้นหากลับไปแสดงใน input
        return "listProducts";
    }

    // Show details of a specific product
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "showProduct";  // View for showing product details
        }
        return "error";  // Redirect to error page if product is not found
    }

    // Show form to create a new product
    @GetMapping("/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";  // View for creating a new product
    }

    // Save a new product
    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product); // Save product
        return "redirect:/products";  // Redirect to the list of products after saving
    }
    
    // Show form to edit an existing product
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "editProduct";  // View for editing an existing product
        }
        return "error";  // Redirect to error page if product is not found
    }

    // Update an existing product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()) {
            existingProduct.get().updateProduct(product.getName(), product.getQuantity(), product.getPrice());
            productService.saveProduct(existingProduct.get());
        }
        return "redirect:/products";
    }

    // Delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";  // Redirect to the list of products after deletion
    }
}
