package com.example.demo.controller;

import com.example.demo.dto.request.PaymentRequest;
import com.example.demo.dto.request.ZaloPayRequest;
import com.example.demo.service.ZaloPayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ZaloPayController {
    ZaloPayService zaloPayService;

    @PostMapping("/create")
    public String createPaymentOrder(@RequestBody ZaloPayRequest request) throws JsonProcessingException {
        return zaloPayService.createPaymentOrderupdate(request);
    }

}
