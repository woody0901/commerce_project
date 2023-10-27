package com.example.commerce.service;

import com.example.commerce.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// AdminService.java
@Service
public class AdminService {
    private final StoreRepository storeRepository;

    @Autowired
    public AdminService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

}