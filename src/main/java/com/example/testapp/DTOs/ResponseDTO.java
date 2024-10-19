package com.example.testapp.DTOs;
@lombok.Data
public class ResponseDTO {
    private String message;
    private String status;

    public ResponseDTO(String status, String message) {
        this.message = message;
        this.status = status;
    }
}
