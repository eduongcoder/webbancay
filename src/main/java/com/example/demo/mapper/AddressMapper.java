package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.request.AddressCreationRequest;
import com.example.demo.dto.respone.AddressRespone;
import com.example.demo.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	Address toAddress(AddressCreationRequest request);
	AddressRespone toAddressRespone(Address address);
}
