package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.TrashComments;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TrashCommentsRepository extends JpaRepository<TrashComments, Long> {
    Optional<TrashComments> findByTrashCanIdAndId(Long trashId, Long commentId);
    List<TrashComments> findByTrashCanId(Long trashId);
}
