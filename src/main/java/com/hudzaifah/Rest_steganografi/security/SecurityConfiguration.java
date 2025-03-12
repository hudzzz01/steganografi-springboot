package com.hudzaifah.Rest_steganografi.security;


import com.hudzaifah.Rest_steganografi.constant.API_URL;
import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                //matikan basic auth
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(cfg -> {
                    cfg.accessDeniedHandler(accessDeniedHandler);
                    cfg.authenticationEntryPoint(authenticationEntryPoint);
                })
                .sessionManagement(cfg -> cfg.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req ->
                                req.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                                        .requestMatchers("api/v1/auth2/**", "/swagger-ui/**", "/v3/api-docs/**", "/cloudinary/**").permitAll()
                                        .requestMatchers("/api/v1/user-account/create").permitAll()
                                        .requestMatchers("/api/v1/user-account/get/all").hasAnyAuthority("ADMIN")
                                        .requestMatchers("/api/v1/user-account/delete").hasAnyAuthority("ADMIN")
                                        .requestMatchers("/api/v1/role/**").hasAnyAuthority("ADMIN")
                                        .requestMatchers("/api/v1/news/**").hasAnyAuthority("USER", "ADMIN")
//                                .requestMatchers("/api/v1/uploads/resource/product/image/**").permitAll()
                                        .requestMatchers("/api/v1/auth/**").permitAll()
                                        .requestMatchers(API_URL.STEGANOGRAFI_API + "/**").permitAll()

//                                .requestMatchers(  API_URL.HTTP_CLIENT + "/**").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasAnyRole("ADMIN")
                                        .anyRequest().authenticated()
                )
                .logout(cfg -> cfg
                        .logoutUrl("/api/v1/auth/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                .addFilterBefore(corsFilter(), CorsFilter.class)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8085", "http://134.185.83.155:8085", "https://yourdomain.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Apply ke semua endpoint
        return source;
    }


}
