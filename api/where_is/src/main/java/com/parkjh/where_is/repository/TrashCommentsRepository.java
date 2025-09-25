package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.TrashComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.Collection;

public interface TrashCommentsRepository extends JpaRepository<TrashComments, Long> {
    Optional<TrashComments> findByTrashCanIdAndId(Long trashId, Long commentId);
    List<TrashComments> findByTrashCanId(Long trashId);

    long countByTrashCanId(Long trashId);

    @Query("select avg(c.rating) from TrashComments c where c.trashCanId = :trashId")
    Double findAverageRatingByTrashId(@Param("trashId") Long trashId);

    @Query("select c.trashCanId, count(c) from TrashComments c where c.trashCanId in :ids group by c.trashCanId")
    List<Object[]> countGroupByTrashId(@Param("ids") Collection<Long> trashIds);

    @Query("select c.trashCanId, avg(c.rating) from TrashComments c where c.trashCanId in :ids group by c.trashCanId")
    List<Object[]> averageGroupByTrashId(@Param("ids") Collection<Long> trashIds);
}
