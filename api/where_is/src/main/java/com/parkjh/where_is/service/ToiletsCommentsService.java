package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Toilets;
import com.parkjh.where_is.domain.ToiletsComments;
import com.parkjh.where_is.dto.ToiletsCommentsRequestDto;
import com.parkjh.where_is.repository.ToiletsCommentsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToiletsCommentsService {
    private final ToiletsCommentsRepository toiletsCommentsRepository;
    public ToiletsCommentsService(ToiletsCommentsRepository toiletsCommentsRepository) {
        this.toiletsCommentsRepository = toiletsCommentsRepository;
    }

    public List<ToiletsComments> getList(Long toiletId) {
        return toiletsCommentsRepository.findByToiletsId(toiletId);
    }

    public Optional<ToiletsComments> getOne(Long toiletId, Long commentId) {
        return toiletsCommentsRepository.findByToiletsIdAndId(toiletId, commentId);
    }


    public ToiletsComments save(ToiletsCommentsRequestDto request) {
        return toiletsCommentsRepository.save(request.toEntity());
    }
    public ToiletsComments update(Long CommentsId, ToiletsCommentsRequestDto data) {
        ToiletsComments existingData = toiletsCommentsRepository.findByToiletsIdAndId(data.getToiletsId(), CommentsId)
                .orElseThrow(() -> new RuntimeException("not found with id " + CommentsId));

        // 부분 업데이트: null이 아닌 값만 업데이트
        if (data.getRating() != null) {
            existingData.setRating(data.getRating());
        }
        if (data.getComment() != null) {
            existingData.setComment(data.getComment());
        }

        ToiletsComments updated = toiletsCommentsRepository.save(existingData);
        return updated;
    }
    public Boolean delete(Long toiletId, Long commentId) {
        // need chk
        toiletsCommentsRepository.deleteById(commentId);
        return Boolean.TRUE;
    }
}
