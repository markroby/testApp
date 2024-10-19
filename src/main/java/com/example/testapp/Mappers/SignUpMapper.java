package com.example.testapp.Mappers;

import com.example.testapp.DTOs.SignUpDTO;
import com.example.testapp.Models.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

public class SignUpMapper {
    public static Users mapSignUpDTO(SignUpDTO dto) {
        return new Users(
                dto.getFirstName(),
                dto.getPassword(),
                dto.getEmail(),
                new BCryptPasswordEncoder().encode(dto.getPassword()),
                dto.getUserName(),
                dto.getPhoneNumber(),
                new ArrayList<>()
        );
    }
}
