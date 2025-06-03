package com.example.warehouse.service.contract;

import com.example.warehouse.dto.request.InventoryLocationUpdateRequest;

public interface InventoryLocationUpdateService  {
    void updateLocation(InventoryLocationUpdateRequest request);
}
