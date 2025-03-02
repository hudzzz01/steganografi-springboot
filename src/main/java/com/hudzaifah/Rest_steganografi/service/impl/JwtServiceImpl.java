package com.hudzaifah.Rest_steganografi.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.hudzaifah.Rest_steganografi.model.dto.auth.JwtClaims;
import com.hudzaifah.Rest_steganografi.model.dto.auth.RequestJwt;
import com.hudzaifah.Rest_steganografi.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt-secret}")
    String secretKey;

    @Value("${jwt.expired}")
    String expired;


    @Override
    public String generateToken(RequestJwt requestJwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            String token = JWT.create()
                    .withIssuer("hudzaifah")
                    .withClaim("userId",requestJwt.getId())
                    .withClaim("role",requestJwt.getRolesString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + (Integer.parseInt(expired) * 1000L)))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erorr saat membuat JWT bang" + exception.getMessage());
        }

    }


    @Override
    public boolean verifyJwtToken(String token) {
        return getClaimsByToken(token) != null;
    }

    @Override
    public JwtClaims getClaimsByToken(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("hudzaifah")
                    // reusable verifier instance
                    .build();

            decodedJWT = verifier.verify(token);


            JwtClaims jwtClaims = new JwtClaims();
            jwtClaims.setUserAccountId(decodedJWT.getClaim("userId").asString());
//            System.out.println(decodedJWT.getClaim("role").asList(String.class) );
            jwtClaims.setRoles(decodedJWT.getClaim("role").asList(String.class));



            return jwtClaims;
        } catch (JWTCreationException exception){
            // Invalid signature/claims
            throw new RuntimeException("Erorr saat decode JWT bang" + exception.getMessage());

        }
    }
}
