package com.hudzaifah.Rest_steganografi.service;

import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AuthUserService  extends UserDetailsService {
    UserAccount loadUserById(String id);
}
