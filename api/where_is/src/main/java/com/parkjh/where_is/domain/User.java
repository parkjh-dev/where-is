package com.parkjh.where_is.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user") // DB 테이블 이름
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String pw;
    private int roleId;
    private String nickname;
    private String profileImag;
    private LocalDate birthday;
    @Column(name = "is_locked")
    private Integer isLocked;
    @Column(name = "login_fail_cnt")
    private Integer loginFailCnt;
    @Column(name = "last_login_fail_dt")
    private LocalDateTime lastLoginFailDt;
    @Column(name = "last_login_success_dt")
    private LocalDateTime lastLoginSuccessDt;
    @JsonIgnore
    @Column(name = "mod_dt", updatable = true, insertable = false)
    private LocalDateTime modDt;
    @JsonIgnore
    @Column(name = "add_dt", updatable = false, insertable = false)
    private LocalDateTime addDt;

    // ToiletsLike와의 관계 (1:N)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ToiletsLike> toiletsLikes;

    // ToiletsLike와의 관계 (1:N)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SmokingLike> smokingLikes;

    // 기본 생성자
    protected User() {}

    // 생성자
    public User(
            String name, String email, int roleId, String phone, String pw,
            String nickname, String profileImag, LocalDate birthday,
            LocalDateTime addDt, LocalDateTime modDt,
            Integer isLocked, Integer loginFailCnt, LocalDateTime lastLoginFailDt, LocalDateTime lastLoginSuccessDt
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
        this.pw = pw;
        this.nickname = nickname;
        this.profileImag = profileImag;
        this.birthday = birthday;
        this.isLocked = isLocked;
        this.loginFailCnt = loginFailCnt;
        this.lastLoginFailDt = lastLoginFailDt;
        this.lastLoginSuccessDt = lastLoginSuccessDt;
        this.addDt = addDt;
        this.modDt = modDt;
    }
}
