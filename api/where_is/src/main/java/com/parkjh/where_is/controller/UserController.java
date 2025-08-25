package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.UserRequestDto;
import com.parkjh.where_is.dto.UserResponseDto;
import com.parkjh.where_is.dto.UserSearchDto;
import com.parkjh.where_is.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "사용자 관리 API")
public class UserController extends BaseController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> CreateUser(@RequestBody UserRequestDto user) {
        UserResponseDto created = userService.save(user);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> ReadUsers(UserSearchDto query) {
        Page<User> result = userService.getList(query);
        return successResponse(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<?>> ReadUser(@PathVariable Long userId) {
        Optional<User> user = userService.getOne(userId);
        
        if (user.isPresent()) {
            return successResponse(user.map(UserResponseDto::toResponseDto).get());
        } else {
            return notFound("User not found with id: " + userId);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<?>> UpdateUser(
            @PathVariable Long userId,
            @RequestBody UserRequestDto userRequest
    ) {
        UserResponseDto updated = userService.update(userId, userRequest);
        return successResponse(updated);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> DeleteUsers(@RequestParam List<Long> ids) {
        Map<String, Object> deleted = userService.delete(ids);
        return successResponse(deleted);
    }
}
