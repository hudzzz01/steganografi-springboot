package com.hudzaifah.Rest_steganografi.service;

import com.hudzaifah.Rest_steganografi.model.dto.auth.JwtClaims;
import com.hudzaifah.Rest_steganografi.model.dto.auth.LoginRequest;
import com.hudzaifah.Rest_steganografi.model.dto.auth.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
    JwtClaims decodeJWT(String token);
    Boolean verifyJwtToken(String token);
}
