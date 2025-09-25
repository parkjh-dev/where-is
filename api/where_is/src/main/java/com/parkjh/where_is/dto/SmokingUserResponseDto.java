package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.SmokingRequests;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class SmokingUserResponseDto extends SmokingResponseDto{
    public SmokingUserResponseDto() {}

    public SmokingUserResponseDto(
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

    public static SmokingUserResponseDto toResponseDto(SmokingRequests entity) {
        return new SmokingUserResponseDto(
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
