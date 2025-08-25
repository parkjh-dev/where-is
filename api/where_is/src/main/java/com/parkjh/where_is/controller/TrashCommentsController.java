package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.TrashComments;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.TrashCommentsRequestDto;
import com.parkjh.where_is.service.TrashCommentsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trash-can/{trashId}/comments")
@Tag(name = "Trash Comments", description = "쓰레기통 위치 댓글 API")
public class TrashCommentsController extends BaseController {

    private final TrashCommentsService trashCommentsService;
    public TrashCommentsController(TrashCommentsService trashCommentsService) {
        this.trashCommentsService = trashCommentsService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createTrashComments(
            @PathVariable Long trashId, @RequestBody TrashCommentsRequestDto data
    ) {
        data.setTrashId(trashId);
        TrashComments created = trashCommentsService.save(data);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readTrashComments(@PathVariable Long trashId) {
        List<TrashComments> datas = trashCommentsService.getList(trashId);
        return successResponse(datas);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto<?>> readTrashComment(
            @PathVariable Long trashId,
            @PathVariable Long commentId
    ) {
        TrashComments comment = trashCommentsService.getOne(trashId, commentId)
                .orElseThrow(() -> new RuntimeException("not found with id " + commentId));
        return successResponse(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto<?>> updateTrashComments(
            @PathVariable Long trashId,
            @PathVariable Long commentId,
            @RequestBody TrashCommentsRequestDto data
    ) {
        data.setTrashId(trashId);
        TrashComments updated = trashCommentsService.update(commentId, data);
        return successResponse(updated);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteTrashComments(
            @PathVariable Long trashId,
            @RequestParam List<Long> ids
    ) {
        Boolean deleted = trashCommentsService.delete(trashId, ids.get(0));
        return successResponse(deleted);
    }
}
