package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @Column(name = "product_id", nullable = false, updatable = false)
    private String id;

    @Column(name = "product_title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "material_type", nullable = false)
    private String materialType;

    @Column(name = "care_instruction", nullable = false)
    private String careInstruction;

    @Column(name = "length", nullable = false)
    private double length;

    @Column(name = "width", nullable = false)
    private double width;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "weight", nullable = false)
    private double weight;

}

