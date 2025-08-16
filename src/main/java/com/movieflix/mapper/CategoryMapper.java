package com.movieflix.mapper;


import com.movieflix.DTOrequest.CategoryRequest;
import com.movieflix.DTOresponse.CategoryResponse;
import com.movieflix.model.Category;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest){
        return Category
        .builder()
        .name(categoryRequest.name())
        .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse
        .builder()
        .id(category.getId())
        .name(category.getName())
        .build();
    }

}
