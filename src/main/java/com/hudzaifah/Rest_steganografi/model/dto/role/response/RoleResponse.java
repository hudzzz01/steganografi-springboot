package com.hudzaifah.Rest_steganografi.model.dto.role.response;

import com.hudzaifah.Rest_steganografi.constant.UserRole;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.response.UserAccountResponse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleResponse {
    private String id;
    private UserRole role;
}
