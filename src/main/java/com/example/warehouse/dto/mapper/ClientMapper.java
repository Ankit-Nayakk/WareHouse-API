package com.example.warehouse.dto.mapper;

import com.example.warehouse.dto.request.ClientRequest;
import com.example.warehouse.dto.request.InboundBatchRequest;
import com.example.warehouse.dto.response.ClientResponse;
import com.example.warehouse.dto.response.InboundBatchResponse;
import com.example.warehouse.entity.Client;
import com.example.warehouse.entity.InboundBatch;
import org.springframework.stereotype.Controller;

@Controller
public class ClientMapper {

    public Client toEntity(ClientRequest source, Client target){
        if (source == null) {
            return target;
        }
        if (target == null) {
            target = new Client();
        }
        target.setOrganizationName(source.organizationName());
        target.setOrganizationEmail(source.organizationEmail());

        return target;
    }

    public ClientResponse toResponse(Client client){
        if (client == null) {
            return null;
        }
        return new ClientResponse(
                client.getClientId(),
                client.getOrganizationName(),
                client.getOrganizationEmail(),
                client.getApiKey(),
                client.getSecretKey(),
                client.getRegisteredAt()
        );
    }
}
