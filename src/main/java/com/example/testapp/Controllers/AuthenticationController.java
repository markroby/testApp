package com.example.testapp.Controllers;

import com.example.testapp.DTOs.AuthenticationResponseDTO;
import com.example.testapp.DTOs.Login.LoginRequestDTO;
import com.example.testapp.DTOs.ResponseDTO;
import com.example.testapp.DTOs.SignUpDTO;
import com.example.testapp.Services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthenticationController {
    final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        final AuthenticationResponseDTO responseDTO = authenticationService.login(loginRequestDTO);
        if(Objects.equals(responseDTO.getStatus(), "Success")){
            return ResponseEntity.ok(responseDTO);
        }else{
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/signUp")
//    @PreAuthorize("hasRole('SignUpRole')")
    public ResponseEntity< AuthenticationResponseDTO > signUp(@RequestBody SignUpDTO signUpDTO){
        final AuthenticationResponseDTO responseDTO = authenticationService.signUp(signUpDTO);

        if (Objects.equals(responseDTO.getStatus(), "Success")){
            return ResponseEntity.ok(responseDTO);
        }else{
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }
}
