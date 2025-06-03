package com.example.warehouse.dto.response;

public record InboundBatchResponse(
        String id,
        int countOfRejectedUnit,
        int countOfAcceptedUnit
) {
}
