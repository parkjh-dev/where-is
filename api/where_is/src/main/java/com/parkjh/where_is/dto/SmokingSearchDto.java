package com.parkjh.where_is.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SmokingSearchDto {
    private long size;
    private long page;
    private String order;
    private String sort;
    private String name;
    private String longitude;
    private String latitude;
    private String managingAgency;
    private String agencyContact;
    private String roadnmAddr;
    private String lotnoAddr;
    private String operatingHours;

    public SmokingSearchDto() {}

    public SmokingSearchDto(
             long size, long page, String order, String sort,
             String name, String longitude, String latitude, String managingAgency,
             String agencyContact, String roadnmAddr, String lotnoAddr, String operatingHours
    ) {
        this.size = size;
        this.page = page;
        this.order = order;
        this.sort = sort;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.managingAgency = managingAgency;
        this.agencyContact = agencyContact;
        this.roadnmAddr = roadnmAddr;
        this.lotnoAddr = lotnoAddr;
        this.operatingHours = operatingHours;
    }
}
