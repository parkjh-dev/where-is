package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.ToiletsRequests;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ToiletsUserResponseDto extends ToiletsResponseDto{
    public ToiletsUserResponseDto() {}

    public ToiletsUserResponseDto(
            Long id, String name, String longitude,
            String latitude, String managingAgency, String agencyContact,
            String roadnmAddr, String lotnoAddr, String operatingHours,
            String facilityImage,
            LocalDateTime modDt, LocalDateTime addDt
    ) {
        super(
                id, name, longitude,
                latitude, managingAgency, agencyContact,
                roadnmAddr, lotnoAddr, operatingHours,
                facilityImage,
                modDt, addDt
        );
    }

    public static ToiletsUserResponseDto toResponseDto(ToiletsRequests entity) {
        return new ToiletsUserResponseDto(
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
}
