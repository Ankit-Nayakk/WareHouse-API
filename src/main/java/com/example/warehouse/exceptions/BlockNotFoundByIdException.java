package com.example.warehouse.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlockNotFoundByIdException extends RuntimeException{
    private final String message;
}
