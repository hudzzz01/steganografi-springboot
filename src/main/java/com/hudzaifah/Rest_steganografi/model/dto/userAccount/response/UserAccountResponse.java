package com.hudzaifah.Rest_steganografi.model.dto.userAccount.response;

import com.hudzaifah.Rest_steganografi.model.dto.role.response.RoleResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserAccountResponse {
    private String id;
    private String username;
    private String password;
    private String name;
    private List<RoleResponse> roles;
}
