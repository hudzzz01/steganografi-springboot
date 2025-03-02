package com.hudzaifah.Rest_steganografi.utils;


import com.hudzaifah.Rest_steganografi.constant.UserRole;
import com.hudzaifah.Rest_steganografi.model.dto.berita.request.NewsRequest;
import com.hudzaifah.Rest_steganografi.model.dto.berita.response.NewsResponse;
import com.hudzaifah.Rest_steganografi.model.dto.role.request.RoleRequest;
import com.hudzaifah.Rest_steganografi.model.dto.role.response.RoleResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.ActualImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.StegaImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.ActualImageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.StegaImageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequest;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.response.UserAccountResponse;
import com.hudzaifah.Rest_steganografi.model.entity.News;
import com.hudzaifah.Rest_steganografi.model.entity.Role;
import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.ActualImage;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.StegaImage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {
    public UserAccount toEntity(UserAccountRequest request){

        List<Role> roles = request.getRoles().stream().map(this::toEntity).toList();

        return UserAccount.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .name(request.getName())
                .roles(roles)
                .build();
    }



    public News toEntity(NewsRequest request){

        UserAccount userAccount = UserAccount.builder()
                .id(request.getUserAccountId()).build();

        return News.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userAccount(userAccount)
                .build();
    }

    public Role toEntity(RoleRequest request){
        return Role.builder()
                .userRole(UserRole.valueOf(request.getStringUserROle()))
                .build();
    }

    public UserAccountResponse toResponse(UserAccount userAccount){
        return UserAccountResponse.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .name(userAccount.getName())
                .roles(userAccount.getRoles().stream().map(this::toResponse).toList())
                .build();
    }

    public RoleResponse toResponse(Role role){
        UserRole userRole = role.getUserRole();


        return RoleResponse.builder()
                .id(role.getId())
                .role(userRole)
                .build();
    }

    public NewsResponse toResponse(News news){

        return NewsResponse.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .userAccountResponse(toResponse(news.getUserAccount()))
                .build();
    }

    public ActualImage toEntity(ActualImageRequest actualImageRequest){

        return ActualImage.builder()


                .name(actualImageRequest.getActualImage().getName())
                .path("/actual-images/")
                .contentType(actualImageRequest.getActualImage().getContentType())
                .size(actualImageRequest.getActualImage().getSize())
                .stegaImage(null)
                .build();
    }

    public ActualImageResponse toResponse(ActualImage actualImage){

        String stegaImageId = "";

        if(actualImage.getStegaImage() != null){
            stegaImageId = actualImage.getStegaImage().getId();
        }

        return ActualImageResponse.builder()
                .id(actualImage.getId())
                .name(actualImage.getName())
                .path(actualImage.getPath())
                .contentType(actualImage.getContentType())
                .size(actualImage.getSize())
                .stegaImageId(stegaImageId)
                .build();

    }

    public StegaImage toEntity(StegaImageRequest stegaImageRequest){

        return  null;

//        return StegaImage.builder()
//                .name(stegaImageRequest.getName())
//                .path(stegaImageRequest.getPath())
//                .contentType(stegaImageRequest.getContentType())
//                .size(stegaImageRequest.getSize())
//                .actualImage(null)
//                .build();
    }

    public StegaImageResponse toResponse (StegaImage stegaImage){

        String actualImageId = "";

        if(stegaImage.getActualImage() != null){
            actualImageId = stegaImage.getActualImage().getId();
        }

        return StegaImageResponse.builder()
                .id(stegaImage.getId())
                .name(stegaImage.getName())
                .path(stegaImage.getPath())
                .contentType(stegaImage.getContentType())
                .size(stegaImage.getSize())
                .actualImageId(actualImageId)
                .key(stegaImage.getKey())
                .secretMessage(stegaImage.getSecretMessage())
                .build();
    }


}
