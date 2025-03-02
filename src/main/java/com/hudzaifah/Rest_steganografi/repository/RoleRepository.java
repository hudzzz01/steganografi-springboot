package com.hudzaifah.Rest_steganografi.repository;

import com.hudzaifah.Rest_steganografi.constant.UserRole;
import com.hudzaifah.Rest_steganografi.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findRoleByUserRole(UserRole userRole);
}
