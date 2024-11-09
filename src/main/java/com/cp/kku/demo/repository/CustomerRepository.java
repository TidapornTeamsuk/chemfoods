package com.cp.kku.demo.repository;

import com.cp.kku.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query methods can be added here if needed
}
