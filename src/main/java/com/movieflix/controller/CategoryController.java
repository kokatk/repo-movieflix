package com.movieflix.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.model.Category;
import com.movieflix.service.CategoryService;

@RestController
@RequestMapping("/movieflix/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @GetMapping("/AllCategories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }
}
