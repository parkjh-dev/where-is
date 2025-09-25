package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Trash;
import com.parkjh.where_is.dto.TrashRequestDto;
import com.parkjh.where_is.dto.TrashResponseDto;
import com.parkjh.where_is.dto.TrashSearchDto;
import com.parkjh.where_is.repository.TrashRepository;
import com.parkjh.where_is.repository.TrashCommentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TrashService {
    private final TrashRepository trashRepository;
    private final TrashCommentsRepository trashCommentsRepository;
    public TrashService(TrashRepository trashRepository, TrashCommentsRepository trashCommentsRepository) {
        this.trashRepository = trashRepository;
        this.trashCommentsRepository = trashCommentsRepository;
    }

    public Page<TrashResponseDto> getList(TrashSearchDto query) {
        Sort sort = "desc".equalsIgnoreCase(query.getOrder())
                ? Sort.by(query.getSort()).descending()
                : Sort.by(query.getSort()).ascending();

        PageRequest pageRequest = PageRequest.of(
                (int) query.getPage(),
                (int) query.getSize(),
                sort
        );
        // 검색 조건이 있는 경우 동적 쿼리 생성
        Page<Trash> page = hasSearchCriteria(query)
            ? trashRepository.findTrashWithSearchCriteria(
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
            : trashRepository.findAll(pageRequest);

        List<Long> ids = page.getContent().stream().map(Trash::getId).toList();
        Map<Long, Long> idToCount = new HashMap<>();
        Map<Long, Double> idToAvg = new HashMap<>();
        if (!ids.isEmpty()) {
            trashCommentsRepository.countGroupByTrashId(ids)
                    .forEach(row -> idToCount.put((Long) row[0], (Long) row[1]));
            trashCommentsRepository.averageGroupByTrashId(ids)
                    .forEach(row -> idToAvg.put((Long) row[0], (Double) row[1]));
        }

        return page.map(entity -> {
            long count = idToCount.getOrDefault(entity.getId(), 0L);
            Double avg = idToAvg.get(entity.getId());
            return TrashResponseDto.toResponseDto(entity, count, avg);
        });
    }

    public Optional<Trash> getOne(Long toiletId) {
        return trashRepository.findById(toiletId);
    }

    public TrashResponseDto save(TrashRequestDto request) {
        Trash created = trashRepository.save(request.toEntity());
        return TrashResponseDto.toResponseDto(created);
    }

    public TrashResponseDto update(Long id, TrashRequestDto request) {
        Trash existingToilet = trashRepository.findById(id)
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

        Trash updated = trashRepository.save(existingToilet);
        return TrashResponseDto.toResponseDto(updated);
    }

    public Map<String, Object> delete(List<Long> ids) {
        // 삭제 전에 존재하는 ID 확인
        List<Long> existingIds = trashRepository.findAllById(ids)
                .stream()
                .map(Trash::getId)
                .toList();

        trashRepository.deleteAllById(existingIds); // 실제 삭제 실행

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

    private boolean hasSearchCriteria(TrashSearchDto query) {
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
