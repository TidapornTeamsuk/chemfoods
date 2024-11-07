package com.cp.kku.demo.service;

import java.util.List;
import java.util.Optional;

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
}
