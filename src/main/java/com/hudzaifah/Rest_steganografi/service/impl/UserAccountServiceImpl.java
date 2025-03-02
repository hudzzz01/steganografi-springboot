package com.hudzaifah.Rest_steganografi.service.impl;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.dto.role.request.RoleRequest;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequest;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequestPatch;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.response.UserAccountResponse;
import com.hudzaifah.Rest_steganografi.model.entity.Role;
import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;
import com.hudzaifah.Rest_steganografi.model.error.ResouceNotFound;
import com.hudzaifah.Rest_steganografi.repository.UserAccountRepository;
import com.hudzaifah.Rest_steganografi.service.RoleService;
import com.hudzaifah.Rest_steganografi.service.UserAccountService;
import com.hudzaifah.Rest_steganografi.utils.Mapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    RoleService roleService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserAccountResponse create(UserAccountRequest request) {
        UserAccount userAccount = mapper.toEntity(request);
        List<Role> roles = request.getRoles().stream().map(mapper::toEntity).toList();

        System.out.println(userAccount.getPassword());
        userAccount.setPassword(passwordEncoder.encode(
                userAccount.getPassword()
        ));

        System.out.println(userAccount.getPassword());

        List<Role> validRoles = new ArrayList<>();

        //todo : check is role exist in DB ?
        for(RoleRequest role : request.getRoles()){
            String roleName = role.getStringUserROle();
            Role validRole = roleService.checkIsExiftAndSave(roleName);
            validRoles.add(validRole);

        }

        //todo : save user account
        List<Role> validUserRole = validRoles.stream().toList();
        userAccount.setRoles(validRoles);

        //todo : set user account in role
        for(RoleRequest role : request.getRoles()){
            String roleName = role.getStringUserROle();
            Role validRole = roleService.readByName(roleName);
            validRole.setUserAccounts(List.of(userAccount));
        }

//        userAccount.getRoles().forEach(role -> role.getUserAccounts().add(userAccount));
        userAccountRepository.save(userAccount);



        System.out.println(userAccount.getId());
        System.out.println(userAccount.getRoles().size());
        return mapper.toResponse(userAccount);
    }

    @Override
    public UserAccountResponse readById(ByIdRequest request) {
        UserAccount userAccount = userAccountRepository.findById(request.getId()).orElseThrow(()-> new ResouceNotFound("UserAccount not found"));
        System.out.println(userAccount);
        return mapper.toResponse(userAccount);
    }

    @Override
    public UserAccountResponse updatePatch(UserAccountRequestPatch request) {
        UserAccount userAccount = userAccountRepository.findById(request.getId()).orElseThrow(()-> new ResouceNotFound("UserAccount not found"));

        if(request.getName() != null) userAccount.setName(request.getName());
        if(request.getPassword() != null) userAccount.setPassword(request.getPassword());
        if(request.getRoles() != null) {
            List<Role> roles = request.getRoles().stream().map(mapper::toEntity).toList();
            List<Role> validRoles = new ArrayList<>();

            //todo : check is role exist in DB ?
            for(RoleRequest role : request.getRoles()){
                String roleName = role.getStringUserROle();
                Role validRole = roleService.checkIsExiftAndSave(roleName);
                validRoles.add(validRole);

            }

            List<Role> validUserRole = validRoles.stream().toList();
            userAccount.setRoles(validRoles);
        }
        if(request.getUsername() != null) userAccount.setUsername(request.getUsername());

        userAccountRepository.save(userAccount);

        return mapper.toResponse(userAccount);
    }

    @Override
    public UserAccountResponse delete(ByIdRequest request) {
        UserAccount userAccount = userAccountRepository.findById(request.getId()).orElseThrow(()-> new ResouceNotFound("UserAccount not found"));
        userAccountRepository.delete(userAccount);
        return mapper.toResponse(userAccount);
    }

    @Override
    public List<UserAccountResponse> readAll() {
        return userAccountRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public UserAccount readById(String id) {
        return userAccountRepository.findById(id).orElseThrow(()-> new ResouceNotFound("UserAccount not found"));
    }

    @Override
    public String checkUsernameAndPassword(String username, String password) {
        UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow(()-> new ResouceNotFound("UserAccount not found"));

        if(!passwordEncoder.matches(password,userAccount.getPassword())) throw new ResouceNotFound("wrong password");
        return userAccount.getId();
    }
}
