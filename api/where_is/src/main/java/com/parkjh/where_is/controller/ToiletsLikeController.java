package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.Toilets;
import com.parkjh.where_is.domain.ToiletsLike;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.ToiletsLikeResponseDto;
import com.parkjh.where_is.service.ToiletsLikeService;
import com.parkjh.where_is.service.ToiletsService;
import com.parkjh.where_is.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/toilets/{toiletId}/likes")
@Tag(name = "Toilets Likes", description = "화장실 위치 정보 좋아요 API")
public class ToiletsLikeController  extends BaseController {
    private final ToiletsLikeService toiletsLikeService;
    private final ToiletsService toiletsService;
    private final UserService userService;

    public ToiletsLikeController(
            ToiletsLikeService toiletsLikeService,
            ToiletsService toiletsService,
            UserService userService
    ) {
        this.toiletsLikeService = toiletsLikeService;
        this.toiletsService = toiletsService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createToiletsLike(
            @PathVariable Long toiletId
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));
        Toilets target = toiletsService.getOne(toiletId)
                .orElseThrow(() -> new RuntimeException("not found"));

        ToiletsLikeResponseDto created = toiletsLikeService.save(user, target);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readToiletsLikes(
            @PathVariable Long toiletId
    ) {
        List<ToiletsLike> getList = toiletsLikeService.getList(toiletId);
        return successResponse(getList);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteToiletsLike(
            @PathVariable Long toiletId
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));
        Toilets target = toiletsService.getOne(toiletId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Boolean deleted = toiletsLikeService.delete(user.getId(), target.getId());
        return successResponse(deleted);
    }
}

