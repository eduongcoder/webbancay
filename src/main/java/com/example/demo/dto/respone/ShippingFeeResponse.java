package com.example.demo.dto.respone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ShippingFeeResponse {
    private int total;
    private int service_fee;
    private int insurance_fee;
    private int pick_station_fee;
    private int coupon_value;
    private int r2s_fee;
    private int return_again;
    private int document_return;
    private int double_check;
    private int cod_fee;
    private int pick_remote_areas_fee;
    private int deliver_remote_areas_fee;
    private int cod_failed_fee;
}
