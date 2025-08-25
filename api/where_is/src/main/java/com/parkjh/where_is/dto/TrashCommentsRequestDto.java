package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.TrashComments;
import lombok.Getter;
import lombok.Setter;

@Getter
public class TrashCommentsRequestDto {
    @Setter
    private Long trashId;
    private Long userId;
    private Long rating;
    private String comment;

    public TrashCommentsRequestDto() {}

    public TrashCommentsRequestDto(
            Long userId, Long rating, String comment
    ) {
        this.userId = userId;
        this.rating = rating ;
        this.comment = comment;
    }

    public TrashComments toEntity() {
        return new TrashComments(
                this.trashId, this.userId,
                this.rating, this.comment,
                null, null
        );
    }
}
