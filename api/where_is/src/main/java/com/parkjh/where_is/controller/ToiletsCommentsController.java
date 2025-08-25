package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.ToiletsComments;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.ToiletsCommentsRequestDto;
import com.parkjh.where_is.service.ToiletsCommentsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/toilets/{toiletId}/comments")
@Tag(name = "Toilets Comments", description = "화장실 위치 댓글 API")
public class ToiletsCommentsController extends BaseController {

    private final ToiletsCommentsService toiletsCommentsService;
    public ToiletsCommentsController(ToiletsCommentsService toiletsCommentsService) {
        this.toiletsCommentsService = toiletsCommentsService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createToiletsComments(
            @PathVariable Long toiletId, @RequestBody ToiletsCommentsRequestDto data
    ) {
        data.setToiletsId(toiletId);
        ToiletsComments created = toiletsCommentsService.save(data);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readToiletsComments(@PathVariable Long toiletId) {
        List<ToiletsComments> datas = toiletsCommentsService.getList(toiletId);
        return successResponse(datas);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto<?>> readToiletsComment(
            @PathVariable Long toiletId,
            @PathVariable Long commentId
    ) {
        ToiletsComments comment = toiletsCommentsService.getOne(toiletId, commentId)
                .orElseThrow(() -> new RuntimeException("not found with id " + commentId));
        return successResponse(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto<?>> updateToiletsComments(
            @PathVariable Long toiletId,
            @PathVariable Long commentId,
            @RequestBody ToiletsCommentsRequestDto data
    ) {
        data.setToiletsId(toiletId);
        ToiletsComments updated = toiletsCommentsService.update(commentId, data);
        return successResponse(updated);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteToiletsComments(
            @PathVariable Long toiletId,
            @RequestParam List<Long> ids
    ) {
        Boolean deleted = toiletsCommentsService.delete(toiletId, ids.get(0));
        return successResponse(deleted);
    }
}
