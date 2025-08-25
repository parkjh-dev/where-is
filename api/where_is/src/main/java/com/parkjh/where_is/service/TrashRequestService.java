package com.parkjh.where_is.service;


import com.parkjh.where_is.domain.TrashRequests;
import com.parkjh.where_is.dto.TrashUserRequestDto;
import com.parkjh.where_is.dto.TrashUserResponseDto;
import com.parkjh.where_is.repository.TrashRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class TrashRequestService {
    private final TrashRequestRepository trashRepository;
    public TrashRequestService(TrashRequestRepository trashRepository) {
        this.trashRepository = trashRepository;
    }

    public TrashUserResponseDto save(TrashUserRequestDto request) {
        TrashRequests created = trashRepository.save(request.toRequestsEntity());
        return TrashUserResponseDto.toResponseDto(created);
    }
}
