package com.parkjh.where_is.dto;

import com.google.common.hash.Hashing;
import lombok.Getter;
import java.nio.charset.StandardCharsets;

@Getter
public class LoginRequestDto {
    private String email;
    private String pw;

    public LoginRequestDto(String email, String pw) {
        this.email = email;
        this.pw = Hashing.sha256()
                .hashString(pw, StandardCharsets.UTF_8)
                .toString();
    }
}
