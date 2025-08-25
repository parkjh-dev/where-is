package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.Smoking;
import com.parkjh.where_is.dto.*;
import com.parkjh.where_is.service.SmokingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/smoking-area")
@Tag(name = "Smoking", description = "흡연구역 위치 API")
public class SmokingController extends BaseController {
    private final SmokingService smokingService;
    public SmokingController(SmokingService smokingService) {
        this.smokingService = smokingService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createSmoking(@RequestBody SmokingRequestDto data) {
        SmokingResponseDto created = smokingService.save(data);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readSmoking(SmokingSearchDto query) {
        Page<Smoking> result = smokingService.getList(query);
        return successResponse(result);
    }

    @GetMapping("/{smokingId}")
    public ResponseEntity<ApiResponseDto<?>> readSmoking(@PathVariable Long smokingId) {
        Optional<Smoking> result = smokingService.getOne(smokingId);
        if (result.isPresent()) {
            return successResponse(result.map(SmokingResponseDto::toResponseDto).get());
        } else {
            return notFound("not found with id: " + smokingId);
        }
    }

    @PutMapping("/{smokingId}")
    public ResponseEntity<ApiResponseDto<?>> updateSmoking(
            @PathVariable Long smokingId,
            @RequestBody SmokingRequestDto smokingRequest
    ) {
        SmokingResponseDto updated = smokingService.update(smokingId, smokingRequest);
        return successResponse(updated);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteSmoking(@RequestParam List<Long> ids) {
        Map<String, Object> deleted = smokingService.delete(ids);
        return successResponse(deleted);
    }
}