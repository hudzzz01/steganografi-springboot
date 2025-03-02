package com.hudzaifah.Rest_steganografi.service;

import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.dto.role.request.RoleRequest;
import com.hudzaifah.Rest_steganografi.model.dto.role.response.RoleResponse;
import com.hudzaifah.Rest_steganografi.model.entity.Role;

import java.util.List;

public interface RoleService {

    RoleResponse create(RoleRequest request);
    RoleResponse readById(ByIdRequest request);
    RoleResponse update(RoleRequest request);
    RoleResponse delete(ByIdRequest request);

    Role readByName(String name);

    Role checkIsExiftAndSave(String name);

    List<RoleResponse> readAll();

}
