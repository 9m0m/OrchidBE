package com.example.orchidservice.service;

import com.example.orchidservice.pojo.Category;
import com.example.orchidservice.repository.CategoryRepository;
import com.example.orchidservice.service.imp.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

}
