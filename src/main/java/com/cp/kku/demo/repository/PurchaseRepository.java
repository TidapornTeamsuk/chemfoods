package com.cp.kku.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.kku.demo.model.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
