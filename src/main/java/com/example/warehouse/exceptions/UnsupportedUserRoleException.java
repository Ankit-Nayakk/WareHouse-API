package com.example.warehouse.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UnsupportedUserRoleException extends RuntimeException{
    private final String message;
}
