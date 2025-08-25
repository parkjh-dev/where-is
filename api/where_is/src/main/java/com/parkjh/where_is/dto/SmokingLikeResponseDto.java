package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SmokingLikeResponseDto {
    private Long id;
    private User user;
    private Smoking smoking;
    private LocalDateTime addDt;

    public SmokingLikeResponseDto() {}

    public SmokingLikeResponseDto(
            Long id, User user, Smoking smoking, LocalDateTime addDt
    ) {
        this.id = id;
        this.user = user;
        this.smoking = smoking;
        this.addDt = addDt;
    }

    public static SmokingLikeResponseDto toResponseDto(SmokingLike entity) {
        return new SmokingLikeResponseDto(
                entity.getId(),
                entity.getUser(),
                entity.getSmoking(),
                entity.getAddDt()

        );
    }
}
