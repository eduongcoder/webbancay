package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.CategoryUpdateRequest;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.respone.CategoryReponse;
import com.example.demo.dto.respone.UserReponse;
import com.example.demo.entity.Category;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

	UserRepository userRepository;
	UserMapper userMapper;
	
	public List<UserReponse> getAll(){
		return  userRepository.findAll().stream().map(t -> userMapper.toUserRespone(t)).toList();
	}
	
	public UserReponse createUser(UserCreationRequest request) {
		Optional<User> exsisUser=userRepository.findByEmail(request.getEmail());
		
		if(exsisUser.isPresent()) {
			return userMapper.toUserRespone(exsisUser.get());
		}
		
		User user=userMapper.toUser(request);
		
		user= userRepository.save(user);
		
		return userMapper.toUserRespone(user);
	}


	public UserReponse UpdateRespone(UserUpdateRequest request) {
		User user=userMapper.toUserUpdate(request);
		
		user= userRepository.save(user);
		
		return userMapper.toUserRespone(user);
	}
	
	public boolean deleteUser(String idUser) {
		try {
			userRepository.deleteById(idUser);
			return true;
		} catch (Exception e) {
			log.info(e.toString());
			return false;
		}

	}
}
