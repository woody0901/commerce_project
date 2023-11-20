package com.example.commerce.repository;

import com.example.commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Product.Category category);
    List<Product> findBySellerIdAndCategory(Long sellerId, Product.Category category);
    List<Product> findAllByOrderByCategoryAsc();
}