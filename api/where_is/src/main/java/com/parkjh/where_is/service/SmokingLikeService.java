package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Smoking;
import com.parkjh.where_is.domain.SmokingLike;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.SmokingLikeResponseDto;
import com.parkjh.where_is.repository.SmokingLikeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SmokingLikeService {
    private final SmokingLikeRepository smokingLikeRepository;
    public SmokingLikeService(SmokingLikeRepository smokingLikeRepository) {
        this.smokingLikeRepository = smokingLikeRepository;
    }

    public SmokingLikeResponseDto save(User user, Smoking request) {
        SmokingLike entity = new SmokingLike(request , user);
        SmokingLike created = smokingLikeRepository.save(entity);
        return SmokingLikeResponseDto.toResponseDto(created);
    }

    public List<SmokingLike> getList(Long smokingId) {
        return smokingLikeRepository.findBySmokingId(smokingId);
    }

    public Boolean delete(Long userId, Long smokingId) {
        Optional<SmokingLike> existingId = smokingLikeRepository.findByUserIdAndSmokingId(userId, smokingId);

        if (existingId.isEmpty()) {
            throw new RuntimeException("not found");
        }
        smokingLikeRepository.delete(existingId.get());
        return Boolean.TRUE;
    }
}
