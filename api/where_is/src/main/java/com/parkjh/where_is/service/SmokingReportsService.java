package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.SmokingReports;
import com.parkjh.where_is.dto.SmokingReportsRequestDto;
import com.parkjh.where_is.repository.SmokingReportsRepository;
import org.springframework.stereotype.Service;

@Service
public class SmokingReportsService {
    private final SmokingReportsRepository smokingReportsRepository;
    public SmokingReportsService(SmokingReportsRepository smokingReportsRepository) {
        this.smokingReportsRepository = smokingReportsRepository;
    }

    public SmokingReports save(SmokingReportsRequestDto request) {
        return smokingReportsRepository.save(request.toEntity());
    }
}
