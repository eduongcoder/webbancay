package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.respone.UserReponse;
import com.example.demo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(UserCreationRequest request);
	User toUserUpdate(UserUpdateRequest request);

	UserReponse toUserRespone(User user);
}
