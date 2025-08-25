package com.parkjh.where_is.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Auth API")
public class LoginController {

    public LoginController() {}

    @PostMapping("login")
    public void login() {}

    @PostMapping("logout")
    public void logout() {}

    @PostMapping("verify")
    public void verify() {}
}
