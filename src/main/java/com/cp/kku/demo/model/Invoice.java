package com.cp.kku.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private LocalDate invoiceDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items;

    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal totalWithTax;
    private String paymentStatus;

    // Default constructor
    public Invoice() {}

    // Parameterized constructor
    public Invoice(String invoiceNumber, LocalDate invoiceDate, Customer customer,
                   List<InvoiceItem> items, BigDecimal tax, String paymentStatus) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customer = customer;
        this.items = items;
        this.tax = tax;
        this.paymentStatus = paymentStatus;
        this.totalAmount = calculateTotalAmount();
        this.totalWithTax = totalAmount.add(tax != null ? tax : BigDecimal.ZERO);
    }

    // Method to calculate total amount of items
    public BigDecimal calculateTotalAmount() {
        return items.stream()
                .map(InvoiceItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
        this.totalAmount = calculateTotalAmount();
        this.totalWithTax = totalAmount.add(tax != null ? tax : BigDecimal.ZERO);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
        this.totalWithTax = totalAmount.add(tax != null ? tax : BigDecimal.ZERO);
    }

    public BigDecimal getTotalWithTax() {
        return totalWithTax;
    }

    public void setTotalWithTax(BigDecimal totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
