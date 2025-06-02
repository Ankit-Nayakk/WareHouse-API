package com.example.warehouse.dto.response;

import com.example.warehouse.enums.ShipmentStatus;

public record InboundShipmentResponse(
        String shipmentId,
        String title,
        double weight,
        double length,
        double height,
        double width,
        int quantity,
        double price,
        String materialType,
        String careInstruction,
        String sellerId,
        ShipmentStatus shipmentStatus
) {
}
