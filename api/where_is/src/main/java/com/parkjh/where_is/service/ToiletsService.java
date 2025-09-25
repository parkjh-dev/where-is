package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Toilets;
import com.parkjh.where_is.dto.*;
import com.parkjh.where_is.repository.ToiletsRepository;
import com.parkjh.where_is.repository.ToiletsCommentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ToiletsService {
    private final ToiletsRepository toiletsRepository;
    private final ToiletsCommentsRepository toiletsCommentsRepository;
    public ToiletsService(ToiletsRepository toiletsRepository, ToiletsCommentsRepository toiletsCommentsRepository) {
        this.toiletsRepository = toiletsRepository;
        this.toiletsCommentsRepository = toiletsCommentsRepository;
    }

    public Page<ToiletsResponseDto> getList(ToiletsSearchDto query) {
        Sort sort = "desc".equalsIgnoreCase(query.getOrder())
                ? Sort.by(query.getSort()).descending()
                : Sort.by(query.getSort()).ascending();

        PageRequest pageRequest = PageRequest.of(
                (int) query.getPage(),
                (int) query.getSize(),
                sort
        );
        // 검색 조건이 있는 경우 동적 쿼리 생성
        Page<Toilets> page = hasSearchCriteria(query)
            ? toiletsRepository.findToiletsWithSearchCriteria(
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
            : toiletsRepository.findAll(pageRequest);

        // 배치 집계: 현재 페이지의 ID 목록을 한 번에 집계
        List<Long> ids = page.getContent().stream().map(Toilets::getId).toList();
        Map<Long, Long> idToCount = new HashMap<>();
        Map<Long, Double> idToAvg = new HashMap<>();
        if (!ids.isEmpty()) {
            toiletsCommentsRepository.countGroupByToiletsId(ids)
                    .forEach(row -> idToCount.put((Long) row[0], (Long) row[1]));
            toiletsCommentsRepository.averageGroupByToiletsId(ids)
                    .forEach(row -> idToAvg.put((Long) row[0], (Double) row[1]));
        }

        return page.map(entity -> {
            long count = idToCount.getOrDefault(entity.getId(), 0L);
            Double avg = idToAvg.get(entity.getId());
            return ToiletsResponseDto.toResponseDto(entity, count, avg);
        });
    }

    public Optional<Toilets> getOne(Long toiletId) {
        return toiletsRepository.findById(toiletId);
    }

    public Optional<ToiletsResponseDto> getOneWithStats(Long toiletId) {
        Optional<Toilets> entityOpt = toiletsRepository.findById(toiletId);
        if (entityOpt.isEmpty()) {
            return Optional.empty();
        }
        Toilets entity = entityOpt.get();
        long reviewCount = toiletsCommentsRepository.countByToiletsId(toiletId);
        Double avg = toiletsCommentsRepository.findAverageRatingByToiletsId(toiletId);
        return Optional.of(ToiletsResponseDto.toResponseDto(entity, reviewCount, avg));
    }

    public ToiletsResponseDto save(ToiletsRequestDto request) {
        Toilets created = toiletsRepository.save(request.toEntity());
        return ToiletsResponseDto.toResponseDto(created);
    }

    public ToiletsResponseDto update(Long id, ToiletsRequestDto toiletsRequest) {
        Toilets existingToilet = toiletsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        // 부분 업데이트: null이 아닌 값만 업데이트
        if (toiletsRequest.getName() != null) {
            existingToilet.setName(toiletsRequest.getName());
        }
        if (toiletsRequest.getLongitude() != null) {
            existingToilet.setLongitude(toiletsRequest.getLongitude());
        }
        if (toiletsRequest.getLatitude() != null) {
            existingToilet.setLatitude(toiletsRequest.getLatitude());
        }
        if (toiletsRequest.getManagingAgency() != null) {
            existingToilet.setManagingAgency(toiletsRequest.getManagingAgency());
        }
        // roleId는 int 타입이므로 기본값 0과 비교
        if (toiletsRequest.getAgencyContact() != null) {
            existingToilet.setAgencyContact(toiletsRequest.getAgencyContact());
        }
        if (toiletsRequest.getRoadnmAddr() != null) {
            existingToilet.setRoadnmAddr(toiletsRequest.getRoadnmAddr());
        }
        if (toiletsRequest.getLotnoAddr() != null) {
            existingToilet.setLotnoAddr(toiletsRequest.getLotnoAddr());
        }
        if (toiletsRequest.getOperatingHours() != null) {
            existingToilet.setOperatingHours(toiletsRequest.getOperatingHours());
        }

        Toilets updated = toiletsRepository.save(existingToilet);
        return ToiletsResponseDto.toResponseDto(updated);
    }

    public Map<String, Object> delete(List<Long> ids) {
        // 삭제 전에 존재하는 ID 확인
        List<Long> existingIds = toiletsRepository.findAllById(ids)
                .stream()
                .map(Toilets::getId)
                .toList();

        toiletsRepository.deleteAllById(existingIds); // 실제 삭제 실행

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

    private boolean hasSearchCriteria(ToiletsSearchDto query) {
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
