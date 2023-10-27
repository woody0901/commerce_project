package com.example.commerce.service;

import com.example.commerce.repository.SellerRepository;
import com.example.commerce.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository, StoreRepository storeRepository) {
        this.sellerRepository = sellerRepository;
        this.storeRepository = storeRepository;
    }

}
