package com.example.warehouse.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShipmentIdNotExistException extends RuntimeException{
    private final String message;
}
