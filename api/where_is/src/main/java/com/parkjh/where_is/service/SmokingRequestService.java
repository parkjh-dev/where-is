package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.SmokingRequests;
import com.parkjh.where_is.dto.SmokingUserRequestDto;
import com.parkjh.where_is.dto.SmokingUserResponseDto;
import com.parkjh.where_is.repository.SmokingRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class SmokingRequestService {
    private final SmokingRequestRepository smokingRepository;
    public SmokingRequestService(SmokingRequestRepository smokingRepository) {
        this.smokingRepository = smokingRepository;
    }

    public SmokingUserResponseDto save(SmokingUserRequestDto request) {
        SmokingRequests created = smokingRepository.save(request.toRequestsEntity());
        return SmokingUserResponseDto.toResponseDto(created);
    }
}
