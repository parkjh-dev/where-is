package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "smoking_area_likes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SmokingLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smoking_area_id")
    private Smoking smoking;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @Column(name = "add_dt", updatable = false, insertable = false)
    private LocalDateTime addDt;

    // 기본 생성자
    public SmokingLike() {}

    public SmokingLike(Smoking smoking, User user) {
        this.smoking = smoking;
        this.user = user;
    }

    public SmokingLike(Smoking smoking, User user, LocalDateTime addDt) {
        this.smoking = smoking;
        this.user = user;
        this.addDt = addDt;
    }
}
