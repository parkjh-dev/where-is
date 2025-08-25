package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.TrashRequests;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class TrashUserResponseDto extends TrashResponseDto{
    public TrashUserResponseDto() {}

    public TrashUserResponseDto(
            Long id, String name, String longitude,
            String latitude, String managingAgency, String agencyContact,
            String roadnmAddr, String lotnoAddr, String operatingHours,
            LocalDateTime modDt, LocalDateTime addDt
    ) {
        super(
                id, name, longitude,
                latitude, managingAgency, agencyContact,
                roadnmAddr, lotnoAddr, operatingHours,
                modDt, addDt
        );
    }

    public static TrashUserResponseDto toResponseDto(TrashRequests entity) {
        return new TrashUserResponseDto(
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
