package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.SmokingComments;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SmokingCommentsRequestDto {
    @Setter
    private Long smokingId;
    private Long userId;
    private Long rating;
    private String comment;

    public SmokingCommentsRequestDto() {}

    public SmokingCommentsRequestDto(
            Long userId, Long rating, String comment
    ) {
        this.userId = userId;
        this.rating = rating ;
        this.comment = comment;
    }

    public SmokingComments toEntity() {
        return new SmokingComments(
                this.smokingId, this.userId,
                this.rating, this.comment,
                null, null
        );
    }
}
