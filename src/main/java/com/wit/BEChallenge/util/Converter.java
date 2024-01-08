package com.wit.BEChallenge.util;

import com.wit.BEChallenge.dto.*;
import com.wit.BEChallenge.entity.Category;
import com.wit.BEChallenge.entity.Product;
import com.wit.BEChallenge.entity.Role;
import com.wit.BEChallenge.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<CategoryResponse> CategoryListConverter(List<Category> allCategories) {
        List<CategoryResponse> categories = new ArrayList<>();
        for (Category category : allCategories) {
            categories.add(new CategoryResponse(category.getId(), category.getGender(),category.getTitle(),  category.getRating(), category.getImg()));
        }
        return categories;
    }

    public static CategoryResponse CategoryConverter(Category category) {
        return new CategoryResponse(category.getId(), category.getGender(),category.getTitle(),  category.getRating(), category.getImg());
    }

    public static List<ProductResponse> ProductListConverter(List<Product> allProducts) {
        List<ProductResponse> products = new ArrayList<>();
        for (Product product : allProducts) {
            products.add(new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getRating(), product.getImage()));
        }
        return products;
    }

    public static ProductResponse ProductConverter(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getRating(), product.getImage());
    }

    public static UserResponse UserConverter(User user) {
        return new UserResponse(user.getId(), user.getFullName(),user.getEmail());
    }

    public static RoleResponse RoleConverter(Role role) {
        return new RoleResponse(role.getId(), role.getAuthority());
    }

    public static List<RoleResponse> RoleListConverter(List<Role> allRoles) {
        List<RoleResponse> roles = new ArrayList<>();
        for (Role role : allRoles) {
            roles.add(new RoleResponse(role.getId(), role.getAuthority()));
        }
        return roles;
    }
}
