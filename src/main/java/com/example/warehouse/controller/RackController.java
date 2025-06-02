package com.example.warehouse.controller;

import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.dto.response.RoomResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.RackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RackController {

    @Autowired
    private RackService rackService;

    @PostMapping("/racks/{blockId}")
    public ResponseEntity<ResponseStructure<RackResponse>> createRack(@RequestBody RackRequest request,@RequestParam String blockId) {
        RackResponse rackResponse = rackService.createRack(request,blockId);
        ResponseStructure<RackResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Rack Created Successfully",rackResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
    }
}
