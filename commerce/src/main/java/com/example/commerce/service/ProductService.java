package com.example.commerce.service;

import com.example.commerce.entity.Product;
import com.example.commerce.entity.Seller;
import com.example.commerce.entity.Store;
import com.example.commerce.repository.ProductRepository;
import com.example.commerce.repository.SellerRepository;
import com.example.commerce.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public List<Product> getAllProductsSortedByCategory() {
        return productRepository.findAllByOrderByCategoryAsc();
    }

    public Optional<Product> getProductDetails(Long productId) {
        return productRepository.findById(productId);
    }

    public Optional<Store> getStoreBySeller(Seller seller) {
        return storeRepository.findBySeller(seller);
    }
}

