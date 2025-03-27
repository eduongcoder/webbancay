package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.request.CategoryCreationRequest;
import com.example.demo.dto.request.ProductCreationRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.respone.CategoryReponse;
import com.example.demo.dto.respone.ProductRespone;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	Product toProduct(ProductCreationRequest request);
	Product toProductUpdate(ProductUpdateRequest request);

	ProductRespone toProductRespone(Product product);
}
