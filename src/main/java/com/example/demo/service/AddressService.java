package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dto.request.AddressCreationRequest;
import com.example.demo.dto.respone.AddressRespone;
import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AddressService {

	UserRepository userRepository;
	AddressMapper addressMapper;
	AddressRepository addressRepository;

	public AddressRespone createAddress(AddressCreationRequest request) {
		Address address= addressMapper.toAddress(request);
		User user=userRepository.findById(request.getIduser()).get();
		
		address.setUser(user);
		
		
		return null;
	}
}
