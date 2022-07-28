package com.example.shangchuanserve.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class TokenOutTimeException extends RuntimeException{
    public TokenOutTimeException(String message) {
        super(message);
    }
}
