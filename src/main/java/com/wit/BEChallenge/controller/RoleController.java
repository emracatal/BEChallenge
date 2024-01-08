package com.wit.BEChallenge.controller;

import com.wit.BEChallenge.dto.RoleResponse;
import com.wit.BEChallenge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public List<RoleResponse> findAll(){
        return roleService.findAll();
    }
}
