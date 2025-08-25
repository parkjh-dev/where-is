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
@Table(name = "toilets_area_likes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ToiletsLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toilets_area_id")
    private Toilets toilets;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @Column(name = "add_dt", updatable = false, insertable = false)
    private LocalDateTime addDt;

    // 기본 생성자
    public ToiletsLike() {}

    public ToiletsLike(Toilets toilets, User user) {
        this.toilets = toilets;
        this.user = user;
    }

    public ToiletsLike(Toilets toilets, User user, LocalDateTime addDt) {
        this.toilets = toilets;
        this.user = user;
        this.addDt = addDt;
    }
}
