package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.Smoking;
import lombok.Getter;

@Getter
public class SmokingRequestDto {
    protected String name;
    protected String longitude;
    protected String latitude;
    protected String managingAgency;
    protected String agencyContact;
    protected String roadnmAddr;
    protected String lotnoAddr;
    protected String operatingHours;

    // 기본 생성자 (Spring Boot 파라미터 바인딩용)
    public SmokingRequestDto() {}

    public SmokingRequestDto(
            String name, String longitude, String latitude,
            String managingAgency, String agencyContact, String roadnmAddr,
            String lotnoAddr, String operatingHours
    ) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.managingAgency = managingAgency;
        this.agencyContact = agencyContact;
        this.roadnmAddr = roadnmAddr;
        this.lotnoAddr = lotnoAddr;
        this.operatingHours = operatingHours;
    }

    public Smoking toEntity() {
        return new Smoking(
                this.name, this.longitude, this.latitude,
                this.managingAgency, this.agencyContact, this.roadnmAddr,
                this.lotnoAddr, this.operatingHours,
                null, null
        );
    }
}
