package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.ToiletsComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.Collection;

public interface ToiletsCommentsRepository extends JpaRepository<ToiletsComments, Long> {
    Optional<ToiletsComments> findByToiletsIdAndId(Long toiletId, Long commentId);
    List<ToiletsComments> findByToiletsId(Long toiletsId);

    long countByToiletsId(Long toiletsId);

    @Query("select avg(c.rating) from ToiletsComments c where c.toiletsId = :toiletsId")
    Double findAverageRatingByToiletsId(@Param("toiletsId") Long toiletsId);

    @Query("select c.toiletsId, count(c) from ToiletsComments c where c.toiletsId in :ids group by c.toiletsId")
    List<Object[]> countGroupByToiletsId(@Param("ids") Collection<Long> toiletsIds);

    @Query("select c.toiletsId, avg(c.rating) from ToiletsComments c where c.toiletsId in :ids group by c.toiletsId")
    List<Object[]> averageGroupByToiletsId(@Param("ids") Collection<Long> toiletsIds);
}
