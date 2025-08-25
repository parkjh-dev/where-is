package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.ToiletsComments;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ToiletsCommentsRequestDto {
    @Setter
    private Long toiletsId;
    private Long userId;
    private Long rating;
    private String comment;

    public ToiletsCommentsRequestDto() {}

    public ToiletsCommentsRequestDto(
            Long userId, Long rating, String comment
    ) {
        this.userId = userId;
        this.rating = rating ;
        this.comment = comment;
    }

    public ToiletsComments toEntity() {
        return new ToiletsComments(
                this.toiletsId, this.userId,
                this.rating, this.comment,
                null, null
        );
    }
}
