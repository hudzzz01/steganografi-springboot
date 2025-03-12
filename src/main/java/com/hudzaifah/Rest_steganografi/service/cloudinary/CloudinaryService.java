package com.hudzaifah.Rest_steganografi.service.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CloudinaryService {

    public String uploadImage(File file) throws IOException;

    public String uploadImage(MultipartFile multipartFile, String folder, String fileName) throws IOException;

}
