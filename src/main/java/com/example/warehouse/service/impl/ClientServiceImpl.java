package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.ClientMapper;
import com.example.warehouse.dto.request.ClientRequest;
import com.example.warehouse.dto.response.ClientResponse;
import com.example.warehouse.entity.Client;
import com.example.warehouse.repository.ClientRepository;
import com.example.warehouse.service.contract.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;

    @Override
    public ClientResponse registerClient(ClientRequest request) {
        String apiKey = UUID.randomUUID().toString();
        String secretKey = Base64.getEncoder().encodeToString(new SecureRandom().generateSeed(32));
        secretKey = passwordEncoder.encode(secretKey);

        Client client = new Client();
        client.setOrganizationName(request.organizationName());
        client.setOrganizationEmail(request.organizationEmail());
        client.setApiKey(apiKey);
        client.setSecretKey(secretKey);

        clientRepository.save(client);
        return clientMapper.toResponse(client);
    }
}
