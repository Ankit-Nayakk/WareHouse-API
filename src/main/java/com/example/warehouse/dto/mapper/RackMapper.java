package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.entity.Rack;
import org.springframework.stereotype.Controller;

@Controller
public class RackMapper {
    public Rack toEntity(RackRequest source, Rack target){
        if (source == null) {
            return target;
        }
        if (target == null) {
            target = new Rack();
        }
        target.setHeight(source.height());
        target.setLength(source.length());
        target.setWidth(source.width());
        return target;
    }

    public RackResponse toResponse(Rack rack){
        if (rack == null) {
            return null;
        }
        return new RackResponse(
                rack.getId(),
                rack.getHeight(),
                rack.getLength(),
                rack.getWidth()
        );
    }
}
