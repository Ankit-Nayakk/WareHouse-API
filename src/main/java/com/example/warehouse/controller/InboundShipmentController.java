package com.example.warehouse.controller;

import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.InboundShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InboundShipmentController {

    @Autowired
    private InboundShipmentService inboundShipmentService;

    @PostMapping("/inboundShipments/{warehouseId}]")
    public ResponseEntity<ResponseStructure<InboundShipmentResponse>> createInboundShipment(InboundShipmentRequest request, String warehouseId){
        InboundShipmentResponse inboundShipmentResponse = inboundShipmentService.createInboundShipment(request, warehouseId);
        ResponseStructure<InboundShipmentResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Inbound Shipment Created Successfully",inboundShipmentResponse);
        return new ResponseEntity<ResponseStructure<InboundShipmentResponse>>(responseStructure, HttpStatus.CREATED);
    }
}
