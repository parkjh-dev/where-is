package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.SmokingLike;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SmokingLikeRepository extends JpaRepository<SmokingLike, Long> {
    List<SmokingLike> findBySmokingId(Long smokingId);
    Optional<SmokingLike> findByUserIdAndSmokingId(Long userId, Long smokingId);
}
