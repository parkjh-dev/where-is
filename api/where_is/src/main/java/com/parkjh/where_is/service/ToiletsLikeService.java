package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Toilets;
import com.parkjh.where_is.domain.ToiletsLike;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.ToiletsLikeResponseDto;
import com.parkjh.where_is.repository.ToiletsLikeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToiletsLikeService {
    private final ToiletsLikeRepository toiletsLikeRepository;
    public ToiletsLikeService(ToiletsLikeRepository toiletsLikeRepository) {
        this.toiletsLikeRepository = toiletsLikeRepository;
    }

    public ToiletsLikeResponseDto save(User user, Toilets request) {
        ToiletsLike entity = new ToiletsLike(request , user);
        ToiletsLike created = toiletsLikeRepository.save(entity);
        return ToiletsLikeResponseDto.toResponseDto(created);
    }

    public List<ToiletsLike> getList(Long toiletId) {
        // ToiletsLikeResponseDto
        return toiletsLikeRepository.findByToiletsId(toiletId);
    }

    public Boolean delete(Long userId, Long toiletId) {
        Optional<ToiletsLike> existingId = toiletsLikeRepository.findByUserIdAndToiletsId(userId, toiletId);

        if (existingId.isEmpty()) {
            throw new RuntimeException("not found");
        }
        toiletsLikeRepository.delete(existingId.get());
        return Boolean.TRUE;
    }
}
