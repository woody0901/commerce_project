package com.example.commerce.controller;

import com.example.commerce.entity.Store;
import com.example.commerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/approve/{storeId}")
    public ResponseEntity<Store> approveStore(@PathVariable Long storeId) {
        Store store = adminService.approveStore(storeId);
        return ResponseEntity.ok(store);
    }

    @PostMapping("/reject/{storeId}")
    public ResponseEntity<Store> rejectStore(@PathVariable Long storeId) {
        Store store = adminService.rejectStore(storeId);
        return ResponseEntity.ok(store);
    }
}

