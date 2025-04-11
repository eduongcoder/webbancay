package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.respone.DistrictWardRespone;
import com.example.demo.dto.respone.DistrictsRespone;
import com.example.demo.dto.respone.ProvinceRespone;
import com.example.demo.dto.respone.WardRespone;
import com.example.demo.repository.http.Shipping;
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
	
	public DistrictWardRespone getDistrictWard(String province,String district,String ward) {
		
		List<ProvinceRespone> provinceRespones=shipping.getProvinces().getData().getData();
		
		ProvinceRespone matchedProvince= provinceRespones.stream().filter(t -> t.getProvinceName().equalsIgnoreCase(province)).findFirst().orElse(null);
		
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
}
