package com.example.commerce.service;

import com.example.commerce.entity.Product;
import com.example.commerce.entity.Seller;
import com.example.commerce.entity.Store;
import com.example.commerce.exception.ResourceNotFoundException;
import com.example.commerce.repository.ProductRepository;
import com.example.commerce.repository.SellerRepository;
import com.example.commerce.repository.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class SellerService {

    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SellerService(StoreRepository storeRepository , SellerRepository sellerRepository ,ProductRepository productRepository) {
        this.storeRepository = storeRepository;this.sellerRepository = sellerRepository;this.productRepository = productRepository;
    }

    public String checkStoreStatus(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new RuntimeException("Store not found"));
        switch(seller.getStatus()) {
            case PENDING:
                return "상점등록을 검토중입니다.";
            case APPROVED:
                return "상품등록이 완료되었습니다.";
            case REJECTED:
                return "상품등록이 거절되었습니다.";
            default:
                return "알 수 없는 상태";
        }
    } //상점 등록 상태 여부

    public boolean isSellerApproved(String username) {
        Seller seller = sellerRepository.findByUsername(username);
        return seller != null && seller.isApproved();
    }


    // 상품 등록 서비스
    public Product createProduct(Long sellerId, Product product) {
        return sellerRepository.findById(sellerId).map(seller -> {
            product.setSeller(seller);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Seller not found with id " + sellerId));
    }

    // 상품 수정 서비스
    public Product updateProduct(Long sellerId, Long productId, Product productDetails) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new ResourceNotFoundException("Seller not found with id " + sellerId);
        }

        return productRepository.findById(productId).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setManufacturer(productDetails.getManufacturer());
            product.setCategory(productDetails.getCategory());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }

    // 상품 삭제 서비스
    public void deleteProduct(Long sellerId, Long productId) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new ResourceNotFoundException("Seller not found with id " + sellerId);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        productRepository.delete(product);
    }

}