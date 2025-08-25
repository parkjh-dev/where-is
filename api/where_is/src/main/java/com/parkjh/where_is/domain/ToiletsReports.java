package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "toilets_area_reports")
public class ToiletsReports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    @Column(name = "toilets_area_id")
    private Long toiletsAreaId;
    @Column(name = "user_id")
    private Long userId;
    private String category;
    private String contents;
    @JsonIgnore
    @Column(name = "mod_dt", updatable = true, insertable = false)
    private LocalDateTime modDt;
    @JsonIgnore
    @Column(name = "add_dt", updatable = false, insertable = false)
    private LocalDateTime addDt;

    public ToiletsReports() {}

    public ToiletsReports(
            Long toiletsAreaId, Long userId, String category,
            String contents, LocalDateTime modDt, LocalDateTime addDt
    ) {
        this.toiletsAreaId = toiletsAreaId;
        this.userId = userId;
        this.category = category;
        this.contents = contents;
        this.modDt = modDt;
        this.addDt = addDt;
    }
}
