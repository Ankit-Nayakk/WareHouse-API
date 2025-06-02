package com.example.warehouse.service.contract;

import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;

public interface InboundShipmentService {
    InboundShipmentResponse createInboundShipment(InboundShipmentRequest request, String warehouseId);
}
