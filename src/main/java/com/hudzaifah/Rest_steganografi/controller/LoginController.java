package com.hudzaifah.Rest_steganografi.controller;

import com.hudzaifah.Rest_steganografi.constant.API_URL;
import com.hudzaifah.Rest_steganografi.model.dto.auth.JwtClaims;
import com.hudzaifah.Rest_steganografi.model.dto.auth.LoginRequest;
import com.hudzaifah.Rest_steganografi.model.dto.auth.LoginResponse;
import com.hudzaifah.Rest_steganografi.model.dto.berita.request.NewsRequest;
import com.hudzaifah.Rest_steganografi.model.dto.berita.response.NewsResponse;
import com.hudzaifah.Rest_steganografi.model.dto.commond.CommondResponse;
import com.hudzaifah.Rest_steganografi.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = API_URL.AUTH_API)
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = service.login(request);

        CommondResponse<LoginResponse> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        CommondResponse<String> responBody = new CommondResponse<>(HttpStatus.OK.value(), "berhasil membuat data", "loggout success");
        return ResponseEntity.status(HttpStatus.OK.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }

    @PostMapping("/jwt-decode")
    public ResponseEntity<?> decodeJwt(@RequestBody @Valid String token) {
        JwtClaims jwtClaims = service.decodeJWT(token);

        CommondResponse<JwtClaims> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", jwtClaims);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }

    @PostMapping("/jwt-verif")
    public ResponseEntity<?> verifyJwt(@RequestBody @Valid String token) {
        Boolean response = service.verifyJwtToken(token);

        CommondResponse<Boolean> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }
}
