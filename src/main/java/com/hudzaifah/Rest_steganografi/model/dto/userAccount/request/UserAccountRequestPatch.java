package com.hudzaifah.Rest_steganografi.model.dto.userAccount.request;

import com.hudzaifah.Rest_steganografi.model.dto.role.request.RoleRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserAccountRequestPatch {
    private String id;
    private String username;
    private String password;
    private String name;
    private List<RoleRequest> roles;
}
