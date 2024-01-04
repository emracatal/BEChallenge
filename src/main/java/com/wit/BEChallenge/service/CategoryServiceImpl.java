package com.wit.BEChallenge.service;

import com.wit.BEChallenge.dto.CategoryResponse;
import com.wit.BEChallenge.entity.Category;
import com.wit.BEChallenge.exceptions.CommerceException;
import com.wit.BEChallenge.repository.CategoryRepository;
import com.wit.BEChallenge.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> findAll() {
        return Converter.CategoryListConverter(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse findById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category found = optional.get();
            return new CategoryResponse(found.getId(), found.getGender(), found.getTitle(), found.getRating(), found.getImg());
        }
        throw new CommerceException("Category with given id is not found " + id, HttpStatus.NOT_FOUND);
    }


    @Override
    public CategoryResponse save(Category category) {
        Category saved = categoryRepository.save(category);
        return new CategoryResponse(category.getId(), category.getGender(), category.getTitle(), category.getRating(), category.getImg());
    }

    @Override
    public CategoryResponse delete(Long id) {
        CategoryResponse toBeRemoved = findById(id);
        categoryRepository.deleteById(toBeRemoved.id());
        return new CategoryResponse(toBeRemoved.id(), toBeRemoved.gender(), toBeRemoved.title(), toBeRemoved.rating(), toBeRemoved.img());
    }

    @Override
    public Category findCategoryById(long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CommerceException("Category with given id is not found " + id, HttpStatus.NOT_FOUND);
    }
}
