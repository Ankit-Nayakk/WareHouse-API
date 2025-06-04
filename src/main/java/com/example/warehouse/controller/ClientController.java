package com.example.warehouse.controller;

import com.example.warehouse.dto.request.ClientRequest;
import com.example.warehouse.dto.response.ClientResponse;
import com.example.warehouse.dto.wrapper.ResponseStructure;
import com.example.warehouse.service.contract.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/client/register")
    public ResponseEntity<ResponseStructure<ClientResponse>> registerClient(@RequestBody ClientRequest request) {
        ClientResponse clientResponse = clientService.registerClient(request);
        ResponseStructure<ClientResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Client Registered Successfully !!!", clientResponse);
        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }
}
