package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.entity.InboundShipment;
import com.example.warehouse.entity.Rack;
import org.springframework.stereotype.Controller;

@Controller
public class InboundShipmentMapper {
    public InboundShipment toEntity(InboundShipmentRequest source, InboundShipment target){
        if (source == null) {
            return target;
        }
        if (target == null) {
            target = new InboundShipment();
        }

        target.setSellerId(source.sellerId());
        target.setProductTitle(source.productDetails().title());
        target.setProductWeight(source.productDetails().weight());
        target.setProductLength(source.productDetails().length());
        target.setProductHeight(source.productDetails().height());
        target.setProductWidth(source.productDetails().width());
        target.setMaterialType(source.productDetails().materialType());
        target.setCareInstrucution(source.productDetails().careInstruction());
        target.setQuantity(source.productDetails().quantity());
        target.setProductPrice(source.productDetails().price());
        target.setShipmentStatus(source.shipmentStatus());
        return target;
    }

    public InboundShipmentResponse toResponse(InboundShipment inboundShipment){
        if (inboundShipment == null) {
            return null;
        }
        return new InboundShipmentResponse(
                inboundShipment.getShipmentId(),
                inboundShipment.getProductTitle(),
                inboundShipment.getProductWeight(),
                inboundShipment.getProductLength(),
                inboundShipment.getProductHeight(),
                inboundShipment.getProductWidth(),
                inboundShipment.getQuantity(),
                inboundShipment.getProductPrice(),
                inboundShipment.getMaterialType(),
                inboundShipment.getCareInstrucution(),
                inboundShipment.getSellerId(),
                inboundShipment.getShipmentStatus()
        );
    }


}
