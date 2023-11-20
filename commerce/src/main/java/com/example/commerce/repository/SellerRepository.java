package com.example.commerce.repository;

import com.example.commerce.entity.Seller;
import com.example.commerce.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    // 이메일을 사용하여 판매자를 찾는 메소드입니다.
    Optional<Seller> findByEmail(String email);

    Seller findByUsername(String username);

    List<Store> findAllById(Long sellerId);
    boolean existsById(Long sellerId);

    // 이메일로 판매자가 존재하는지 확인.
    boolean existsByEmail(String email);

    // 이름으로 판매자가 존재하는지 확인.
    boolean existsByName(String name);

    // 예시: 상태로 판매자 목록 조회
    List<Seller> findByStatus(Seller.Status status);


}