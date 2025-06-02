package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rack")
@Inheritance(strategy = InheritanceType.JOINED)
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rack_id",nullable = false,updatable = false)
    private String id;

    @Column(name = "height",nullable = false)
    private double height;

    @Column(name = "length",nullable = false)
    private double length;

    @Column(name = "width",nullable = false)
    private double width;

    @ManyToOne
    @JoinColumn(name = "block_id", nullable = false)
    private List<Block> block;

    @ManyToOne
    @JoinColumn(name = "racked_block_id", nullable = false)
    private RackedBlock rackedBlock;
}
