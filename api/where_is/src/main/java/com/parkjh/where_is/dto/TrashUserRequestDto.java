package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.TrashRequests;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrashUserRequestDto extends TrashRequestDto {
    public TrashUserRequestDto() {}

    public TrashUserRequestDto(
            String name, String longitude, String latitude,
            String managingAgency, String agencyContact, String roadnmAddr,
            String lotnoAddr, String operatingHours, String facilityImage
    ) {
        super(
                name, longitude, latitude, managingAgency, agencyContact,
                roadnmAddr, lotnoAddr, operatingHours, facilityImage
        );
    }

    public TrashRequests toRequestsEntity() {
        return new TrashRequests(
                this.name, this.longitude, this.latitude,
                this.managingAgency, this.agencyContact, this.roadnmAddr,
                this.lotnoAddr, this.operatingHours, this.facilityImage,
                null, null
        );
    }
}
