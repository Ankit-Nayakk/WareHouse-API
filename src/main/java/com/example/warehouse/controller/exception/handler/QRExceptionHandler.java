package com.example.warehouse.controller.exception.handler;

import com.example.warehouse.dto.wrapper.ErrorResponse;
import com.example.warehouse.exceptions.ProductUnitNotFoundByIdException;
import com.example.warehouse.exceptions.RackNotFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QRExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRackNotFoundByIdException(RackNotFoundByIdException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleProductUnitNotFoundByIdException(ProductUnitNotFoundByIdException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
