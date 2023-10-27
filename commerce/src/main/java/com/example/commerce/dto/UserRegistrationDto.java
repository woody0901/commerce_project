package com.example.commerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRegistrationDto {
    private String email;
    private String name;
    private String password;
    private LocalDate birthdate;
    private String gender;

}
