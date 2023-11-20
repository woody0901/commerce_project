package com.example.commerce.repository;

import com.example.commerce.entity.Seller;
import com.example.commerce.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllBySellerId(Long sellerId);
    boolean existsBySellerId(Long sellerId);

    Optional<Store> findBySeller(Seller seller);
}