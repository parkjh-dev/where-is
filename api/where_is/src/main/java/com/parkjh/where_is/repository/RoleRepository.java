package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // JpaRepository 덕분에 기본적인 findAll(), findById() 같은 메서드 자동 제공
}