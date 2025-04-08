package com.example.demo.dto.respone;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class OrderDataRespone {
	private String id;


	private String orderCode;

	private BigDecimal totalFee;
}
