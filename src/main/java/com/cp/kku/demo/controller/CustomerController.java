package com.cp.kku.demo.controller;

import com.cp.kku.demo.model.Customer;
import com.cp.kku.demo.model.Product;
import com.cp.kku.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Display all customers
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "listCustomer";
    }

    @GetMapping("/{id}")
    public String showCustomerDetails(@PathVariable("id") Long id, Model model) {
        // ดึงข้อมูลลูกค้าจาก service หรือ repository
        Optional<Customer> customer = customerService.getCustomerById(id);

        // ถ้าลูกค้าไม่พบ ให้แสดง error หรือ redirect ไปยังหน้าอื่น
        if (customer.isEmpty()) {
            return "redirect:/customers";  // หรือส่ง error ไปแสดงในหน้าจอ
        }

        model.addAttribute("customer", customer);
        return "showCustomer";  // ชื่อของ HTML template ที่จะแสดง
    }

    // Show the form to add a new customer
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }

    // Add a new customer
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    // Show the form to edit an existing customer
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id).orElse(new Customer()));
        return "editCustomer";
    }

    // Update customer details
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers";
    }

    // Delete a customer
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
