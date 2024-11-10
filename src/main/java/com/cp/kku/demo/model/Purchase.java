package com.cp.kku.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String customerCompanyName;
    private String customerPhoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "purchase_product",
        joinColumns = @JoinColumn(name = "purchase_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )

    @JsonIgnore
    private List<Product> products = new ArrayList<>(); // Initialize products as ArrayList

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    public void updateProduct(Product product) {
        // Find the corresponding product in the list and update its details
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getId().equals(product.getId())) {
                this.products.get(i).setName(product.getName());
                this.products.get(i).setQuantity(product.getQuantity());
                this.products.get(i).setPrice(product.getPrice());
                break;
            }
        }
    }
}
