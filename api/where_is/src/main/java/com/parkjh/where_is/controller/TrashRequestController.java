package com.parkjh.where_is.controller;

import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.dto.TrashUserRequestDto;
import com.parkjh.where_is.dto.TrashUserResponseDto;
import com.parkjh.where_is.service.TrashRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Trash-can/requests")
@Tag(name = "Trash Requests", description = "쓰레기통 위치 추가 요청 API")
public class TrashRequestController extends BaseController {
    private final TrashRequestService trashRequestService;
    public TrashRequestController(TrashRequestService trashRequestService) {
        this.trashRequestService = trashRequestService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> createTrashRequest(@RequestBody TrashUserRequestDto data) {
        TrashUserResponseDto created = trashRequestService.save(data);
        return successResponse(created);
    }

    @PostMapping("/approval")
    public ResponseEntity<ApiResponseDto<?>> approvalTrash() {
        return successResponse("RequestsApproval", "위치 추가 요청이 승인되었습니다.");
    }

    @PostMapping("/rejection")
    public ResponseEntity<ApiResponseDto<?>> rejectionTrash() {
        return successResponse("RequestsRejection", "위치 추가 요청이 거절되었습니다.");
    }
}
