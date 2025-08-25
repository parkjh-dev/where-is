package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "smoking_area_comments")
public class SmokingComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    @Column(name = "smoking_area_id")
    private Long smokingId;
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

    public SmokingComments() {}

    public SmokingComments(
            Long smokingId, Long userId, Long rating,
            String comment, LocalDateTime modDt, LocalDateTime addDt
    ) {
        this.smokingId = smokingId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.modDt = modDt;
        this.addDt = addDt;
    }
}
