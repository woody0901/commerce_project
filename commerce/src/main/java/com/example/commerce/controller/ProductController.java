package com.example.commerce.controller;

import com.example.commerce.entity.Product;
import com.example.commerce.entity.Seller;
import com.example.commerce.entity.Store;
import com.example.commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.getAllProductsSortedByCategory();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable Long id) {
        Optional<Product> product = productService.getProductDetails(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/store")
    public ResponseEntity<Store> getStoreInformation(@PathVariable Long id) {
        Optional<Product> product = productService.getProductDetails(id);
        if (product.isPresent()) {
            Seller seller = product.get().getSeller();
            Optional<Store> store = productService.getStoreBySeller(seller);
            return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}