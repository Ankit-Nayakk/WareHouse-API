package com.example.warehouse.dto.request;

public record InboundBatchRequest(
        int countOfRejectUnit,
        int countOfAcceptedUnit
)
{ }
