package com.hudzaifah.Rest_steganografi.service.impl;

import com.hudzaifah.Rest_steganografi.constant.UserRole;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.dto.role.request.RoleRequest;
import com.hudzaifah.Rest_steganografi.model.dto.role.response.RoleResponse;
import com.hudzaifah.Rest_steganografi.model.entity.Role;
import com.hudzaifah.Rest_steganografi.repository.RoleRepository;
import com.hudzaifah.Rest_steganografi.service.RoleService;
import com.hudzaifah.Rest_steganografi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        Role role = mapper.toEntity(request);
        roleRepository.save(role);
        return mapper.toResponse(role);
    }

    @Override
    public RoleResponse readById(ByIdRequest request) {
        return mapper.toResponse(roleRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("Role not found")));
    }

    @Override
    public RoleResponse update(RoleRequest request) {
        Role roleReq = mapper.toEntity(request);
        Role role = roleRepository.findRoleByUserRole(roleReq.getUserRole()).orElseThrow(()-> new RuntimeException("Role not found"));
        if(roleReq.getUserRole() != null) role.setUserRole(roleReq.getUserRole());
        roleRepository.save(role);
        return mapper.toResponse(role);
    }

    @Override
    public RoleResponse delete(ByIdRequest request) {
        Role role = roleRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("Role not found"));
        roleRepository.delete(role);
        return mapper.toResponse(role);
    }

    @Override
    public Role readByName(String name) {
        return roleRepository.findRoleByUserRole(UserRole.valueOf(name)).orElseThrow(()-> new RuntimeException("Role not found"));
    }

    @Override
    public Role checkIsExiftAndSave(String name) {
        Optional<Role> existRole = roleRepository.findRoleByUserRole(UserRole.valueOf(name));
        if(existRole.isPresent()) return existRole.get();
        Role role = new Role();
        role.setUserRole(UserRole.valueOf(name));
        return roleRepository.save(role);
    }

    @Override
    public List<RoleResponse> readAll() {
        List<Role> roles = roleRepository.findAll();
//        System.out.println(roles);
        return roles.stream().map(mapper::toResponse).toList();
    }
}
