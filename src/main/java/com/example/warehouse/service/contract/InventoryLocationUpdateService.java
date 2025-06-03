package com.example.warehouse.service.contract;

import com.example.warehouse.dto.request.InventoryLocationUpdateRequest;
import com.example.warehouse.dto.response.InventoryLocationUpdateResponse;

public interface InventoryLocationUpdateService  {
    void updateLocation(InventoryLocationUpdateRequest request);
}
