package com.example.commerce.service;

import com.example.commerce.entity.Seller;
import com.example.commerce.entity.User;
import com.example.commerce.repository.SellerRepository;
import com.example.commerce.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
    } //


    public User registerUser(@Valid User user) throws Exception {
        verifyEmailAndName(user.getEmail(), user.getName()); // 이메일과 이름 검증
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
        return userRepository.save(user);// 사용자 저장
    } //회원가입 (예외 던짐)

    public Seller registerSeller(@Valid Seller seller) throws Exception { //
        verifyEmailAndName(seller.getEmail(), seller.getName());
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        return sellerRepository.save(seller);
    } //판매자 등록(예외 던짐)

    private void verifyEmailAndName(String email, String name) throws Exception {
        if (userRepository.existsByEmail(email) || sellerRepository.existsByEmail(email)) {
            throw new Exception("Email is already in use.");
        }
        if (userRepository.existsByName(name) || sellerRepository.existsByName(name)) {
            throw new Exception("Name is already in use.");
        } // 사용자 중복 가입 방지
    }

}