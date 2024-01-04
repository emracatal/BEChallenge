package com.wit.BEChallenge.service;

import com.wit.BEChallenge.dto.CategoryResponse;
import com.wit.BEChallenge.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);

    CategoryResponse save(Category category);

    CategoryResponse delete(Long id);

    Category findCategoryById(long id);

}
