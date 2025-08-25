package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.SmokingComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SmokingCommentsRepository extends JpaRepository<SmokingComments, Long> {
    Optional<SmokingComments> findBySmokingIdAndId(Long toiletId, Long commentId);
    List<SmokingComments> findBySmokingId(Long toiletsId);
}
