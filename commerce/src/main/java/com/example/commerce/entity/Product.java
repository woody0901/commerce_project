package com.example.commerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Category category;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Min(0)
    private double price;

    @NotNull
    @Size(min = 1, max = 255)
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public enum Category {
        CLOTHING, FOOD, BOOK
    }

}