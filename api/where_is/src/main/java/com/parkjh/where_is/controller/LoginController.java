package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.TokenResopnseDto;
import com.parkjh.where_is.dto.UserResponseDto;
import com.parkjh.where_is.security.JwtCookie;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.LoginRequestDto;
import com.parkjh.where_is.security.JwtUtils;
import com.parkjh.where_is.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Auth API")
public class LoginController extends BaseController{
    private UserService userService;
    private JwtUtils jwtUtils;

    public LoginController(
            UserService userService,
            JwtUtils jwtUtils
    ) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponseDto<?>> login(
            @RequestBody LoginRequestDto request,
            HttpServletResponse response
    ) {
        try {
            // auth validate
            User user = userService.validateLogin(request.getEmail(), request.getPw());
             if (user == null) {
                 return unauthorized("Invalid credentials");
             }

            // generate token
            String token = this.jwtUtils.generateToken(request.getEmail(), "USER");
            String expireTimeString  = System.getenv("JWT_EXPIRE_NORMAL");
            int expireTime = Integer.parseInt(expireTimeString);
            Map<String, String> tokenInfo = Map.of(
                    "type", "Bearer",
                    "token", token,
                    "expires_in", Long.toString(this.jwtUtils.getExpirationTime())
            );

            // set-cookie
            Cookie jwtCookie = JwtCookie.setJwtCookie(token, expireTime);
            response.addCookie(jwtCookie);

            TokenResopnseDto responseData = new TokenResopnseDto(
                    tokenInfo,
                    UserResponseDto.toResponseDto(user)
            );

            return successResponse(responseData);
        } catch (Exception e) {
            return internalServerError("auth error");
        }
    }

    @PostMapping("logout")
    public ResponseEntity<ApiResponseDto<?>> logout(HttpServletResponse response) {
        try {
            // set-cookie
            Cookie jwtCookie = JwtCookie.setJwtCookie("", 0);
            response.addCookie(jwtCookie);
            return successResponse("tmp");
        } catch (Exception e) {
            return internalServerError("auth error");
        }
    }

    @PostMapping("verify")
    public ResponseEntity<ApiResponseDto<?>> verify(
            @CookieValue(value = "jwt_token", required = false) String token
    ) {
        try {
            if (token == null || token.isEmpty()) {
                return unauthorized("Invalid credentials");
            }
            boolean tokenValidate = jwtUtils.validateToken(token);
            
            if (tokenValidate) {
                Map<String, String> tokenInfo = Map.of(
                        "type", "Bearer",
                        "token", token,
                        "expires_in", Long.toString(this.jwtUtils.getExpirationTime())
                );

                TokenResopnseDto responseData = new TokenResopnseDto(
                        tokenInfo,
                        UserResponseDto.toResponseDto(null)
                );

                return successResponse(responseData);
            } else {
                return unauthorized("Invalid credentials");
            }
        } catch (Exception e) {
            return internalServerError("auth error");
        }
    }
}
