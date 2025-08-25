package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.TrashComments;
import com.parkjh.where_is.dto.TrashCommentsRequestDto;
import com.parkjh.where_is.repository.TrashCommentsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrashCommentsService {
    private final TrashCommentsRepository trashCommentsRepository;
    public TrashCommentsService(TrashCommentsRepository trashCommentsRepository) {
        this.trashCommentsRepository = trashCommentsRepository;
    }

    public List<TrashComments> getList(Long trashId) {
        return trashCommentsRepository.findByTrashCanId(trashId);
    }

    public Optional<TrashComments> getOne(Long trashId, Long commentId) {
        return trashCommentsRepository.findByTrashCanIdAndId(trashId, commentId);
    }


    public TrashComments save(TrashCommentsRequestDto request) {
        return trashCommentsRepository.save(request.toEntity());
    }
    public TrashComments update(Long CommentsId, TrashCommentsRequestDto data) {
        TrashComments existingData = trashCommentsRepository.findByTrashCanIdAndId(data.getTrashId(), CommentsId)
                .orElseThrow(() -> new RuntimeException("not found with id " + CommentsId));

        // 부분 업데이트: null이 아닌 값만 업데이트
        if (data.getRating() != null) {
            existingData.setRating(data.getRating());
        }
        if (data.getComment() != null) {
            existingData.setComment(data.getComment());
        }

        TrashComments updated = trashCommentsRepository.save(existingData);
        return updated;
    }
    public Boolean delete(Long trashId, Long commentId) {
        // need chk
        trashCommentsRepository.deleteById(commentId);
        return Boolean.TRUE;
    }
}
