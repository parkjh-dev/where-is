package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.SmokingComments;
import com.parkjh.where_is.dto.SmokingCommentsRequestDto;
import com.parkjh.where_is.repository.SmokingCommentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmokingCommentsService {
    private final SmokingCommentsRepository smokingCommentsRepository;
    public SmokingCommentsService(SmokingCommentsRepository smokingCommentsRepository) {
        this.smokingCommentsRepository = smokingCommentsRepository;
    }

    public List<SmokingComments> getList(Long smokingId) {
        return smokingCommentsRepository.findBySmokingId(smokingId);
    }

    public Optional<SmokingComments> getOne(Long smokingId, Long commentId) {
        return smokingCommentsRepository.findBySmokingIdAndId(smokingId, commentId);
    }


    public SmokingComments save(SmokingCommentsRequestDto request) {
        return smokingCommentsRepository.save(request.toEntity());
    }
    public SmokingComments update(Long CommentsId, SmokingCommentsRequestDto data) {
        SmokingComments existingData = smokingCommentsRepository.findBySmokingIdAndId(data.getSmokingId(), CommentsId)
                .orElseThrow(() -> new RuntimeException("not found with id " + CommentsId));

        // 부분 업데이트: null이 아닌 값만 업데이트
        if (data.getRating() != null) {
            existingData.setRating(data.getRating());
        }
        if (data.getComment() != null) {
            existingData.setComment(data.getComment());
        }

        SmokingComments updated = smokingCommentsRepository.save(existingData);
        return updated;
    }
    public Boolean delete(Long smokingId, Long commentId) {
        // need chk
        smokingCommentsRepository.deleteById(commentId);
        return Boolean.TRUE;
    }
}
