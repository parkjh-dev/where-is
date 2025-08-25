package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.Trash;
import lombok.Getter;

@Getter
public class TrashRequestDto {
    protected String name;
    protected String longitude;
    protected String latitude;
    protected String managingAgency;
    protected String agencyContact;
    protected String roadnmAddr;
    protected String lotnoAddr;
    protected String operatingHours;

    // 기본 생성자 (Spring Boot 파라미터 바인딩용)
    public TrashRequestDto() {}

    public TrashRequestDto(
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

    public Trash toEntity() {
        return new Trash(
                this.name, this.longitude, this.latitude,
                this.managingAgency, this.agencyContact, this.roadnmAddr,
                this.lotnoAddr, this.operatingHours,
                null, null
        );
    }
}
