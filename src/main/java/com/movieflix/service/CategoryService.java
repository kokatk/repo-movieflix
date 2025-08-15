package com.movieflix.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.movieflix.model.Category;
import com.movieflix.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository category) {
        this.categoryRepository = category;
    }



    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

}
