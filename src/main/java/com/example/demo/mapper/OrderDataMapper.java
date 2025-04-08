package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.respone.OrderDataRespone;
import com.example.demo.entity.OrderDatas;

@Mapper(componentModel = "spring")
public interface OrderDataMapper {
	OrderDataRespone toOrderDataRespone(OrderDatas orderData);

}
