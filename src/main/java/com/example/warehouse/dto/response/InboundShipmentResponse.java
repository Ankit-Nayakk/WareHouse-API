package com.example.warehouse.dto.response;

import com.example.warehouse.entity.Product;
import com.example.warehouse.enums.ShipmentStatus;

import java.time.Instant;

public record InboundShipmentResponse(
        String shipmentId,
        ProductResponse productResponse,
        String sellerId,
        long createdAt,
        ShipmentStatus status,
        int quantity
) {
}
