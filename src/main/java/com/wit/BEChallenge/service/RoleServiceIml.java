package com.wit.BEChallenge.service;

import com.wit.BEChallenge.dto.RoleResponse;
import com.wit.BEChallenge.entity.Role;
import com.wit.BEChallenge.repository.RoleRepository;
import com.wit.BEChallenge.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceIml implements RoleService{
    private RoleRepository roleRepository;

    @Autowired

    public RoleServiceIml(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleResponse> findAll() {
        return Converter.RoleListConverter(roleRepository.findAll());
    }
}
