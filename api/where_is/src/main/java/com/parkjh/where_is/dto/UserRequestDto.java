package com.parkjh.where_is.dto;

import com.google.common.hash.Hashing;
import com.parkjh.where_is.domain.User;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Getter
public class UserRequestDto {
    private String name;
    private String email;
    private String phone;
    private String pw;
    private int roleId;
    private String nickname;
    private String profileImag;
    private LocalDate birthday;

    // 생성자
    public UserRequestDto(
            String name, String email, String phone, String pw, int roleId, String nickname,
            String profileImag, LocalDate birthday
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pw = Hashing.sha256()
                .hashString(pw, StandardCharsets.UTF_8)
                .toString();
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


