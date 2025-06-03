package com.example.warehouse.service.impl;

import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.entity.Product;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.contract.InboundShipmentService;

import com.example.warehouse.dto.mapper.InboundShipmentMapper;
import com.example.warehouse.entity.InboundShipment;
import com.example.warehouse.entity.WareHouse;
import com.example.warehouse.exceptions.WareHouseNotFoundByIdException;
import com.example.warehouse.repository.InboundShipmentRepository;
import com.example.warehouse.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InboundShipmentServiceImpl implements InboundShipmentService {
    @Autowired
    private InboundShipmentRepository inboundShipmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Autowired
    private InboundShipmentMapper inboundShipmentMapper;

    @Override
    public InboundShipmentResponse createInboundShipment(InboundShipmentRequest request, String warehouseId) {
        WareHouse wareHouse = wareHouseRepository.findById(warehouseId)
                .orElseThrow(() -> new WareHouseNotFoundByIdException("Warehouse Not Found!!!"));

        Product product = productRepository.findById(request.productDetails().id()).orElse(null);
        if (product == null) {
            product = inboundShipmentMapper.productToEntity(request.productDetails());
            product = productRepository.save(product);
        }

        InboundShipment inboundShipment = inboundShipmentMapper.toEntity(request, new InboundShipment());
        inboundShipment.setProduct(product);
        inboundShipment.setWarehouse(wareHouse);
        inboundShipmentRepository.save(inboundShipment);

        return inboundShipmentMapper.toResponse(inboundShipment);
    }


}
