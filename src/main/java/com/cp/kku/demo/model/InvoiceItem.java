package com.cp.kku.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private BigDecimal unitPrice;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    // Default constructor
    public InvoiceItem() {}

    // Parameterized constructor
    public InvoiceItem(String productName, BigDecimal unitPrice, int quantity, Invoice invoice) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.invoice = invoice;
    }

    // Method to calculate total price for the item
    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
