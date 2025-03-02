package com.hudzaifah.Rest_steganografi.service.impl;

import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;
import com.hudzaifah.Rest_steganografi.model.error.ResouceNotFound;
import com.hudzaifah.Rest_steganografi.repository.UserAccountRepository;
import com.hudzaifah.Rest_steganografi.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserServiceImpl implements AuthUserService {


    @Autowired
    private UserAccountRepository userAccountRepository;


    @Override
    public UserAccount loadUserById(String id) {
        return userAccountRepository.findById(id).orElseThrow(() -> new ResouceNotFound("UserAccount not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccount = userAccountRepository.findByUsername(username);
        return userAccount.orElseThrow(() -> new UsernameNotFoundException("UserAccount not found"));
    }
}
