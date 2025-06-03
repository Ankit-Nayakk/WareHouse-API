package com.example.warehouse.controller;

import com.example.warehouse.dto.request.InventoryLocationUpdateRequest;
import com.example.warehouse.service.contract.InventoryLocationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryLocationUpdateController {

    @Autowired
    private InventoryLocationUpdateService inventoryLocationUpdateService;

    @PutMapping("/location")
    public void updateLocation(@RequestBody InventoryLocationUpdateRequest request) {
        inventoryLocationUpdateService.updateLocation(request);
    }

}
