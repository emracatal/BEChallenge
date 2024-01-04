package com.wit.BEChallenge.service;

import com.wit.BEChallenge.dto.ProductResponse;
import com.wit.BEChallenge.entity.Product;

import java.math.BigInteger;
import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();

    ProductResponse findById(Long id);

    ProductResponse save(Product product);

    ProductResponse delete(Long id);

    //TODO ÇALIŞANA KADAR DEVAM
    List<ProductResponse> findByCategoryId(Integer categoryId);

    List<Product> saveAll(List<Product> products);

}
