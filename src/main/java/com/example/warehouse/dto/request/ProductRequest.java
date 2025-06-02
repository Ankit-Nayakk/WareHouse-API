package com.example.warehouse.dto.request;

public record ProductRequest(
        String title,
        double weight,
        double length,
        double height,
        double width,
        int quantity,
        double price,
        String materialType,
        String careInstruction
) { }
