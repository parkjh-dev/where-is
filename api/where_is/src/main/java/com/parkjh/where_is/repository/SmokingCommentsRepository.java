package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.SmokingComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Collection;

public interface SmokingCommentsRepository extends JpaRepository<SmokingComments, Long> {
    Optional<SmokingComments> findBySmokingIdAndId(Long toiletId, Long commentId);
    List<SmokingComments> findBySmokingId(Long toiletsId);

    long countBySmokingId(Long smokingId);

    @Query("select avg(c.rating) from SmokingComments c where c.smokingId = :smokingId")
    Double findAverageRatingBySmokingId(@Param("smokingId") Long smokingId);

    @Query("select c.smokingId, count(c) from SmokingComments c where c.smokingId in :ids group by c.smokingId")
    List<Object[]> countGroupBySmokingId(@Param("ids") Collection<Long> smokingIds);

    @Query("select c.smokingId, avg(c.rating) from SmokingComments c where c.smokingId in :ids group by c.smokingId")
    List<Object[]> averageGroupBySmokingId(@Param("ids") Collection<Long> smokingIds);
}
