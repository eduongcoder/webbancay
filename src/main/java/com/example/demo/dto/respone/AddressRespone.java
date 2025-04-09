package com.example.demo.dto.respone;

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
public class AddressRespone {
	String idAddress;

	String address;

	String feeShip;
}
