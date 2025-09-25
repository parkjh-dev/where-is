package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.ToiletsRequests;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToiletsUserRequestDto extends ToiletsRequestDto {
    public ToiletsUserRequestDto() {}
    
    public ToiletsUserRequestDto(
            String name, String longitude, String latitude,
            String managingAgency, String agencyContact, String roadnmAddr,
            String lotnoAddr, String operatingHours, String facilityImage
    ) {
        super(name, longitude, latitude, managingAgency, agencyContact, roadnmAddr, lotnoAddr, operatingHours, facilityImage);
    }

    public ToiletsRequests toRequestsEntity() {
        return new ToiletsRequests(
                this.name, this.longitude, this.latitude,
                this.managingAgency, this.agencyContact, this.roadnmAddr,
                this.lotnoAddr, this.operatingHours, this.facilityImage,
                null, null
        );
    }
}
