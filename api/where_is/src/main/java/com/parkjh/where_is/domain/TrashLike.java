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
@Table(name = "trash_can_area_likes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TrashLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trash_can_area_id")
    private Trash trash;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @Column(name = "add_dt", updatable = false, insertable = false)
    private LocalDateTime addDt;

    // 기본 생성자
    public TrashLike() {}

    public TrashLike(Trash trash, User user) {
        this.trash = trash;
        this.user = user;
    }

    public TrashLike(Trash trash, User user, LocalDateTime addDt) {
        this.trash = trash;
        this.user = user;
        this.addDt = addDt;
    }
}
