package com.example.warehouse.controller.exception.handler;

import com.example.warehouse.dto.wrapper.ErrorResponse;
import com.example.warehouse.exceptions.UnsupportedUserRoleException;
import com.example.warehouse.exceptions.UserNotFoundByEmailException;
import com.example.warehouse.exceptions.UserNotFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundById(UserNotFoundByIdException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUnsupportedUserRoleException(UnsupportedUserRoleException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(),e.getMessage());
        return  new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundByEmail(UserNotFoundByEmailException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return  new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }


}
