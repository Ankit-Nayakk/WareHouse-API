package com.example.warehouse.dto.request;

public record ProductRequest(
        String id,
        String title,
        double weight,
        double length,
        double height,
        double width,
        double price,
        String materialType,
        String careInstruction

) { }
