package com.example.commerce.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SellerDto implements Serializable {
    private String email;
    private String name;
    private String password;
}