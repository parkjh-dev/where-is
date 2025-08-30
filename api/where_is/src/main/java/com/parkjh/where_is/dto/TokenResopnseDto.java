package com.parkjh.where_is.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class TokenResopnseDto {
    private Map<String, String> token;
    private UserResponseDto user;

    public TokenResopnseDto() {}

    public TokenResopnseDto(
            Map<String, String> token,
            UserResponseDto user
    ) {
        this.token = token;
        this.user = user;
    }
}
