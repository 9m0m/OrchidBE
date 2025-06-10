package com.example.orchidservice.service.imp;

import com.example.orchidservice.pojo.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Integer id);
    Category saveCategory(Category category);
    void deleteCategory(Integer id);
    Optional<Category> getCategoryByName(String categoryName);
}
