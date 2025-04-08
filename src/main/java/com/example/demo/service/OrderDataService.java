package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.respone.CategoryReponse;
import com.example.demo.dto.respone.OrderDataRespone;
import com.example.demo.mapper.OrderDataMapper;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderDataRepository;
import com.example.demo.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class OrderDataService {
	OrderDataRepository orderDataRepository;
	OrderDataMapper orderDataMapper;
	public List<OrderDataRespone> getAll(){
		return  orderDataRepository.findAll().stream().map(t -> orderDataMapper.toOrderDataRespone(t)).toList();
	}
}
