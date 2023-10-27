package com.example.commerce.service;

import com.example.commerce.dto.LoginDto;
import com.example.commerce.dto.UserRegistrationDto;
import com.example.commerce.entity.User;
import com.example.commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserRegistrationDto registrationDto) throws Exception {
        if (userRepository.existsByEmail(registrationDto.getEmail()) ||
                userRepository.existsByName(registrationDto.getName())) {
            throw new Exception("Email or Name already exists!");
        }

        User newUser = new User();
        newUser.setEmail(registrationDto.getEmail());
        newUser.setName(registrationDto.getName());
        newUser.setPassword(registrationDto.getPassword()); // 비밀번호 암호화 로직 추가하기
        newUser.setBirthdate(registrationDto.getBirthdate());
        newUser.setGender(registrationDto.getGender());

        return userRepository.save(newUser);
    }

    public User validateUser(LoginDto loginDto) throws Exception {
        User existingUser = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new Exception("User does not exist!"));

        if (!existingUser.getPassword().equals(loginDto.getPassword())) { // 비밀번호 암호화 로직 추가하기
            throw new Exception("Invalid password!");
        }

        return existingUser;
    }
}