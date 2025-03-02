package com.hudzaifah.Rest_steganografi.service;

import com.hudzaifah.Rest_steganografi.model.dto.auth.JwtClaims;
import com.hudzaifah.Rest_steganografi.model.dto.auth.RequestJwt;
import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;


public interface JwtService {
    String generateToken(RequestJwt userAccountJwt);
    boolean verifyJwtToken(String token);
    JwtClaims getClaimsByToken(String token);
}
