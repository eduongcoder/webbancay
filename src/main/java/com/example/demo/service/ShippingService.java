package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.request.AddressFeeRequest;
import com.example.demo.dto.request.Item;
import com.example.demo.dto.request.ShippingRequest;
import com.example.demo.dto.respone.ApiRespone;
import com.example.demo.dto.respone.ApiShippingData2;
import com.example.demo.dto.respone.ApiShippingData3;
import com.example.demo.dto.respone.DistrictWardRespone;
import com.example.demo.dto.respone.DistrictsRespone;
import com.example.demo.dto.respone.ProvinceRespone;
import com.example.demo.dto.respone.ShippingFeeResponse;
import com.example.demo.dto.respone.WardRespone;
import com.example.demo.repository.http.Shipping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ShippingService {
	Shipping shipping;
    ObjectMapper objectMapper;

	public DistrictWardRespone getDistrictWard(String province,String district,String ward) {
		
		List<ProvinceRespone> provinceRespones=shipping.getProvinces().getData().getData();
		
		ProvinceRespone matchedProvince= provinceRespones.stream().filter(t -> t.getProvinceName().equals(province)).findFirst().orElse(null);
		
		if(matchedProvince==null) {
	        throw new RuntimeException("Province not found: " + province);

		}
		List<DistrictsRespone> districtsRespones=shipping.getDistrict(matchedProvince.getProvinceID()).getData().getData();
		
		DistrictsRespone matcheDistrict=districtsRespones.stream().filter(t -> t.getDistrictName().equalsIgnoreCase(district)).findFirst().orElse(null);
		
		if(matcheDistrict==null) {
	        throw new RuntimeException("District not found: " + district);

		}
		
		List<WardRespone> wardRespones=shipping.getWard(matcheDistrict.getDistrictID()).getData().getData();
		
		WardRespone matchedWard=wardRespones.stream().filter(t -> t.getWardName().equalsIgnoreCase(ward)).findFirst().orElse(null);
		
		if(matchedWard==null) {
	        throw new RuntimeException("Ward not found: " + ward);

		}
		
		return DistrictWardRespone.builder().wardCode(matchedWard.getWardCode()).districtID(matcheDistrict.getDistrictID()).build();
	}
	 
	public ShippingFeeResponse calFeeShip(AddressFeeRequest request) throws JsonMappingException, JsonProcessingException{
		
		DistrictWardRespone districtWardRespone=getDistrictWard(request.getProvince(),request.getDistrict(),request.getWard());
		
		Item item = Item.builder()
		        .name("TEST1")
		        .quantity(1)
		        .length(200)
		        .width(200)
		        .height(200)
		        .weight(100)
		        .build();

		ShippingRequest shipRequest = ShippingRequest.builder()
		        .from_district_id(1454)
		        .from_ward_code("21211")
		        .service_id(53321)
		        .service_type_id(2)
		        .to_district_id(districtWardRespone.getDistrictID())
		        .to_ward_code(districtWardRespone.getWardCode())
		        .height(50)
		        .length(220)
		        .weight(200)
		        .width(20)
		        .insurance_value(10000)
		        .cod_failed_amount(2000)
		        .coupon(null)
		        .items(Collections.singletonList(item)) 
		        .build();
		
		
		
		JsonNode rootNode = objectMapper.readTree(shipping.calculatefee(shipRequest).getBody().toString());

		JsonNode dataNode = rootNode.get("data");
		
		ShippingFeeResponse shippingFeeResponse = objectMapper.treeToValue(dataNode, ShippingFeeResponse.class);
        
		return shippingFeeResponse;

	}
}
