package com.example.warehouse.dto.response;

public record ProductResponse(
        String productId,
        String productTitle,
        double productWeight,
        double productLength,
        double productHeight,
        double productWidth,
        String materialType,
        String careInstruction,
        double productPrice
) { }
