package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "trash_can_area_comments")
public class TrashComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    @Column(name = "trash_can_area_id")
    private Long trashCanId;
    @Column(name = "user_id")
    private Long userId;
    private Long rating;
    private String comment;
    @JsonIgnore
    @Column(name = "mod_dt", updatable = true, insertable = false)
    private LocalDateTime modDt;
    @JsonIgnore
    @Column(name = "add_dt", updatable = false, insertable = false)
    private LocalDateTime addDt;

    public TrashComments() {}

    public TrashComments(
            Long trashCanId, Long userId, Long rating,
            String comment, LocalDateTime modDt, LocalDateTime addDt
    ) {
        this.trashCanId = trashCanId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.modDt = modDt;
        this.addDt = addDt;
    }
}
