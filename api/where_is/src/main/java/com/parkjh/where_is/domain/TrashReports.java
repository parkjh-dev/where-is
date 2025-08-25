package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "trash_can_area_reports")
public class TrashReports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    @Column(name = "trash_can_area_id")
    private Long trashCanAreaId;
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

    public TrashReports() {}

    public TrashReports(
            Long trashCanAreaId, Long userId, String category,
            String contents, LocalDateTime modDt, LocalDateTime addDt
    ) {
        this.trashCanAreaId = trashCanAreaId;
        this.userId = userId;
        this.category = category;
        this.contents = contents;
        this.modDt = modDt;
        this.addDt = addDt;
    }
}
