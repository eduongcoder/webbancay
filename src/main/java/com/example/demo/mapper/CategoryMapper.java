package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.request.CategoryCreationRequest;
import com.example.demo.dto.request.CategoryUpdateRequest;
import com.example.demo.dto.respone.CategoryReponse;
import com.example.demo.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	Category toCategory(CategoryCreationRequest request);
	Category toCategoryUpdate(CategoryUpdateRequest request);
	CategoryReponse toCategoryReponse(Category category);
}
