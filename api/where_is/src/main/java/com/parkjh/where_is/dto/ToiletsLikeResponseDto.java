package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.Toilets;
import com.parkjh.where_is.domain.ToiletsLike;
import com.parkjh.where_is.domain.User;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ToiletsLikeResponseDto {
    private Long id;
    private User user;
    private Toilets toilets;
    private LocalDateTime addDt;

    public ToiletsLikeResponseDto() {}

    public ToiletsLikeResponseDto(
            Long id, User user, Toilets toilets, LocalDateTime addDt
    ) {
        this.id = id;
        this.user = user;
        this.toilets = toilets;
        this.addDt = addDt;
    }

    public static ToiletsLikeResponseDto toResponseDto(ToiletsLike entity) {
        return new ToiletsLikeResponseDto(
                entity.getId(),
                entity.getUser(),
                entity.getToilets(),
                entity.getAddDt()

        );
    }
}
