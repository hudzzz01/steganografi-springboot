package com.hudzaifah.Rest_steganografi.service.cloudinary.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hudzaifah.Rest_steganografi.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;


    @Override
    public String uploadImage(File file) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);

        Map uploadResult = cloudinary.uploader().upload(fileInputStream, ObjectUtils.emptyMap());

        fileInputStream.close();


//        System.out.println(uploadResult);



        return uploadResult.get("secure_url").toString();
    }

    @Override
    public String uploadImage(MultipartFile multipartFile, String folder, String fileName) throws IOException {
        Map<String, Object> params = new HashMap<>();
        // Directory/folder structure
        params.put("folder", folder);

        // Custom public ID (filename without extension)
        params.put("public_id", fileName);

        Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), params);

        System.out.println(uploadResult);
        return uploadResult.get("secure_url").toString();
    }
}
