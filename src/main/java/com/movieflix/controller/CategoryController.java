package com.movieflix.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        categoryService.createdCategory(category);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getIdCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findIdCategory(id);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public void deletedCategory(@PathVariable Long id) {
        categoryService.deletedCategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updatedCategory(@PathVariable Long id, @Valid @RequestBody Category category){
        Optional<Category> updated = categoryService.updateCategory(id, category);
        if (updated.isPresent()){
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
