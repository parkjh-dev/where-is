package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "smocking_area_reports")
public class SmokingReports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    @Column(name = "smoking_area_id")
    private Long smokingAreaId;
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

    public SmokingReports() {}

    public SmokingReports(
            Long smokingAreaId, Long userId, String category,
            String contents, LocalDateTime modDt, LocalDateTime addDt
    ) {
        this.smokingAreaId = smokingAreaId;
        this.userId = userId;
        this.category = category;
        this.contents = contents;
        this.modDt = modDt;
        this.addDt = addDt;
    }
}
