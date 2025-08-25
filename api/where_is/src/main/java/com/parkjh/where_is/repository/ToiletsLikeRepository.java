package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.ToiletsLike;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ToiletsLikeRepository extends JpaRepository<ToiletsLike, Long> {
    List<ToiletsLike> findByToiletsId(Long toiletsId);
    Optional<ToiletsLike> findByUserIdAndToiletsId(Long userId, Long toiletId);
}
