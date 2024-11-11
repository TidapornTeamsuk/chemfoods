package com.cp.kku.demo.service;

import java.util.List;
import java.util.Optional;

import com.cp.kku.demo.model.Product;
import com.cp.kku.demo.model.Purchase;
import com.cp.kku.demo.repository.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
	
	@Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchases() {
        return (List<Purchase>) purchaseRepository.findAll();
    }

    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Purchase updatePurchase(Long id, Purchase updatedPurchase) {
    	updatedPurchase.setId(id); // Ensure the ID is set for updating
        return purchaseRepository.save(updatedPurchase);
    }

    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

    public List<Purchase> searchPurchasesByCompanyName(String companyName) {
        return purchaseRepository.findByCustomerCompanyNameContainingIgnoreCase(companyName);
    }

    // ฟังก์ชันเพื่อดึงประวัติการซื้อทั้งหมดตาม companyId
    public List<Purchase> getPurchasesByCompanyId(Long companyId) {
        return purchaseRepository.findByCompanyId(companyId);
    }

    // ดึงรายการ Purchase ตามชื่อบริษัท (ค้นหาแบบใกล้เคียง)
    public List<Purchase> getPurchasesByCompanyName(String companyName) {
        return purchaseRepository.findByCustomerCompanyNameContainingIgnoreCase(companyName);
    }

    public List<Purchase> findByCompanyName(String companyName) {
        return purchaseRepository.findByCompanyNameContainingIgnoreCase(companyName); // การค้นหาจากชื่อบริษัทที่ตรงหรือใกล้เคียง
    }
}
