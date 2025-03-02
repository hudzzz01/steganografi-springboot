package com.hudzaifah.Rest_steganografi.security;

import com.hudzaifah.Rest_steganografi.model.dto.auth.JwtClaims;
import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;
import com.hudzaifah.Rest_steganografi.service.AuthUserService;
import com.hudzaifah.Rest_steganografi.service.JwtService;
import com.hudzaifah.Rest_steganografi.service.UserAccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {


    private String AUTH_HEADER = "Authorization";

    @Autowired
    private  JwtService jwtService;

    @Autowired
    private  AuthUserService authUserService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader(AUTH_HEADER);

        if (bearerToken != null && jwtService.verifyJwtToken(bearerToken.split("Bearer ")[1])) {
            System.out.println("Bearer Token: " + bearerToken);

            String token = bearerToken.split("Bearer ")[1];

            JwtClaims jwtClaims = jwtService.getClaimsByToken(token);

            UsernamePasswordAuthenticationToken authentication;


            UserAccount userAccount = authUserService.loadUserById(jwtClaims.getUserAccountId());

            authentication = new UsernamePasswordAuthenticationToken(
                    userAccount.getUsername(),
                    userAccount.getPassword(),
                    userAccount.getAuthorities()
            );

            System.out.println(List.of(new SimpleGrantedAuthority(userAccount.getRoles().toString())));

            authentication.setDetails(new WebAuthenticationDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);


        }


        System.out.println("Hore Masuk ");
        filterChain.doFilter(request, response);


    }

}

