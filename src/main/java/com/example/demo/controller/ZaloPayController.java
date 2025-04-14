package com.example.demo.controller;

import com.example.demo.dto.request.PaymentRequest;
import com.example.demo.dto.request.ZaloPayCallback;
import com.example.demo.dto.request.ZaloPayRequest;
import com.example.demo.dto.respone.ZaloPayResponseData;
import com.example.demo.service.ZaloPayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ZaloPayController {
    ZaloPayService zaloPayService;
    ObjectMapper objectMapper;
    

    @PostMapping("/create")
    public String createPaymentOrder(@RequestBody ZaloPayRequest request) throws JsonProcessingException {
    	ResponseEntity<?> responseEntity = zaloPayService.createPaymentOrderupdate(request);

        JsonNode rootNode = objectMapper.readTree(responseEntity.getBody().toString());

        String responseDataString = rootNode.get("response_data").asText();

        ZaloPayResponseData responseData = objectMapper.readValue(responseDataString, ZaloPayResponseData.class);

        System.out.println("Mã giao dịch: " + responseData.getZp_trans_token());
        System.out.println("Link thanh toán: " + responseData.getOrder_url());


        return responseData.getOrder_url();
    }

}
