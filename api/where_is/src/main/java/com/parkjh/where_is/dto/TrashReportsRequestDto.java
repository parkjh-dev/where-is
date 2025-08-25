package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.TrashReports;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrashReportsRequestDto {
    private Long trashId;
    private Long userId;
    private String contents;
    private String category;

    public TrashReportsRequestDto() {}

    public TrashReportsRequestDto(
            Long trashId, Long userId,
            String contents, String category
    ) {
        this.trashId = trashId;
        this.userId = userId;
        this.contents = contents;
        this.category = category;
    }
    public TrashReports toEntity() {
        return new TrashReports(
                this.trashId, this.userId, this.contents,
                this.category, null, null
        );
    }
}
