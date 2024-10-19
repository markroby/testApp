package com.example.testapp.DTOs;

import com.example.testapp.Models.Users;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponseDTO extends ResponseDTO {
    private String accessToken;
    private Users user;


    public AuthenticationResponseDTO(String accessToken, Users user, String status, String message) {
        super(status, message);
        this.accessToken = accessToken;
        this.user = user;
    }
}
