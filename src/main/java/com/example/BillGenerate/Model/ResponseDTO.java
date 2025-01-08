package com.example.BillGenerate.Model;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ResponseDTO <T>{
    private T body;
    private HttpStatus status;
    private String message;

    public ResponseDTO(T body, HttpStatus status, String message) {
        this.body = body;
        this.status = status;
        this.message = message;
    }
}


