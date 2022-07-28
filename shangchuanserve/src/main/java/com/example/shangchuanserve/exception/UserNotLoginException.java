package com.example.shangchuanserve.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserNotLoginException extends RuntimeException{

    public UserNotLoginException(String message) {
        super(message);
    }
}
