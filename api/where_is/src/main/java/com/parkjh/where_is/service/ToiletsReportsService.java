package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.ToiletsReports;
import com.parkjh.where_is.dto.ToiletsReportsRequestDto;
import com.parkjh.where_is.repository.ToiletsReportsRepository;
import org.springframework.stereotype.Service;

@Service
public class ToiletsReportsService {
    private final ToiletsReportsRepository toiletsReportsRepository;
    public ToiletsReportsService(ToiletsReportsRepository toiletsReportsRepository) {
        this.toiletsReportsRepository = toiletsReportsRepository;
    }

    public ToiletsReports save(ToiletsReportsRequestDto request) {
        return toiletsReportsRepository.save(request.toEntity());
    }
}
