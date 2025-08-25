package com.parkjh.where_is.controller;

import com.parkjh.where_is.domain.Role;
import com.parkjh.where_is.dto.ApiResponseDto;
import com.parkjh.where_is.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
@Tag(name = "Role", description = "역할 관리 API")
public class RoleController extends BaseController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) { this.roleService = roleService; }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> readRoles() {
        ApiResponseDto<List<Role>> response = new ApiResponseDto<>(200, "SUCCESS", roleService.getList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponseDto<?>> readRole(@PathVariable Long roleId) {
        Optional<Role> role = roleService.getOne(roleId);

        if (role.isPresent()) {
            ApiResponseDto<Role> response = new ApiResponseDto<>(200, "SUCCESS", role.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
