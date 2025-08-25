package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.SmokingReports;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmokingReportsRequestDto {
    private Long smokingId;
    private Long userId;
    private String contents;
    private String category;

    public SmokingReportsRequestDto() {}

    public SmokingReportsRequestDto(
            Long smokingId, Long userId,
            String contents, String category
    ) {
        this.smokingId = smokingId;
        this.userId = userId;
        this.contents = contents;
        this.category = category;
    }
    public SmokingReports toEntity() {
        return new SmokingReports(
                this.smokingId, this.userId, this.contents,
                this.category, null, null
        );
    }
}
