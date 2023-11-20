package com.example.commerce.controller;

import com.example.commerce.entity.Product;
import com.example.commerce.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller-page")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<?> getSellerProfile(Authentication authentication) {
        String username = authentication.getName();
        if (sellerService.isSellerApproved(username)) {
            return ResponseEntity.ok("Seller profile information");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not approved to access this profile.");
        }
    }

    @PostMapping("/{sellerId}/product")
    public ResponseEntity<Product> createProduct(@PathVariable(value = "sellerId") Long sellerId,
                                                 @Valid @RequestBody Product product) {
        return ResponseEntity.ok(sellerService.createProduct(sellerId, product));
    }

    @PutMapping("/{sellerId}/product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "sellerId") Long sellerId,
                                                 @PathVariable(value = "productId") Long productId,
                                                 @Valid @RequestBody Product productDetails) {
        return ResponseEntity.ok(sellerService.updateProduct(sellerId, productId, productDetails));
    }

    @DeleteMapping("/{sellerId}/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "sellerId") Long sellerId,
                                              @PathVariable(value = "productId") Long productId) {
        sellerService.deleteProduct(sellerId, productId);
        return ResponseEntity.ok().build();
    }

}