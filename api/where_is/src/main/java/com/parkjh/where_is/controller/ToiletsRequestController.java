package com.parkjh.where_is.controller;

import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.ToiletsResponseDto;
import com.parkjh.where_is.dto.ToiletsUserRequestDto;
import com.parkjh.where_is.service.ToiletsRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/toilets/requests")
@Tag(name = "Toilets Requests", description = "화장실 위치 추가 요청 API")
public class ToiletsRequestController extends BaseController {
    private final ToiletsRequestService toiletsRequestService;
    public ToiletsRequestController(ToiletsRequestService toiletsRequestService) {
        this.toiletsRequestService = toiletsRequestService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createToiletsRequests(@RequestBody ToiletsUserRequestDto data) {
        ToiletsResponseDto created = toiletsRequestService.save(data);
        return successResponse(created);
    }

    @PostMapping("/approval")
    public ResponseEntity<ApiResponseDto<?>> approvalToilets() {
        return successResponse("ToiletsRequestsApproval", "화장실 위치 추가 요청이 승인되었습니다.");
    }

    @PostMapping("/rejection")
    public ResponseEntity<ApiResponseDto<?>> rejectionToilets() {
        return successResponse("ToiletsRequestsRejection", "화장실 위치 추가 요청이 거절되었습니다.");
    }
}
