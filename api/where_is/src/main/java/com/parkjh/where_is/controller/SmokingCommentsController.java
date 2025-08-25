package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.SmokingComments;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.SmokingCommentsRequestDto;
import com.parkjh.where_is.service.SmokingCommentsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/smoking/{smokingId}/comments")
@Tag(name = "Smoking Comments", description = "흡연구역 위치 댓글 API")
public class SmokingCommentsController extends BaseController {

    private final SmokingCommentsService smokingCommentsService;
    public SmokingCommentsController(SmokingCommentsService smokingCommentsService) {
        this.smokingCommentsService = smokingCommentsService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createSmokingComments(
            @PathVariable Long smokingId, @RequestBody SmokingCommentsRequestDto data
    ) {
        data.setSmokingId(smokingId);
        SmokingComments created = smokingCommentsService.save(data);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readSmokingComments(@PathVariable Long smokingId) {
        List<SmokingComments> datas = smokingCommentsService.getList(smokingId);
        return successResponse(datas);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto<?>> readSmokingComment(
            @PathVariable Long smokingId,
            @PathVariable Long commentId
    ) {
        SmokingComments comment = smokingCommentsService.getOne(smokingId, commentId)
                .orElseThrow(() -> new RuntimeException("not found with id " + commentId));
        return successResponse(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto<?>> updateSmokingComments(
            @PathVariable Long smokingId,
            @PathVariable Long commentId,
            @RequestBody SmokingCommentsRequestDto data
    ) {
        data.setSmokingId(smokingId);
        SmokingComments updated = smokingCommentsService.update(commentId, data);
        return successResponse(updated);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteSmokingComments(
            @PathVariable Long smokingId,
            @RequestParam List<Long> ids
    ) {
        Boolean deleted = smokingCommentsService.delete(smokingId, ids.get(0));
        return successResponse(deleted);
    }
}
