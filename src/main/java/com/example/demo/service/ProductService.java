package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.CategoryCreationRequest;
import com.example.demo.dto.request.ProductCreationRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.respone.CategoryReponse;
import com.example.demo.dto.respone.ProductRespone;
import com.example.demo.dto.respone.UploadFileRespone;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ProductService {

	ProductRepository productRepository;
	ProductMapper productMapper;
	UploadFileService uploadFileService;
	CategoryRepository categoryRepository;
	
	public List<ProductRespone> getAll(){
		return  productRepository.findAll().stream().map(t -> productMapper.toProductRespone(t)).toList();
	}
	
	public ProductRespone createProduct(MultipartFile image,ProductCreationRequest request) throws IOException {
		
		Category category=categoryRepository.findById(request.getIdCategory()).orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getIdCategory()));
		
		Product product=productMapper.toProduct(request);
		UploadFileRespone url=uploadFileService.uploadFile(image);

		product.setCategory(category);
		
		product.setUrl(url.getUrl());
		product= productRepository.save(product);
		
		return productMapper.toProductRespone(product);
	}
	
	public ProductRespone UpdateProduct(MultipartFile image,ProductUpdateRequest request) throws IOException {
		Category category=categoryRepository.findById(request.getIdCategory()).orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getIdCategory()));

		Product product=productMapper.toProductUpdate(request);
		UploadFileRespone url=uploadFileService.uploadFile(image);

		product.setCategory(category);
		product.setUrl(url.getUrl());
		product= productRepository.save(product);
		
		return productMapper.toProductRespone(product);
	}


	public boolean deleteProduct(String idUser) {
		try {
			productRepository.deleteById(idUser);
			return true;
		} catch (Exception e) {
			log.info(e.toString());
			return false;
		}

	}
}
