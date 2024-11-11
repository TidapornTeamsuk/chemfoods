package com.cp.kku.demo.repository;

import com.cp.kku.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.kku.demo.model.Purchase;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    // ค้นหาประวัติการซื้อจาก companyId
    List<Purchase> findByCompanyId(Long id);


    // ค้นหาประวัติการซื้อจากชื่อบริษัทใน Customer (ใช้ชื่อบริษัทที่อยู่ใน Customer)
//    List<Purchase> findByCustomerCompanyNameContainingIgnoreCase(String companyName);

//    public List<Purchase> findByCompanyNameContainingIgnoreCase(String companyName);
    List<Purchase> findByCompanyNameContainingIgnoreCase(String companyName);

}