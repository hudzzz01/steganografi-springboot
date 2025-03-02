package com.hudzaifah.Rest_steganografi.model.dto.role.request;

import com.hudzaifah.Rest_steganografi.constant.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleRequest {
    @NotBlank
    @NotNull
    private String stringUserROle;
}
