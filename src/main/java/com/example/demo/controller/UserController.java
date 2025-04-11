package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.ProductCreationRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.respone.ApiRespone;
import com.example.demo.dto.respone.ApiShippingData;
import com.example.demo.dto.respone.ApiShippingData2;
import com.example.demo.dto.respone.DistrictWardRespone;
import com.example.demo.dto.respone.DistrictsRespone;
import com.example.demo.dto.respone.ProductRespone;
import com.example.demo.dto.respone.ProvinceRespone;
import com.example.demo.dto.respone.UserReponse;
import com.example.demo.dto.respone.WardRespone;
import com.example.demo.repository.http.Shipping;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShippingService;
import com.example.demo.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

	UserService userService;
	Shipping shipping;
	ShippingService shippingService;
	
	@GetMapping("/getAll")
	public ApiRespone<List<UserReponse>> getAllUser(){
		return ApiRespone.<List<UserReponse>>builder().result(userService.getAll()).build();
	}
	
	@PostMapping("/create")
	public ApiRespone<UserReponse> createUser(@RequestBody UserCreationRequest request){
		return ApiRespone.<UserReponse>builder().result(userService.createUser(request)).build();
	}
	
	@PutMapping("/update")
	public ApiRespone<UserReponse> updateUser(@RequestBody UserUpdateRequest request){
		return ApiRespone.<UserReponse>builder().result(userService.UpdateRespone(request)).build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ApiRespone<Boolean> deleteUser(@PathVariable(value = "id") String id){
		return ApiRespone.<Boolean>builder().result(userService.deleteUser(id)).build();
	}
	
	@GetMapping("/getProvice")
	public ApiShippingData<ApiShippingData2<ProvinceRespone>> getProvice(){
		return shipping.getProvinces();
	}
	
	@GetMapping(value = "/getDistrict/{provinceId}")
	public ApiShippingData<ApiShippingData2<DistrictsRespone>> getDistrict(@PathVariable(name = "provinceId") int provinceId){
		return shipping.getDistrict(provinceId);
	}
	
	@GetMapping(value = "/getWard/{districtId}")
	public ApiShippingData<ApiShippingData2<WardRespone>> getWard(@PathVariable(name = "districtId") int districtId){
		return shipping.getWard(districtId);
	}
	
	@GetMapping("/getDistrictWard")
	public ApiRespone<DistrictWardRespone> getDistrictWard(@RequestParam String province,@RequestParam String district,@RequestParam String ward){
		return ApiRespone.<DistrictWardRespone>builder().result(shippingService.getDistrictWard(province, district, ward)).build() ;
	}

}
