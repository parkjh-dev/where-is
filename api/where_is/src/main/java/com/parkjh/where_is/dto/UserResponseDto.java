package com.parkjh.where_is.dto;

import com.parkjh.where_is.domain.User;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int roleId;
    private String nickname;
    private LocalDateTime addDt;
    private LocalDateTime modDt;
    private String profileImag;
    private LocalDate birthday;
    private Integer isLocked;
    private Integer loginFailCnt;
    private LocalDateTime lastLoginFailDt;
    private LocalDateTime lastLoginSuccessDt;

    public UserResponseDto(
            Long id, String name, String email, String phone, int roleId, String nickname,
            String profileImag, LocalDate birthday,
            Integer isLocked, Integer loginFailCnt, LocalDateTime lastLoginFailDt, LocalDateTime lastLoginSuccessDt,
            LocalDateTime addDt, LocalDateTime modDt

    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
        this.nickname = nickname;
        this.addDt = addDt;
        this.modDt = modDt;
        this.profileImag = profileImag;
        this.birthday = birthday;

        this.isLocked = isLocked;
        this.loginFailCnt = loginFailCnt;
        this.lastLoginFailDt = lastLoginFailDt;
        this.lastLoginSuccessDt = lastLoginSuccessDt;
    }

    public static UserResponseDto toResponseDto(User entity) {
        return new UserResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getRoleId(),
                entity.getNickname(),
                entity.getProfileImag(),
                entity.getBirthday(),
                entity.getIsLocked(),
                entity.getLoginFailCnt(),
                entity.getLastLoginFailDt(),
                entity.getLastLoginSuccessDt(),
                entity.getAddDt(),
                entity.getModDt()
        );
    }
}