package com.example.warehouse.service.impl;

import com.example.warehouse.entity.ProductUnit;
import com.example.warehouse.exceptions.ProductUnitNotFoundByIdException;
import com.example.warehouse.repository.ProductUnitRepository;
import com.example.warehouse.service.contract.ProductUnitService;
import com.example.warehouse.shared.QRCodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Autowired
    ProductUnitRepository productUnitRepository;

    @Autowired
    QRCodeGeneratorService qrCodeGeneratorService;

    @Override
    public byte[] generateProductUnitQRCode(String unitId) {
        ProductUnit productUnit = productUnitRepository.findById(unitId)
                .orElseThrow( () -> new ProductUnitNotFoundByIdException("Product Unit Not Found !!!"));

        String productId = productUnit.getProduct().getId();
        String batchId = productUnit.getInboundBatch().getBatchId();

        String content = String.format("""
                {
                "productId" : "%s",
                "InboundBatchId" : "%s",
                "productUnitId" : "%s"
                }
                """ , productId, batchId, productUnit.getUnitId());

        try {
            return qrCodeGeneratorService.generateQRCode(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}
