package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.Trash;
import com.parkjh.where_is.domain.TrashLike;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.TrashLikeResponseDto;
import com.parkjh.where_is.service.TrashLikeService;
import com.parkjh.where_is.service.TrashService;
import com.parkjh.where_is.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trash-can/{trashId}/likes")
@Tag(name = "Trash Likes", description = "쓰레기통 위치 정보 좋아요 API")
public class TrashLikeController extends BaseController {
    private final TrashLikeService trashLikeService;
    private final TrashService trashService;
    private final UserService userService;

    public TrashLikeController(
            TrashLikeService trashLikeService,
            TrashService trashService,
            UserService userService
    ) {
        this.trashLikeService = trashLikeService;
        this.trashService = trashService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createTrashLike(
            @PathVariable Long trashId
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));
        Trash target = trashService.getOne(trashId)
                .orElseThrow(() -> new RuntimeException("not found"));

        TrashLikeResponseDto created = trashLikeService.save(user, target);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readTrashLikes(
            @PathVariable Long trashId
    ) {
        List<TrashLike> getList = trashLikeService.getList(trashId);
        return successResponse(getList);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteTrashLike(
            @PathVariable Long trashId
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));
        Trash target = trashService.getOne(trashId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Boolean deleted = trashLikeService.delete(user.getId(), target.getId());
        return successResponse(deleted);
    }
}

