package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.User;
import lombok.Getter;
import java.time.LocalDate;

public class UserRequestDto {
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String phone;
    @Getter
    private String pw;
    @Getter
    private int roleId;
    @Getter
    private String nickname;
    @Getter
    private String profileImag;
    @Getter
    private LocalDate birthday;

    // 생성자
    public UserRequestDto(
            String name, String email, String phone, String pw, int roleId, String nickname,
            String profileImag, LocalDate birthday
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pw = pw;
        this.roleId = roleId;
        this.nickname = nickname;
        this.profileImag = profileImag;
        this.birthday = birthday;
    }

    public User toEntity() {
        return new User(
                this.name, this.email, this.roleId,
                this.phone, this.pw, this.nickname,
                this.profileImag, this.birthday,
                null, null, 0, 0,
                null, null
        );
    }
}


