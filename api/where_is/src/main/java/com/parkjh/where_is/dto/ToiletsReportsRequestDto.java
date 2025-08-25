package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.ToiletsReports;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToiletsReportsRequestDto {
    private Long toiletId;
    private Long userId;
    private String contents;
    private String category;

    public ToiletsReportsRequestDto() {}

    public ToiletsReportsRequestDto(
            Long toiletId, Long userId,
            String contents, String category
    ) {
        this.toiletId = toiletId;
        this.userId = userId;
        this.contents = contents;
        this.category = category;
    }
    public ToiletsReports toEntity() {
        return new ToiletsReports(
                this.toiletId, this.userId, this.contents,
                this.category, null, null
        );
    }
}
