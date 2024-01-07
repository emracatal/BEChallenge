package com.wit.BEChallenge.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public String admin(){
        return "This is admin area";
    }

    //TODO adminin erişeceği bir endpoint ile kullanıcının rolünü admin olarak set etmek gerekli
}
