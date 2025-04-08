package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.request.ProductCreationRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.respone.ApiRespone;
import com.example.demo.dto.respone.ProductRespone;
import com.example.demo.service.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductController {

	ProductService productService;
	
	@GetMapping("/getAll") 
	public ApiRespone<List<ProductRespone>> getAll(){
		return ApiRespone.<List<ProductRespone>>builder().result(productService.getAll()).build();
	}
	
	@PostMapping(value = "/create",consumes =  { "multipart/form-data" })
	public ApiRespone<ProductRespone> createProduct(@RequestParam MultipartFile image,@RequestPart(required = false) ProductCreationRequest request) throws IOException{
		ProductRespone productRespone=productService.createProduct(image,request);
		return ApiRespone.<ProductRespone>builder().result(productRespone).build();
	} 
	
	@PutMapping(value ="/update",consumes =  { "multipart/form-data" })
	public ApiRespone<ProductRespone> updateProduct(@RequestParam MultipartFile image,@RequestPart(required = false) ProductUpdateRequest request) throws IOException{
		return ApiRespone.<ProductRespone>builder().result(productService.UpdateProduct(image,request)).build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ApiRespone<Boolean> deleteProduct(@PathVariable(value = "id") String id){
		return ApiRespone.<Boolean>builder().result(productService.deleteProduct(id)).build(); 
	}

}
