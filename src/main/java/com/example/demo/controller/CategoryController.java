package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.CategoryCreationRequest;
import com.example.demo.dto.request.CategoryUpdateRequest;
import com.example.demo.dto.request.ProductCreationRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.respone.ApiRespone;
import com.example.demo.dto.respone.CategoryReponse;
import com.example.demo.dto.respone.ProductRespone;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryController {
	
	CategoryService categoryService;

	@GetMapping("/getAll")
	public ApiRespone<List<CategoryReponse>> getAll() {
		return ApiRespone.<List<CategoryReponse>>builder().result(categoryService.getAll()).build();
	}

	@PostMapping("/create")
	public ApiRespone<CategoryReponse> createProduct(@RequestBody CategoryCreationRequest request) {
		return ApiRespone.<CategoryReponse>builder().result(categoryService.createCategory(request)).build();
	}

	@PutMapping("/update")
	public ApiRespone<CategoryReponse> updateProduct(@RequestBody CategoryUpdateRequest request) {
		return ApiRespone.<CategoryReponse>builder().result(categoryService.UpdateCategory(request)).build();
	}

	@DeleteMapping("/delete/{id}")
	public ApiRespone<Boolean> deleteProduct(@PathVariable(value = "id") String id) {
		return ApiRespone.<Boolean>builder().result(categoryService.deleteCategory(id)).build();
	}
}
