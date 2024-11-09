package com.cp.kku.demo.repository;

import com.cp.kku.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.kku.demo.model.Purchase;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    List<Purchase> findByCustomerCompanyNameContainingIgnoreCase(String companyName);
}
