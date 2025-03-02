package com.hudzaifah.Rest_steganografi.service;

import com.hudzaifah.Rest_steganografi.model.entity.ImageUserAccount;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

public interface ImageUserAccountService {
    ImageUserAccount create(MultipartFile multipartFile, String flag);
    void deleteById(String id);

}
