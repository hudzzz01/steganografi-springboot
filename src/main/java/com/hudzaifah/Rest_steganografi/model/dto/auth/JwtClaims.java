package com.hudzaifah.Rest_steganografi.model.dto.auth;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtClaims {
    private String userAccountId;
    private List<String> roles;
}
