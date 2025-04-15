package com.example.demo.repository.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.ShippingOrderRequest;
import com.example.demo.dto.request.ShippingRequest;
import com.example.demo.dto.respone.ApiShippingData;
import com.example.demo.dto.respone.ApiShippingData2;
import com.example.demo.dto.respone.DistrictsRespone;
import com.example.demo.dto.respone.ProvinceRespone;
import com.example.demo.dto.respone.WardRespone;

@FeignClient(name = "ghn-service",url = "${app.services.profile}")
public interface Shipping {
	@GetMapping(value = "/api/GHN/provinces",produces= MediaType.APPLICATION_JSON_VALUE)
	ApiShippingData<ApiShippingData2<ProvinceRespone>> getProvinces();
	
	@GetMapping(value = "/api/GHN/districts/{provinceId}",produces= MediaType.APPLICATION_JSON_VALUE)
	ApiShippingData<ApiShippingData2<DistrictsRespone>> getDistrict(@PathVariable(name = "provinceId") int provinceId);
	
	@GetMapping(value = "/api/GHN/wards/{districtId}",produces= MediaType.APPLICATION_JSON_VALUE)
	ApiShippingData<ApiShippingData2<WardRespone>> getWard(@PathVariable(name = "districtId") int districtId);
	
//	@PostMapping(value = "/api/GHN/calculate-fee",produces = MediaType.APPLICATION_JSON_VALUE)
//	ApiShippingData3<ShippingFeeResponse> calculatefee(@RequestBody ShippingRequest request);
	
	@PostMapping(value = "/api/GHN/calculate-fee",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> calculatefee(@RequestBody ShippingRequest request);
	
	@PostMapping(value = "/api/GHN/create-order",produces = MediaType.APPLICATION_JSON_VALUE) 
	ResponseEntity<?> createOrder(@RequestBody ShippingOrderRequest request);

}
