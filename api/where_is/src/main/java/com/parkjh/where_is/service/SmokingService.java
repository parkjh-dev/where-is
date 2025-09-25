package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Smoking;
import com.parkjh.where_is.dto.*;
import com.parkjh.where_is.repository.SmokingRepository;
import com.parkjh.where_is.repository.SmokingCommentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SmokingService {
    private final SmokingRepository smokingRepository;
    private final SmokingCommentsRepository smokingCommentsRepository;
    public SmokingService(SmokingRepository smokingRepository, SmokingCommentsRepository smokingCommentsRepository) {
        this.smokingRepository = smokingRepository;
        this.smokingCommentsRepository = smokingCommentsRepository;
    }

    public Page<SmokingResponseDto> getList(SmokingSearchDto query) {
        Sort sort = "desc".equalsIgnoreCase(query.getOrder())
                ? Sort.by(query.getSort()).descending()
                : Sort.by(query.getSort()).ascending();

        PageRequest pageRequest = PageRequest.of(
                (int) query.getPage(),
                (int) query.getSize(),
                sort
        );
        // 검색 조건이 있는 경우 동적 쿼리 생성
        Page<Smoking> page = hasSearchCriteria(query)
            ? smokingRepository.findSmokingWithSearchCriteria(
                    query.getName(),
                    query.getLongitude(),
                    query.getLatitude(),
                    query.getManagingAgency(),
                    query.getAgencyContact(),
                    query.getRoadnmAddr(),
                    query.getLotnoAddr(),
                    query.getOperatingHours(),
                    pageRequest
            )
            : smokingRepository.findAll(pageRequest);

        List<Long> ids = page.getContent().stream().map(Smoking::getId).toList();
        Map<Long, Long> idToCount = new HashMap<>();
        Map<Long, Double> idToAvg = new HashMap<>();
        if (!ids.isEmpty()) {
            smokingCommentsRepository.countGroupBySmokingId(ids)
                    .forEach(row -> idToCount.put((Long) row[0], (Long) row[1]));
            smokingCommentsRepository.averageGroupBySmokingId(ids)
                    .forEach(row -> idToAvg.put((Long) row[0], (Double) row[1]));
        }

        return page.map(entity -> {
            long count = idToCount.getOrDefault(entity.getId(), 0L);
            Double avg = idToAvg.get(entity.getId());
            return SmokingResponseDto.toResponseDto(entity, count, avg);
        });
    }

    public Optional<Smoking> getOne(Long toiletId) {
        return smokingRepository.findById(toiletId);
    }

    public SmokingResponseDto save(SmokingRequestDto request) {
        Smoking created = smokingRepository.save(request.toEntity());
        return SmokingResponseDto.toResponseDto(created);
    }

    public SmokingResponseDto update(Long id, SmokingRequestDto request) {
        Smoking existingToilet = smokingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        // 부분 업데이트: null이 아닌 값만 업데이트
        if (request.getName() != null) {
            existingToilet.setName(request.getName());
        }
        if (request.getLongitude() != null) {
            existingToilet.setLongitude(request.getLongitude());
        }
        if (request.getLatitude() != null) {
            existingToilet.setLatitude(request.getLatitude());
        }
        if (request.getManagingAgency() != null) {
            existingToilet.setManagingAgency(request.getManagingAgency());
        }
        // roleId는 int 타입이므로 기본값 0과 비교
        if (request.getAgencyContact() != null) {
            existingToilet.setAgencyContact(request.getAgencyContact());
        }
        if (request.getRoadnmAddr() != null) {
            existingToilet.setRoadnmAddr(request.getRoadnmAddr());
        }
        if (request.getLotnoAddr() != null) {
            existingToilet.setLotnoAddr(request.getLotnoAddr());
        }
        if (request.getOperatingHours() != null) {
            existingToilet.setOperatingHours(request.getOperatingHours());
        }

        Smoking updated = smokingRepository.save(existingToilet);
        return SmokingResponseDto.toResponseDto(updated);
    }

    public Map<String, Object> delete(List<Long> ids) {
        // 삭제 전에 존재하는 ID 확인
        List<Long> existingIds = smokingRepository.findAllById(ids)
                .stream()
                .map(Smoking::getId)
                .toList();

        smokingRepository.deleteAllById(existingIds); // 실제 삭제 실행

        // 결과 반환
        Map<String, Object> result = new HashMap<>();
        result.put("requestedCount", ids.size());       // 요청한 개수
        result.put("foundCount", existingIds.size());   // 실제 존재했던 개수
        result.put("deletedIds", existingIds);          // 삭제된 ID 목록
        result.put("notFoundIds", ids.stream()
                .filter(id -> !existingIds.contains(id))
                .toList());        // 존재하지 않았던 ID 목록

        return result;
    }

    private boolean hasSearchCriteria(SmokingSearchDto query) {
        return (query.getName() != null && !query.getName().trim().isEmpty()) ||
                (query.getLongitude() != null && !query.getLongitude().trim().isEmpty()) ||
                (query.getLatitude() != null && !query.getLatitude().trim().isEmpty()) ||
                (query.getManagingAgency() != null && !query.getManagingAgency().trim().isEmpty()) ||
                (query.getAgencyContact() != null && !query.getAgencyContact().trim().isEmpty()) ||
                (query.getRoadnmAddr() != null && !query.getRoadnmAddr().trim().isEmpty()) ||
                (query.getLotnoAddr() != null && !query.getLotnoAddr().trim().isEmpty()) ||
                (query.getOperatingHours() != null && !query.getOperatingHours().toString().trim().isEmpty());
    }
}
