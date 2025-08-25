package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.ToiletsReports;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.ToiletsReportsRequestDto;
import com.parkjh.where_is.service.ToiletsReportsService;
import com.parkjh.where_is.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toilets/{toiletId}/reports")
@Tag(name = "Toilets Reports", description = "잘못된 화장실 위치 제보 API")
public class ToiletsReportsController extends BaseController {
    private final ToiletsReportsService toiletsReportsService;
    private final UserService userService;
    public ToiletsReportsController(
            ToiletsReportsService toiletsReportsService,
            UserService userService
    ) {
        this.toiletsReportsService = toiletsReportsService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createToiletsReport(
            @PathVariable Long toiletId, @RequestBody ToiletsReportsRequestDto data
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));

        data.setUserId(user.getId());
        data.setToiletId(toiletId);

        ToiletsReports created = toiletsReportsService.save(data);
        return successResponse(created);
    }
}

