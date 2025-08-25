package com.parkjh.where_is.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserSearchDto {
    private long size;
    private long page;
    private String order;
    private String sort;
    private String name;
    private String phone;
    private String nickname;
    private String email;
    private LocalDate birthday;

    // 기본 생성자 (Spring Boot 파라미터 바인딩용)
    public UserSearchDto() {}

    public UserSearchDto(
            long page, long size, String order, String sort,
            String name, String phone, String nickname, String email, LocalDate birthday
    ) {
        this.page = page;
        this.size = size;
        this.order = order;
        this.sort = sort;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;
    }
}
