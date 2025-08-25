package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.Trash;
import com.parkjh.where_is.domain.TrashLike;
import com.parkjh.where_is.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TrashLikeResponseDto {
    private Long id;
    private User user;
    private Trash trash;
    private LocalDateTime addDt;

    public TrashLikeResponseDto() {}

    public TrashLikeResponseDto(
            Long id, User user, Trash trash, LocalDateTime addDt
    ) {
        this.id = id;
        this.user = user;
        this.trash = trash;
        this.addDt = addDt;
    }

    public static TrashLikeResponseDto toResponseDto(TrashLike entity) {
        return new TrashLikeResponseDto(
                entity.getId(),
                entity.getUser(),
                entity.getTrash(),
                entity.getAddDt()

        );
    }
}
