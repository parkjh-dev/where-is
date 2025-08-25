package com.parkjh.where_is.dto;

import lombok.Getter;

@Getter
public class RoleResponseDto {
    private Long id;
    private String name;

    public RoleResponseDto(long id, String name) {
        this.id = id;
        this.name = name;
    }
}