package com.wit.BEChallenge.service;

import com.wit.BEChallenge.dto.CategoryResponse;
import com.wit.BEChallenge.dto.ProductResponse;
import com.wit.BEChallenge.dto.RoleResponse;
import com.wit.BEChallenge.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleService{
    List<RoleResponse> findAll();

}
