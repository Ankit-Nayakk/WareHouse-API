package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.request.ProductRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.dto.response.ProductResponse;
import com.example.warehouse.entity.InboundShipment;
import com.example.warehouse.entity.Product;
import org.springframework.stereotype.Controller;

@Controller
public class InboundShipmentMapper {
    public InboundShipment toEntity(InboundShipmentRequest source, InboundShipment target){
        target.setSellerId(source.sellerId());
        target.setShipmentStatus(source.status());
        target.setQuantity(source.quantity());
        target.setProduct(productToEntity(source.productDetails()));
        return target;
    }

    public InboundShipmentResponse toResponse(InboundShipment inBoundShipment) {

        return new InboundShipmentResponse(
                inBoundShipment.getShipmentId(),
                productToResponse(inBoundShipment.getProduct()),
                inBoundShipment.getSellerId(),
                inBoundShipment.getCreatedAt().toEpochMilli(),
                inBoundShipment.getShipmentStatus(),
                inBoundShipment.getQuantity()
        );
    }

    public Product productToEntity(ProductRequest productRequest) {

        Product product = new Product();
        product.setId(productRequest.id());
        product.setTitle(productRequest.title());
        product.setWeight(productRequest.weight());
        product.setLength(productRequest.length());
        product.setHeight(productRequest.height());
        product.setWidth(productRequest.width());
        product.setMaterialType(productRequest.materialType());
        product.setCareInstruction(productRequest.careInstruction());
        product.setPrice(productRequest.price());

        return product;
    }

    private ProductResponse productToResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getWeight(),
                product.getLength(),
                product.getHeight(),
                product.getWidth(),
                product.getMaterialType(),
                product.getCareInstruction(),
                product.getPrice()
        );
    }


}
