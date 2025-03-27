//package com.example.demo.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//
//import lombok.Getter;
//
//@Getter
//public enum ErrorCode {
//	USER_IDENTITY_UNKNOW_ERROR(2000, "User identity unknow error",HttpStatus.BAD_REQUEST), USER_IDENTITY_EXISTS(2001, "User identity exists",HttpStatus.BAD_REQUEST),
//	USER_IDENTITY_NOT_EXISTS(2002, "User identity not exists",HttpStatus.BAD_REQUEST),USER_IDENTITY_PASSWORD_NOT_CORRECT(2003,"User identity password not correct",HttpStatus.BAD_REQUEST),
//	UNAUTHENTICATED(2004,"Unauthenticated",HttpStatus.BAD_REQUEST), DECODE_TOKEN_ERROR(2005,"Decode token error",HttpStatus.BAD_REQUEST);
//
//	private int code;
//	private String message;
//	private HttpStatusCode statusCode;
//
//	private ErrorCode(int code, String message,HttpStatusCode statusCode) {
//		this.code = code;
//		this.message = message;
//		this.statusCode= statusCode;
//	}
//
//}
