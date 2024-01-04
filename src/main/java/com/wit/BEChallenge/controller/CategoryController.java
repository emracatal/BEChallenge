package com.wit.BEChallenge.controller;

import com.wit.BEChallenge.dto.CategoryResponse;
import com.wit.BEChallenge.entity.Category;
import com.wit.BEChallenge.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/")
    public CategoryResponse save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }

    //TODO OPTIONAL https://workintech-fe-ecommerce.onrender.com/categories üzerinden data almak için RESTTEMPLATEBUILDER yapabilirsin
}
