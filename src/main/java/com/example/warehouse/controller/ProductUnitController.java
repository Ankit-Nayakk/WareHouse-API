package com.example.warehouse.controller;

import com.example.warehouse.service.contract.ProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;

    @GetMapping(value = "/productUnitQR", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateProductUnitQRCode(@RequestParam String unitId) {
        byte[] qrCode = productUnitService.generateProductUnitQRCode(unitId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCode);
    }
}
