package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.Trash;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.TrashRequestDto;
import com.parkjh.where_is.dto.TrashResponseDto;
import com.parkjh.where_is.dto.TrashSearchDto;
import com.parkjh.where_is.service.TrashService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/trash-can")
@Tag(name = "Trash", description = "쓰레기통 위치 API")
public class TrashController extends BaseController {
    private final TrashService trashService;
    public TrashController(TrashService trashService) {
        this.trashService = trashService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createTrash(@RequestBody TrashRequestDto data) {
        TrashResponseDto created = trashService.save(data);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readTrash(TrashSearchDto query) {
        Page<TrashResponseDto> result = trashService.getList(query);
        return successResponse(result);
    }

    @GetMapping("/{trashId}")
    public ResponseEntity<ApiResponseDto<?>> readTrash(@PathVariable Long trashId) {
        Optional<Trash> result = trashService.getOne(trashId);
        if (result.isPresent()) {
            return successResponse(result.map(TrashResponseDto::toResponseDto).get());
        } else {
            return notFound("not found with id: " + trashId);
        }
    }

    @PutMapping("/{trashId}")
    public ResponseEntity<ApiResponseDto<?>> updateTrash(
            @PathVariable Long trashId,
            @RequestBody TrashRequestDto trashRequest
    ) {
        TrashResponseDto updated = trashService.update(trashId, trashRequest);
        return successResponse(updated);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteTrash(@RequestParam List<Long> ids) {
        Map<String, Object> deleted = trashService.delete(ids);
        return successResponse(deleted);
    }
}