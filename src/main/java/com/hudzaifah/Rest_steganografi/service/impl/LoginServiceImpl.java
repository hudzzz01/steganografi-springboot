package com.hudzaifah.Rest_steganografi.service.impl;

import com.hudzaifah.Rest_steganografi.model.dto.auth.JwtClaims;
import com.hudzaifah.Rest_steganografi.model.dto.auth.LoginRequest;
import com.hudzaifah.Rest_steganografi.model.dto.auth.LoginResponse;
import com.hudzaifah.Rest_steganografi.model.dto.auth.RequestJwt;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.dto.role.response.RoleResponse;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.response.UserAccountResponse;
import com.hudzaifah.Rest_steganografi.service.AuthUserService;
import com.hudzaifah.Rest_steganografi.service.JwtService;
import com.hudzaifah.Rest_steganografi.service.LoginService;
import com.hudzaifah.Rest_steganografi.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private JwtService jwtService;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        String userId = userAccountService.checkUsernameAndPassword(username, password);

        UserAccountResponse userAccountResponse = userAccountService.readById(ByIdRequest.builder()
                .id(userId)
                .build());

        RequestJwt requestJwt = RequestJwt.builder()
                .id(userAccountResponse.getId())
                .rolesString(userAccountResponse.getRoles().stream().map(RoleResponse::getRole).map(Enum::name).toList())
                .build();

        String token = jwtService.generateToken(requestJwt);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(token)
                .build();

        return loginResponse;
    }

    @Override
    public JwtClaims decodeJWT(String token) {
        return jwtService.getClaimsByToken(token);
    }

    @Override
    public Boolean verifyJwtToken(String token) {
         return jwtService.verifyJwtToken(token);
    }
}
