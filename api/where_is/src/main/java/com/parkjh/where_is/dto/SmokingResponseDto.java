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
    private LocalDateTime modDt;
    private LocalDateTime addDt;

    public SmokingResponseDto() {}

    public SmokingResponseDto(
            Long id, String name, String longitude,
            String latitude, String managingAgency, String agencyContact,
            String roadnmAddr, String lotnoAddr, String operatingHours,
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
        this.modDt = modDt;
        this.addDt = addDt;
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
                entity.getAddDt(),
                entity.getModDt()
        );
    }
}
