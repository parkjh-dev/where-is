package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.Smoking;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SmokingResponseDto {
    private Long id;
    private String name;
    private String longitude; // 경도
    private String latitude; // 위도
    private String managingAgency;
    private String agencyContact;
    private String roadnmAddr;
    private String lotnoAddr;
    private String operatingHours;
    private String facilityImage;
    private LocalDateTime modDt;
    private LocalDateTime addDt;
    private Long reviewCount;
    private Double starRating;

    public SmokingResponseDto() {}

    public SmokingResponseDto(
            Long id, String name, String longitude,
            String latitude, String managingAgency, String agencyContact,
            String roadnmAddr, String lotnoAddr, String operatingHours,
            String facilityImage,
            LocalDateTime modDt, LocalDateTime addDt
    ) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.managingAgency = managingAgency;
        this.agencyContact = agencyContact;
        this.roadnmAddr = roadnmAddr;
        this.lotnoAddr = lotnoAddr;
        this.operatingHours = operatingHours;
        this.facilityImage = facilityImage;
        this.modDt = modDt;
        this.addDt = addDt;
    }

    public SmokingResponseDto(
            Long id, String name, String longitude,
            String latitude, String managingAgency, String agencyContact,
            String roadnmAddr, String lotnoAddr, String operatingHours,
            String facilityImage,
            LocalDateTime modDt, LocalDateTime addDt,
            Long reviewCount, Double starRating
    ) {
        this(id, name, longitude, latitude, managingAgency, agencyContact,
                roadnmAddr, lotnoAddr, operatingHours, facilityImage, modDt, addDt);
        this.reviewCount = reviewCount;
        this.starRating = starRating;
    }

    public static SmokingResponseDto toResponseDto(Smoking entity) {
        return new SmokingResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getLongitude(),
                entity.getLatitude(),
                entity.getManagingAgency(),
                entity.getAgencyContact(),
                entity.getRoadnmAddr(),
                entity.getLotnoAddr(),
                entity.getOperatingHours(),
                entity.getFacilityImage(),
                entity.getAddDt(),
                entity.getModDt()
        );
    }

    public static SmokingResponseDto toResponseDto(Smoking entity, Long reviewCount, Double starRating) {
        Double safeAvg = (starRating == null) ? 0.0 : Math.round(starRating * 10.0) / 10.0;
        return new SmokingResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getLongitude(),
                entity.getLatitude(),
                entity.getManagingAgency(),
                entity.getAgencyContact(),
                entity.getRoadnmAddr(),
                entity.getLotnoAddr(),
                entity.getOperatingHours(),
                entity.getFacilityImage(),
                entity.getAddDt(),
                entity.getModDt(),
                reviewCount,
                safeAvg
        );
    }
}
