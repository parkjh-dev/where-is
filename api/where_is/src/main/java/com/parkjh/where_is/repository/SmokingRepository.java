package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.Smoking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SmokingRepository extends JpaRepository<Smoking, Long> {
    Page<Smoking> findAll(Pageable pageable);
    @Query("SELECT u FROM Toilets u WHERE " +
            "(:name IS NULL OR u.name LIKE %:name%) AND " +
            "(:longitude IS NULL OR u.longitude LIKE %:longitude%) AND " +
            "(:latitude IS NULL OR u.latitude LIKE %:latitude%) AND " +
            "(:managingAgency IS NULL OR u.managingAgency LIKE %:managingAgency%) AND " +
            "(:agencyContact IS NULL OR u.agencyContact LIKE %:agencyContact%) AND " +
            "(:roadnmAddr IS NULL OR u.roadnmAddr LIKE %:roadnmAddr%) AND " +
            "(:lotnoAddr IS NULL OR u.lotnoAddr LIKE %:lotnoAddr%) AND " +
            "(:operatingHours IS NULL OR u.operatingHours = :operatingHours)")
    Page<Smoking> findSmokingWithSearchCriteria(
            @Param("name") String name,
            @Param("longitude") String longitude,
            @Param("latitude") String latitude,
            @Param("managingAgency") String managingAgency,
            @Param("agencyContact") String agencyContact,
            @Param("roadnmAddr") String roadnmAddr,
            @Param("lotnoAddr") String lotnoAddr,
            @Param("operatingHours") String operatingHours,
            Pageable pageable
    );
}
