package com.hudzaifah.Rest_steganografi.service;

import com.hudzaifah.Rest_steganografi.model.entity.ImageNews;
import org.springframework.web.multipart.MultipartFile;

public interface ImageNewsService {

    ImageNews create(MultipartFile multipartFile, String flag);
    void deleteById(String id);
}
