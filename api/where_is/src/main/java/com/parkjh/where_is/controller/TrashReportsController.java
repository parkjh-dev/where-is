package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.TrashReports;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.TrashReportsRequestDto;
import com.parkjh.where_is.service.TrashReportsService;
import com.parkjh.where_is.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trash-can/{trashId}/reports")
@Tag(name = "Trash Reports", description = "잘못된 쓰레기통 위치 제보 API")
public class TrashReportsController extends BaseController {
    private final TrashReportsService trashReportsService;
    private final UserService userService;
    public TrashReportsController(
            TrashReportsService trashReportsService,
            UserService userService
    ) {
        this.trashReportsService = trashReportsService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createTrashReport(
            @PathVariable Long trashId, @RequestBody TrashReportsRequestDto data
    ) {
        User user = userService.getOne(1L)
                .orElseThrow(() -> new RuntimeException("not found"));

        data.setUserId(user.getId());
        data.setTrashId(trashId);

        TrashReports created = trashReportsService.save(data);
        return successResponse(created);
    }
}

