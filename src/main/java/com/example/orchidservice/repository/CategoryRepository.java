package com.example.orchidservice.repository;

import com.example.orchidservice.pojo.Category;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository {
    List<Category> findAll();
    Optional<Category> findById(Integer id);
    Category save(Category category);
    void deleteById(Integer id);
    Optional<Category> findByCategoryName(String categoryName);
}
