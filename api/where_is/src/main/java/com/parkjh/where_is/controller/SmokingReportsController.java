package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.SmokingReports;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.SmokingReportsRequestDto;
import com.parkjh.where_is.service.SmokingReportsService;
import com.parkjh.where_is.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/smoking-area/{smokingId}/reports")
@Tag(name = "Smoking Reports", description = "잘못된 흡연구역 위치 제보 API")
public class SmokingReportsController extends BaseController {
    private final SmokingReportsService smokingReportsService;
    private final UserService userService;
    public SmokingReportsController(
            SmokingReportsService smokingReportsService,
            UserService userService
    ) {
        this.smokingReportsService = smokingReportsService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createSmokingReport(
            @PathVariable Long smokingId, @RequestBody SmokingReportsRequestDto data
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));

        data.setUserId(user.getId());
        data.setSmokingId(smokingId);

        SmokingReports created = smokingReportsService.save(data);
        return successResponse(created);
    }
}

