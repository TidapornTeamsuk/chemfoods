package com.cp.kku.demo.repository;

import com.cp.kku.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
}
