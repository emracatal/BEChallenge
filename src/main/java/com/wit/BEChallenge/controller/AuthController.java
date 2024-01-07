package com.wit.BEChallenge.controller;

import com.wit.BEChallenge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

//    @PostMapping("/register")
//    public UserResponse register(@RequestBody RegisterUser registerUser){
//        return authenticationService
//                .register(registerUser.fullName(), registerUser.email(), registerUser.password());
//    }
}
