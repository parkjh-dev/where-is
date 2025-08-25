package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.Smoking;
import com.parkjh.where_is.domain.SmokingLike;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.SmokingLikeResponseDto;
import com.parkjh.where_is.service.SmokingLikeService;
import com.parkjh.where_is.service.SmokingService;
import com.parkjh.where_is.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/smoking-area/{smokingId}/likes")
@Tag(name = "Smoking Likes", description = "흡연구역 위치 정보 좋아요 API")
public class SmokingLikeController extends BaseController {
    private final SmokingLikeService smokingLikeService;
    private final SmokingService smokingService;
    private final UserService userService;

    public SmokingLikeController(
            SmokingLikeService smokingLikeService,
            SmokingService smokingService,
            UserService userService
    ) {
        this.smokingLikeService = smokingLikeService;
        this.smokingService = smokingService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createSmokingLike(
            @PathVariable Long smokingId
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));
        Smoking target = smokingService.getOne(smokingId)
                .orElseThrow(() -> new RuntimeException("not found"));

        SmokingLikeResponseDto created = smokingLikeService.save(user, target);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readSmokingLikes(
            @PathVariable Long smokingId
    ) {
        List<SmokingLike> getList = smokingLikeService.getList(smokingId);
        return successResponse(getList);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteSmokingLike(
            @PathVariable Long smokingId
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));
        Smoking target = smokingService.getOne(smokingId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Boolean deleted = smokingLikeService.delete(user.getId(), target.getId());
        return successResponse(deleted);
    }
}

