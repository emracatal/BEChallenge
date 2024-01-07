package com.wit.BEChallenge.controller;

import com.wit.BEChallenge.dto.LoginRequest;
import com.wit.BEChallenge.dto.RegisterUser;
import com.wit.BEChallenge.dto.UserResponse;
import com.wit.BEChallenge.entity.User;
import com.wit.BEChallenge.service.AuthenticationService;
import com.wit.BEChallenge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService,AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService=authenticationService;
    }

    @RequestMapping("/")
    public String getUser(){
        return "User area";
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterUser registerUser){
        return authenticationService
                .register(registerUser.fullName(), registerUser.email(), registerUser.password());
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest loginRequest){
        System.out.printf("logged in successfully");
        return authenticationService.login(loginRequest);
    }
}
