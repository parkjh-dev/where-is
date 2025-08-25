package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.Role;
import com.parkjh.where_is.repository.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getList() {
        return roleRepository.findAll();
    }
    public Optional<Role> getOne(Long roleId) {
        return roleRepository.findById(roleId);
    }
}