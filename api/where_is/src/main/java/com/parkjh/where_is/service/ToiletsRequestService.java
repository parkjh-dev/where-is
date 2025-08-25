package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.ToiletsRequests;
import com.parkjh.where_is.dto.ToiletsUserRequestDto;
import com.parkjh.where_is.dto.ToiletsUserResponseDto;
import com.parkjh.where_is.repository.ToiletsRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class ToiletsRequestService {
    private final ToiletsRequestRepository toiletsRepository;
    public ToiletsRequestService(ToiletsRequestRepository toiletsRepository) {
        this.toiletsRepository = toiletsRepository;
    }

    public ToiletsUserResponseDto save(ToiletsUserRequestDto request) {
        ToiletsRequests created = toiletsRepository.save(request.toRequestsEntity());
        return ToiletsUserResponseDto.toResponseDto(created);
    }
}
