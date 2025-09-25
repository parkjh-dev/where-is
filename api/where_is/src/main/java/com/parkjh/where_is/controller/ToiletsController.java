package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.Toilets;
import com.parkjh.where_is.dto.*;
import com.parkjh.where_is.service.ToiletsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/toilets")
@Tag(name = "Toilets", description = "화장실 위치 API")
public class ToiletsController extends BaseController {
    private final ToiletsService toiletsService;
    public ToiletsController(ToiletsService toiletsService) {
        this.toiletsService = toiletsService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createToilets(@RequestBody ToiletsRequestDto data) {
        ToiletsResponseDto created = toiletsService.save(data);
        return successResponse(created);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readToilets(ToiletsSearchDto query) {
        Page<ToiletsResponseDto> result = toiletsService.getList(query);
        return successResponse(result);
    }

    @GetMapping("/{toiletsId}")
    public ResponseEntity<ApiResponseDto<?>> readToilet(@PathVariable Long toiletsId) {
        Optional<Toilets> result = toiletsService.getOne(toiletsId);
        if (result.isPresent()) {
            return successResponse(result.map(ToiletsResponseDto::toResponseDto).get());
        } else {
            return notFound("User not found with id: " + toiletsId);
        }
    }

    @PutMapping("/{toiletsId}")
    public ResponseEntity<ApiResponseDto<?>> updateToilets(
            @PathVariable Long toiletsId,
            @RequestBody ToiletsRequestDto toiletsRequest
    ) {
        ToiletsResponseDto updated = toiletsService.update(toiletsId, toiletsRequest);
        return successResponse(updated);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<?>> deleteToilets(@RequestParam List<Long> ids) {
        Map<String, Object> deleted = toiletsService.delete(ids);
        return successResponse(deleted);
    }
}