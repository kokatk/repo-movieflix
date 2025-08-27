package com.movieflix.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.DTOrequest.CategoryRequest;
import com.movieflix.DTOresponse.CategoryResponse;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.model.Category;
import com.movieflix.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movieflix/categories")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        List<CategoryResponse> list = categories.stream()
        .map(CategoryMapper::toCategoryResponse)
        .toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        Category createdCategory = categoryService.createdCategory(CategoryMapper.toCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(createdCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getIdCategory(@PathVariable Long id){
        return categoryService.findIdCategory(id)
        .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
        .orElse(ResponseEntity.notFound().build());
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedCategory(@PathVariable Long id) {
        categoryService.deletedCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updatedCategory(@PathVariable Long id, @Valid @RequestBody Category request){
        return categoryService.updateCategory(id, request)
        .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
        .orElse(ResponseEntity.notFound().build());
    
    }
}
