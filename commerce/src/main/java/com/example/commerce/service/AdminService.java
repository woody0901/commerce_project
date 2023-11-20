package com.example.commerce.service;

import com.example.commerce.repository.StoreRepository;
import com.example.commerce.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final StoreRepository storeRepository;

    @Autowired
    public AdminService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    public Store approveStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));
        store.setStatus(Store.Status.APPROVED);
        return storeRepository.save(store);
    } //상점 등록 승인

    public Store rejectStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));
        store.setStatus(Store.Status.REJECTED);
        return storeRepository.save(store);
    }


}