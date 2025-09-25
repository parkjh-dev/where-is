package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.SmokingRequests;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmokingUserRequestDto extends SmokingRequestDto {
    public SmokingUserRequestDto() {}

    public SmokingUserRequestDto(
            String name, String longitude, String latitude,
            String managingAgency, String agencyContact, String roadnmAddr,
            String lotnoAddr, String operatingHours, String facilityImage
    ) {
        super(name, longitude, latitude, managingAgency, agencyContact, roadnmAddr, lotnoAddr, operatingHours, facilityImage);
    }

    public SmokingRequests toRequestsEntity() {
        return new SmokingRequests(
                this.name, this.longitude, this.latitude,
                this.managingAgency, this.agencyContact, this.roadnmAddr,
                this.lotnoAddr, this.operatingHours, this.facilityImage,
                null, null
        );
    }
}
