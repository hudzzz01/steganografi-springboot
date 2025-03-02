package com.hudzaifah.Rest_steganografi.model.dto.userAccount.request;

import com.hudzaifah.Rest_steganografi.model.dto.role.request.RoleRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserAccountRequest {
    private String username;
    private String password;
    private String name;
    private List<RoleRequest> roles;
}
