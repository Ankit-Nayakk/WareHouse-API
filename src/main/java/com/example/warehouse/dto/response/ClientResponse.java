package com.example.warehouse.dto.response;

import java.time.Instant;

public record ClientResponse(
        String clientId,
        String organizationName,
        String organizationEmail,
        String apiKey,
        String secretKey,
        Instant registeredAt

) {
}
