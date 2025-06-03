package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.InboundBatchMapper;
import com.example.warehouse.dto.request.InboundBatchRequest;
import com.example.warehouse.dto.response.InboundBatchResponse;
import com.example.warehouse.entity.InboundBatch;
import com.example.warehouse.entity.InboundShipment;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.ProductUnit;
import com.example.warehouse.exceptions.ShipmentIdNotExistException;
import com.example.warehouse.repository.InboundBatchRepository;
import com.example.warehouse.repository.InboundShipmentRepository;
import com.example.warehouse.repository.ProductUnitRepository;
import com.example.warehouse.service.contract.InboundBatchService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InboundBatchServiceImpl implements InboundBatchService {
    @Autowired
    private InboundBatchRepository inBoundBatchRepository;

    @Autowired
    private ProductUnitRepository productUnitRepository;

    @Autowired
    private InboundBatchMapper inBoundBatchMapper;

    @Autowired
    private InboundShipmentRepository inBoundShipmentRepository;

    @Transactional
    @Override
    public InboundBatchResponse receiveProductUnit(InboundBatchRequest request, String shipmentId) {
        InboundShipment inBoundShipment = inBoundShipmentRepository.findById(shipmentId).orElseThrow(() -> new ShipmentIdNotExistException("Shipment Not Created Yet!!"));
        Product product = inBoundShipment.getProduct();
        com.example.warehouse.entity.InboundBatch inBoundBatch = inBoundBatchMapper.toEntity(request, new InboundBatch());

        List<ProductUnit> productUnits = new ArrayList<>();

        for (int i = 0; i < inBoundBatch.getCountOfAcceptedUnit(); i++) {
            ProductUnit unit = new ProductUnit();
            unit.setProduct(product);
            unit.setInboundShipment(inBoundShipment);
            unit.setInboundBatch(inBoundBatch);
            productUnits.add(unit);
        }
        inBoundBatch.setProductUnits(productUnits);
        inBoundBatchRepository.save(inBoundBatch);
        productUnitRepository.saveAll(productUnits);
        return inBoundBatchMapper.toResponse(inBoundBatch);
    }
}
