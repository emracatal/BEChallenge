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

    List<ProductResponse> findByCategoryId(Integer categoryId);

    //RESTTEMPLATEBUILDER İÇİN KULLANDIM
    List<Product> saveAll(List<Product> products);

    List<ProductResponse> findByRequestParams(Integer categoryId,String sort,String filter,Long limit,Long offset);

    List<ProductResponse> bestSeller8();

}
