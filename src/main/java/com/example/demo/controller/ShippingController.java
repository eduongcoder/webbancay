package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.AddressFeeRequest;
import com.example.demo.dto.request.ShippingRequest;
import com.example.demo.dto.respone.ApiRespone;
import com.example.demo.dto.respone.ApiShippingData;
import com.example.demo.dto.respone.ApiShippingData2;
import com.example.demo.dto.respone.ApiShippingData3;
import com.example.demo.dto.respone.DistrictWardRespone;
import com.example.demo.dto.respone.DistrictsRespone;
import com.example.demo.dto.respone.ProvinceRespone;
import com.example.demo.dto.respone.ShippingFeeResponse;
import com.example.demo.dto.respone.WardRespone;
import com.example.demo.repository.http.Shipping;
import com.example.demo.service.ShippingService;
import com.example.demo.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ShippingController {
	Shipping shipping;
	ShippingService shippingService;

	@GetMapping("/getProvice")
	public ApiShippingData<ApiShippingData2<ProvinceRespone>> getProvice() {
		return shipping.getProvinces();
	}

	@GetMapping(value = "/getDistrict/{provinceId}")
	public ApiShippingData<ApiShippingData2<DistrictsRespone>> getDistrict(
			@PathVariable(name = "provinceId") int provinceId) {
		return shipping.getDistrict(provinceId);
	}

	@GetMapping(value = "/getWard/{districtId}")
	public ApiShippingData<ApiShippingData2<WardRespone>> getWard(@PathVariable(name = "districtId") int districtId) {
		return shipping.getWard(districtId);
	}

	@GetMapping("/getDistrictWard")
	public ApiRespone<DistrictWardRespone> getDistrictWard(@RequestParam String province, @RequestParam String district,
			@RequestParam String ward) {
		return ApiRespone.<DistrictWardRespone>builder()
				.result(shippingService.getDistrictWard(province, district, ward)).build();
	}

//	@PostMapping("/fee")
//	public ApiRespone<ResponseEntity<?>> calFee(@RequestBody ShippingRequest request){
//		return ApiRespone.<ResponseEntity<?>>builder().result(shipping.calculatefee(request)).build() ;
//	}

	@PostMapping("/fee")
	public ShippingFeeResponse calFee(@RequestBody AddressFeeRequest request) {
		log.info("Request gửi tới GHN: {}", request);
		try {
			ShippingFeeResponse response = shippingService.calFeeShip(request);
		    log.info("Phản hồi GHN: {}", response);
		    return response;
		} catch (Exception e) {
		    log.error("Lỗi khi gọi GHN", e);
		    return null;
		}
	}
}
