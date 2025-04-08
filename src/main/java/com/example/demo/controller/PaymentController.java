package com.example.demo.controller;

import com.example.demo.configuration.Config_VNPay;
import com.example.demo.dto.respone.ApiRespone;
import com.example.demo.dto.respone.PaymentResponse;
import com.example.demo.dto.respone.TransactionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/zalo_pay")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentController {
	@GetMapping("/create_payment")
	public ApiRespone<?> createPayment(HttpServletRequest request) throws UnsupportedEncodingException {

		String vnp_TxnRef = Config_VNPay.getRandomNumber(8);

		String vnp_TmnCode = Config_VNPay.vnp_TmnCode;
		long amount = 100000 * 100; // 100,000 VND
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", Config_VNPay.vnp_Version);
		vnp_Params.put("vnp_Command", Config_VNPay.vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
		vnp_Params.put("vnp_BankCode", "NCB");
		vnp_Params.put("vnp_OrderType", "200000"); // Mã danh mục hàng hóa
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_ReturnUrl", Config_VNPay.vnp_ReturnUrl);
		vnp_Params.put("vnp_Locale", "vn");
		String clientIp = Config_VNPay.getIpAddress(request);
		log.info("Client IP: " + clientIp);
		vnp_Params.put("vnp_IpAddr", clientIp);
		vnp_Params.put("vnp_ReturnUrl", Config_VNPay.vnp_ReturnUrl);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 100000);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config_VNPay.hmacSHA512(Config_VNPay.secretKey, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config_VNPay.vnp_PayUrl + "?" + queryUrl;
		PaymentResponse paymentResponse = PaymentResponse.builder().message("success").status("OK").URL(paymentUrl)
				.build();

		return ApiRespone.builder().message("success").result(paymentResponse).build();
	}

	@GetMapping("payment_infor")
	public ApiRespone<Object> getPaymentInfor(@RequestParam(value = "vnp_Amount") String amount,
			@RequestParam(value = "vnp_BankCode") String backcode,
			@RequestParam(value = "vnp_OrderInfo") String orderinfor,
			@RequestParam(value = "vnp_ResponseCode") String response) {
		TransactionResponse responseTransaction = new TransactionResponse();
		if (response.equals("00")) {
			responseTransaction.setMessage("SUCCESS");
			responseTransaction.setData("");
			responseTransaction.setStatus("OK");
		} else {
			responseTransaction.setStatus("FAIL");
			responseTransaction.setData("");
			responseTransaction.setStatus("NO");
		}
		return ApiRespone.builder().result(responseTransaction).message("success").code(0).build();
	}
}
