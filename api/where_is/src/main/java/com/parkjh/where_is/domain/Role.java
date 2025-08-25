package com.parkjh.where_is.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "role") // DB 테이블 이름
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;
    private String name;

    // 기본 생성자
    protected Role() {}

    // 생성자
    public Role(String name) {
        this.name = name;
    }
}