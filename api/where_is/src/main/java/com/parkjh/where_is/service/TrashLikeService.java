package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Trash;
import com.parkjh.where_is.domain.TrashLike;
import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.TrashLikeResponseDto;
import com.parkjh.where_is.repository.TrashLikeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrashLikeService {
    private final TrashLikeRepository trashLikeRepository;
    public TrashLikeService(TrashLikeRepository trashLikeRepository) {
        this.trashLikeRepository = trashLikeRepository;
    }

    public TrashLikeResponseDto save(User user, Trash request) {
        TrashLike entity = new TrashLike(request , user);
        TrashLike created = trashLikeRepository.save(entity);
        return TrashLikeResponseDto.toResponseDto(created);
    }

    public List<TrashLike> getList(Long trashId) {
        return trashLikeRepository.findByTrashId(trashId);
    }

    public Boolean delete(Long userId, Long trashId) {
        Optional<TrashLike> existingId = trashLikeRepository.findByUserIdAndTrashId(userId, trashId);

        if (existingId.isEmpty()) {
            throw new RuntimeException("not found");
        }
        trashLikeRepository.delete(existingId.get());
        return Boolean.TRUE;
    }
}
