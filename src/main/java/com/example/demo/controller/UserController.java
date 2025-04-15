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

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.respone.ApiRespone;
import com.example.demo.dto.respone.UserReponse;
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
	


}
