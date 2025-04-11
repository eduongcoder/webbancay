package com.example.demo.service;

import com.example.demo.Client.ZaloPayClient;
import com.example.demo.Util.ZaloPayUtil;
import com.example.demo.dto.request.EmbedData;
import com.example.demo.dto.request.PaymentRequest;
import com.example.demo.dto.request.ZaloPayRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.cloudinary.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ZaloPayService {
    String ZALO_PAY_API_URL = "https://zlpdev-mi-zlpdemo.zalopay.vn/zlp-demo/v2/api/gateway";
    String APP_ID = "2553";
    String APP_KEY = "PcY4iZIKFCIdgZvA6ueMcMHHUbRLYjPL";
    String ZALO_PAY_SECRET_KEY = "kLtgPl8HHhfvMuDHPwKfgfsY4Ydm9eIz";
    ZaloPayUtil zaloPayUtil;
    String key1 = "PcY4iZIKFCIdgZvA6ueMcMHHUbRLYjPL";
    String key2 = "kLtgPl8HHhfvMuDHPwKfgfsY4Ydm9eIz";

    ZaloPayClient zaloPayClient;
    public String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    public String createPaymentOrderupdate(ZaloPayRequest user){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("app_id", "2553");
        map.add("key1", key1);
        map.add("key2", key2);
        map.add("amount", user.getAmount());
        map.add("app_user", "demo");
        map.add("embed_data", "{\"promotioninfo\":\"\",\"merchantinfo\":\"embeddata123\"}");
        map.add("item", "[{\"itemid\":\"knb\",\"itemname\":\"kim nguyen bao\",\"itemprice\":198400,\"itemquantity\":1}]");
        map.add("description", "Demo - Thanh toan don hang #ORDERID");
        map.add("more_param", "currency=VND&phone=0925226173");
        map.add("bankcode", "zalopayapp");
        String randum=String.valueOf(Math.random()*1000000000);
        StringBuilder builder=new StringBuilder();
        builder.append("250411");
        builder.append("_");
        builder.append(randum);
        String data = "app_id=" + "2553" + "&app_trans_id=" +builder.toString() + "&...";  // Include other parameters here

// Generate MAC
        String mac = zaloPayUtil.HMacHexStringEncode(zaloPayUtil.HMACSHA256, "PcY4iZIKFCIdgZvA6ueMcMHHUbRLYjPL", data);
        map.add("mac", mac);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://zlpdev-mi-zlpdemo.zalopay.vn/zlp-demo/v2/api/gateway",
                HttpMethod.POST,
                entity,
                String.class
        );
        return response.toString();
    }


}
