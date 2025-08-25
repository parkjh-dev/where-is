package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.TrashLike;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TrashLikeRepository extends JpaRepository<TrashLike, Long> {
    List<TrashLike> findByTrashId(Long trashId);
    Optional<TrashLike> findByUserIdAndTrashId(Long userId, Long trashId);
}
