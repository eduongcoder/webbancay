package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.CategoryCreationRequest;
import com.example.demo.dto.request.CategoryUpdateRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.respone.CategoryReponse;
import com.example.demo.dto.respone.ProductRespone;
import com.example.demo.dto.respone.UserReponse;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class CategoryService {
	CategoryRepository categoryRepository;
	CategoryMapper categoryMapper;
	
	public List<CategoryReponse> getAll(){
		return  categoryRepository.findAll().stream().map(t -> categoryMapper.toCategoryReponse(t)).toList();
	}
	
	
	public CategoryReponse createCategory(CategoryCreationRequest request) {
		Category category=categoryMapper.toCategory(request);
		
		category= categoryRepository.save(category);
		
		return categoryMapper.toCategoryReponse(category);
	}


	public CategoryReponse UpdateCategory(CategoryUpdateRequest request) {
		Category category=categoryMapper.toCategoryUpdate(request);
		
		category= categoryRepository.save(category);
		
		return categoryMapper.toCategoryReponse(category);
	}
	
	public boolean deleteCategory(String idUser) {
		try {
			categoryRepository.deleteById(idUser);
			return true;
		} catch (Exception e) {
			log.info(e.toString());
			return false;
		}

	}
}
