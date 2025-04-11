package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    int amount;
    String app_id;
    String app_time;
    String app_trans_id;
    String app_user;
    List<String> item=new ArrayList<>();
    String bank_code;
    String description;
    EmbedData embed_data;


}
