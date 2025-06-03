package com.example.warehouse.service.impl;

import com.example.warehouse.dto.request.InventoryLocationUpdateRequest;
import com.example.warehouse.entity.Block;
import com.example.warehouse.entity.ProductUnit;
import com.example.warehouse.entity.Room;
import com.example.warehouse.enums.BlockType;
import com.example.warehouse.exceptions.BlockNotFoundByIdException;
import com.example.warehouse.repository.BlockRepository;
import com.example.warehouse.repository.ProductUnitRepository;
import com.example.warehouse.service.contract.InventoryLocationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLocationUpdateServiceImpl implements InventoryLocationUpdateService {

    @Autowired
    ProductUnitRepository productUnitRepository;

    @Autowired
    BlockRepository blockRepository;

    @Override
    public void updateLocation(InventoryLocationUpdateRequest request) {
        Block block = blockRepository.findById(request.blockId()).orElseThrow(() -> new BlockNotFoundByIdException("Block Not Found!!!"));
        Room room = block.getRoom();
        BlockType blockType = block.getType();
        StringBuilder locationBuilder = new StringBuilder();
        locationBuilder.append(room.getRoomId())
                .append(";")
                .append(block.getBlockId())
                .append(";")
                .append(blockType.name());
        if (blockType == BlockType.RACKED) {
            locationBuilder.append(";")
                    .append(request.rackId())
                    .append(";")
                    .append(request.shelfNo());
        }
        String location = locationBuilder.toString();
        List<ProductUnit> productUnits = productUnitRepository.findAllById(request.productUnitId());
        for (ProductUnit productUnit : productUnits) {
            productUnit.setLocation(location);
        }
        productUnitRepository.saveAll(productUnits);
    }
}
