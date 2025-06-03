package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.request.InboundBatchRequest;
import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.InboundBatchResponse;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.entity.InboundBatch;
import com.example.warehouse.entity.Rack;
import org.springframework.stereotype.Controller;

@Controller
public class InboundBatchMapper {
    public InboundBatch toEntity(InboundBatchRequest source, InboundBatch target){
        if (source == null) {
            return target;
        }
        if (target == null) {
            target = new InboundBatch();
        }
        target.setCountOfRejectedUnit(source.countOfRejectUnit());
        target.setCountOfAcceptedUnit(source.countOfAcceptedUnit());
        return target;
    }

    public InboundBatchResponse toResponse(InboundBatch inboundBatch){
        if (inboundBatch == null) {
            return null;
        }
        return new InboundBatchResponse(
                inboundBatch.getBatchId(),
                inboundBatch.getCountOfRejectedUnit(),
                inboundBatch.getCountOfAcceptedUnit()
        );
    }
}
