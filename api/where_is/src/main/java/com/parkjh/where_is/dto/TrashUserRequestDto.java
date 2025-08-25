package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.TrashRequests;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrashUserRequestDto extends ToiletsRequestDto {
    public TrashUserRequestDto() {}

    public TrashUserRequestDto(
            String name, String longitude, String latitude,
            String managingAgency, String agencyContact, String roadnmAddr,
            String lotnoAddr, String operatingHours
    ) {
        super(name, longitude, latitude, managingAgency, agencyContact, roadnmAddr, lotnoAddr, operatingHours);
    }

    public TrashRequests toRequestsEntity() {
        return new TrashRequests(
                this.name, this.longitude, this.latitude,
                this.managingAgency, this.agencyContact, this.roadnmAddr,
                this.lotnoAddr, this.operatingHours,
                null, null
        );
    }
}
