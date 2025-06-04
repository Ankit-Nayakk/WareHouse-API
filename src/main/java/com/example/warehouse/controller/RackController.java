package com.example.warehouse.controller;

import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.RackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RackController {

    @Autowired
    private RackService rackService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/racks/{blockId}")
    public ResponseEntity<ResponseStructure<RackResponse>> createRack(@RequestBody RackRequest request,@PathVariable String blockId) {
        RackResponse rackResponse = rackService.createRack(request,blockId);
        ResponseStructure<RackResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Rack Created Successfully",rackResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
    }

    @GetMapping(value = "/racks/qrcode" , produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateRackQRCode(@RequestParam String rackId) {
            byte[] qrCode = rackService.generateRackQRCode(rackId);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCode);
    }

}
