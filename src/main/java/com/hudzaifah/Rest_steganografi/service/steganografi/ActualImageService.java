package com.hudzaifah.Rest_steganografi.service.steganografi;

import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.ActualImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.ActualImageResponse;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.ActualImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface ActualImageService {

    ActualImageResponse saveImage(ActualImageRequest request);
    ActualImage saveImage(MultipartFile actualImage);
    ActualImageResponse save(ActualImage actualImage);

    ActualImage getImageByPathObj(String url);
    ActualImageResponse getImageByPath(String url);
    String deleteImageByPath(String path);
    String deleteImageById(String id);
    String deleteImageByName(String name);

    List<ActualImageResponse> getAllImage();
    List<ActualImage> getAllImageObj();
    ActualImageResponse getByName(String name);
    ActualImage getByNameObj(String name);
    ActualImageResponse getById(String id);
    ActualImage getByIdObj(String id);
}
