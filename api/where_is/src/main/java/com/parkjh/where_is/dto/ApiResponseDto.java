package com.parkjh.where_is.dto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponseDto<T> {
    private int code;
    private String message;
    private LocalDateTime responseTime;
    private T data;

    public ApiResponseDto(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.responseTime = LocalDateTime.now();
        this.data = data;
    }
}