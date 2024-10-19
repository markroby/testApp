package com.example.testapp.Services;

import com.example.testapp.DTOs.Login.LoginRequestDTO;
import com.example.testapp.DTOs.AuthenticationResponseDTO;
import com.example.testapp.DTOs.ResponseDTO;
import com.example.testapp.DTOs.SignUpDTO;
import com.example.testapp.Mappers.SignUpMapper;
import com.example.testapp.Models.Users;
import com.example.testapp.Repositories.AuthenticationRepository;
import com.example.testapp.Security.JWTGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    final AuthenticationRepository authenticationRepository;
    final AuthenticationManager authenticationManager;
    final JWTGenerator jwtGenerator;

    public AuthenticationService(AuthenticationRepository authenticationRepository, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
        this.authenticationRepository = authenticationRepository;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }


    public AuthenticationResponseDTO signUp(SignUpDTO signUpDTO) {
        Users user = SignUpMapper.mapSignUpDTO(signUpDTO);
        try {
            authenticationRepository.save(user);
            Authentication authManager = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(), signUpDTO.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authManager);
            if(authManager.isAuthenticated()) {
                String accessToken = jwtGenerator.generateToken(authManager);
                return new AuthenticationResponseDTO(accessToken, user, "Success","Sign Up Successful");
            }else{
                return new AuthenticationResponseDTO(null, null, "Failed","Sign Up Failed");
            }

        } catch (Exception e) {
            return new AuthenticationResponseDTO(null, null, "Failed","Sign Up Failed");
        }

    }


    public AuthenticationResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authManager = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getIdentifier(), loginRequestDTO.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authManager);
        if(authManager.isAuthenticated()){
            String accessToken = jwtGenerator.generateToken(authManager);
            Users user = authenticationRepository.findByUsernameOrEmail(loginRequestDTO.getIdentifier(),loginRequestDTO.getIdentifier());
            return new AuthenticationResponseDTO(accessToken, user, "Success","Login Success");
        }else{
            return new AuthenticationResponseDTO(null, null, "Failed","Login Failed");
        }
    }
}
