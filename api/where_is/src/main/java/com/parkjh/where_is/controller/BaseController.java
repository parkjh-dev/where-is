package com.parkjh.where_is.controller;

import com.parkjh.where_is.dto.ApiResponseDto;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    
    /**
     * 성공 응답을 반환하는 공통 메서드
     */
    protected ResponseEntity<ApiResponseDto<?>> successResponse(Object data) {
        return ResponseEntity.ok(new ApiResponseDto<>(200, "SUCCESS", data));
    }
    
    /**
     * 성공 응답을 반환하는 공통 메서드 (커스텀 메시지)
     */
    protected ResponseEntity<ApiResponseDto<?>> successResponse(Object data, String message) {
        return ResponseEntity.ok(new ApiResponseDto<>(200, message, data));
    }
    
    /**
     * 에러 응답을 반환하는 공통 메서드
     */
    protected ResponseEntity<ApiResponseDto<?>> errorResponse(int code, String message) {
        return ResponseEntity.status(code).body(new ApiResponseDto<>(code, message, null));
    }
    
    /**
     * 에러 응답을 반환하는 공통 메서드 (데이터 포함)
     */
    protected ResponseEntity<ApiResponseDto<?>> errorResponse(int code, String message, Object data) {
        return ResponseEntity.status(code).body(new ApiResponseDto<>(code, message, data));
    }
    
    /**
     * 400 Bad Request 응답
     */
    protected ResponseEntity<ApiResponseDto<?>> badRequest(String message) {
        return errorResponse(400, message);
    }
    
    /**
     * 404 Not Found 응답
     */
    protected ResponseEntity<ApiResponseDto<?>> notFound(String message) {
        return errorResponse(404, message);
    }
    
    /**
     * 500 Internal Server Error 응답
     */
    protected ResponseEntity<ApiResponseDto<?>> internalServerError(String message) {
        return errorResponse(500, message);
    }
} 