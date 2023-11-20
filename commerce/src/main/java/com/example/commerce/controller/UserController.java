package com.example.commerce.controller;


import com.example.commerce.entity.Seller;
import com.example.commerce.entity.User;
import com.example.commerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/seller/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody @Valid Seller seller) {
        try {
            Seller newSeller = userService.registerSeller(seller);
            return ResponseEntity.ok(newSeller);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}