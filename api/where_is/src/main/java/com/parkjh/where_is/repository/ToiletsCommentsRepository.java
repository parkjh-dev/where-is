package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.ToiletsComments;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ToiletsCommentsRepository extends JpaRepository<ToiletsComments, Long> {
    Optional<ToiletsComments> findByToiletsIdAndId(Long toiletId, Long commentId);
    List<ToiletsComments> findByToiletsId(Long toiletsId);
}
