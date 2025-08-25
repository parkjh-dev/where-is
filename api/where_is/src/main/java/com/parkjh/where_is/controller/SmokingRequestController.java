package com.parkjh.where_is.controller;

import com.parkjh.where_is.dto.*;
import com.parkjh.where_is.service.SmokingRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smoking-area/requests")
@Tag(name = "Smoking Requests", description = "흡연구역 위치 추가 요청 API")
public class SmokingRequestController extends BaseController {
    private final SmokingRequestService smokingRequestService;
    public SmokingRequestController(SmokingRequestService smokingRequestService) {
        this.smokingRequestService = smokingRequestService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createSmokingRequest(@RequestBody SmokingUserRequestDto data) {
        SmokingUserResponseDto created = smokingRequestService.save(data);
        return successResponse(created);
    }

    @PostMapping("/approval")
    public ResponseEntity<ApiResponseDto<?>> approvalSmoking() {
        return successResponse("RequestsApproval", "위치 추가 요청이 승인되었습니다.");
    }

    @PostMapping("/rejection")
    public ResponseEntity<ApiResponseDto<?>> rejectionSmoking() {
        return successResponse("RequestsRejection", "위치 추가 요청이 거절되었습니다.");
    }
}
