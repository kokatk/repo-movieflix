package com.movieflix.service;

import java.util.List;
import java.util.Optional;

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

    public Category createdCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findIdCategory(Long id) {
        return categoryRepository.findById(id);
    }

    public void deletedCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> updateCategory(Long id, Category updatedCategory) {
        if (id == null || updatedCategory == null){
            return Optional.empty();
        }

        return categoryRepository.findById(id)
        .map(existing -> {
            if (updatedCategory.getName() != null) {
                existing.setName(updatedCategory.getName());
            }return categoryRepository.save(existing);
        });
    }
}
