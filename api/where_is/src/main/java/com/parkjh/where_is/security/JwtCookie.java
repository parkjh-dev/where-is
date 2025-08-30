package com.parkjh.where_is.security;

import jakarta.servlet.http.Cookie;

public class JwtCookie {

    public static Cookie setJwtCookie(String token, int expireTime){
        Cookie jwtCookie = new Cookie("jwt_token", token);
        jwtCookie.setHttpOnly(true);        // JavaScript에서 접근 불가
        jwtCookie.setSecure(false); // dev mode
        // jwtCookie.setSecure(true); // product mode
        jwtCookie.setPath("/");             // 모든 경로에서 접근
        jwtCookie.setMaxAge(expireTime);          // 1시간 유효 (초 단위)
        jwtCookie.setDomain("localhost");    // 로컬 개발환경
        return jwtCookie;
    }
}
