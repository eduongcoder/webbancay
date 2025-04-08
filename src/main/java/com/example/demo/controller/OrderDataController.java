package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.respone.ApiRespone;
import com.example.demo.dto.respone.OrderDataRespone;
import com.example.demo.service.OrderDataService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDataController {
	OrderDataService orderDataService;
	
	@GetMapping("/getAll") 
	public ApiRespone<List<OrderDataRespone>> getAll(){
		return ApiRespone.<List<OrderDataRespone>>builder().result(orderDataService.getAll()).build();
	}
}
