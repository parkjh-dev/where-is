package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.TrashReports;
import com.parkjh.where_is.dto.TrashReportsRequestDto;
import com.parkjh.where_is.repository.TrashReportsRepository;
import org.springframework.stereotype.Service;

@Service
public class TrashReportsService {
    private final TrashReportsRepository trashReportsRepository;
    public TrashReportsService(TrashReportsRepository trashReportsRepository) {
        this.trashReportsRepository = trashReportsRepository;
    }

    public TrashReports save(TrashReportsRequestDto request) {
        return trashReportsRepository.save(request.toEntity());
    }
}
