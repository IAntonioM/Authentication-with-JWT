package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.dto.AuthenticationRequest;
import com.example.springsecurityjwt.dto.AuthenticationResponse;
import com.example.springsecurityjwt.dto.RegistrationRequest;
import com.example.springsecurityjwt.dto.RegistrationResponse;
import com.example.springsecurityjwt.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest){
        AuthenticationResponse jwtDto=authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody @Valid RegistrationRequest registrationRequest){
        try {
            return ResponseEntity.ok(authenticationService.register(registrationRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new RegistrationResponse(e.getMessage()));
        }
    }
    @GetMapping("/public-access")
    public String publicAccesEndPoint(){
        return "Este end point es public";
    }
}
