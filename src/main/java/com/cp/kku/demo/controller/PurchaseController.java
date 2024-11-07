package com.cp.kku.demo.controller;

import com.cp.kku.demo.model.Product;
import com.cp.kku.demo.model.Purchase;
import com.cp.kku.demo.repository.ProductRepository;
import com.cp.kku.demo.service.ProductService;
import com.cp.kku.demo.service.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    // Show all purchases
    @GetMapping
    public String listPurchases(Model model) {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        model.addAttribute("purchases", purchases);
        return "listPurchases";  // View for listing all purchases
    }

    // Show details of a specific purchase
    @GetMapping("/{id}")
    public String showPurchase(@PathVariable Long id, Model model) {
        Optional<Purchase> purchase = purchaseService.getPurchaseById(id);
        if (purchase.isPresent()) {
            model.addAttribute("purchase", purchase.get());
            return "showPurchase";  // View for showing purchase details
        }
        return "error";  // Redirect to error page if purchase is not found
    }

    // Show form to create a new purchase
    @GetMapping("/new")
    public String createPurchaseForm(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);  // ส่ง products ไปยัง view

        model.addAttribute("purchase", new Purchase());
        return "createPurchase";  // View for creating a new purchase
    }

    // Save a new purchase
    @PostMapping
    public String savePurchase(@ModelAttribute Purchase purchase) {
        purchaseService.savePurchase(purchase); // Save purchase, which will cascade to products
        return "redirect:/purchases";  // Redirect to the list of purchases after saving
    }
    
    // Show form to edit an existing purchase
    @GetMapping("/edit/{id}")
    public String editPurchaseForm(@PathVariable Long id, Model model) {
        Optional<Purchase> purchase = purchaseService.getPurchaseById(id);
        if (purchase.isPresent()) {
            model.addAttribute("purchase", purchase.get());
            return "editPurchase";  // View for editing an existing purchase
        }
        return "error";  // Redirect to error page if purchase is not found
    }

    // Update an existing purchase
    @PostMapping("/update/{id}")
    public String updatePurchase(@PathVariable("id") Long id, @ModelAttribute Purchase purchase) {
        purchaseService.updatePurchase(id, purchase);
        return "redirect:/purchases";  // Redirect to the updated purchase details
    }


    // Delete a purchase
    @GetMapping("/delete/{id}")
    public String deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return "redirect:/purchases";  // Redirect to the list of purchases after deletion
    }
    
 // API สำหรับค้นหาผลิตภัณฑ์
    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> searchProducts(@RequestParam("term") String term) {
        return productRepository.findByNameContainingIgnoreCase(term); // ค้นหาจากชื่อผลิตภัณฑ์
    }
    
}