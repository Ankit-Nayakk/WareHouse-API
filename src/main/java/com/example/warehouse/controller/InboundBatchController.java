package com.example.warehouse.controller;

import com.example.warehouse.dto.request.InboundBatchRequest;
import com.example.warehouse.dto.response.InboundBatchResponse;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.InboundBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InboundBatchController {
    @Autowired
    private InboundBatchService inboundBatchService;

    @PostMapping("/inboundBatches/{shipmentId}")
    public ResponseEntity<ResponseStructure<InboundBatchResponse>> createInboundBatch(@RequestBody InboundBatchRequest request,@PathVariable String shipmentId){
        InboundBatchResponse inBoundBatchResponse =  inboundBatchService.receiveProductUnit(request,shipmentId);
        ResponseStructure<InboundBatchResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Units Are Added !!",inBoundBatchResponse);
        return new ResponseEntity<ResponseStructure<InboundBatchResponse>>(responseStructure,HttpStatus.CREATED);
    }
}
