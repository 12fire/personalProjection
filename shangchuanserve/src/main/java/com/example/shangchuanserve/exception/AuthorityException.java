package com.example.shangchuanserve.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AuthorityException extends RuntimeException {
    public AuthorityException(String message) {
        super(message);
    }
}
