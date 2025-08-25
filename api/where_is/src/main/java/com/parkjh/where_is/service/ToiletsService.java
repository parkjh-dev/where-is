package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Toilets;
import com.parkjh.where_is.dto.*;
import com.parkjh.where_is.repository.ToiletsRepository;
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
    public ToiletsService(ToiletsRepository toiletsRepository) {
        this.toiletsRepository = toiletsRepository;
    }

    public Page<Toilets> getList(ToiletsSearchDto query) {
        Sort sort = "desc".equalsIgnoreCase(query.getOrder())
                ? Sort.by(query.getSort()).descending()
                : Sort.by(query.getSort()).ascending();

        PageRequest pageRequest = PageRequest.of(
                (int) query.getPage(),
                (int) query.getSize(),
                sort
        );
        // 검색 조건이 있는 경우 동적 쿼리 생성
        if (hasSearchCriteria(query)) {
            return toiletsRepository.findToiletsWithSearchCriteria(
                    query.getName(),
                    query.getLongitude(),
                    query.getLatitude(),
                    query.getManagingAgency(),
                    query.getAgencyContact(),
                    query.getRoadnmAddr(),
                    query.getLotnoAddr(),
                    query.getOperatingHours(),
                    pageRequest
            );
        }
        // 검색 조건이 없는 경우 전체 조회
        return toiletsRepository.findAll(pageRequest);
    }

    public Optional<Toilets> getOne(Long toiletId) {
        return toiletsRepository.findById(toiletId);
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
