package com.example.shangchuanserve.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class FileException extends Exception{
    public FileException(String message) {
        super(message);
    }
}
