package com.hudzaifah.Rest_steganografi.service.steganografi;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.StegaImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.StegaImageResponse;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.StegaImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface StegaImageService {
    StegaImageResponse saveImage(StegaImageRequest request);
    StegaImageResponse saveImage(StegaImageRequest request, String key);
    StegaImageResponse save(StegaImage stegaImage);

    StegaImageResponse saveImage(StegaImageRequest request, String key, String secretMessage);
    StegaImage saveImage(MultipartFile stegaImage);
    StegaImage getImageByPathObj(String url);
    StegaImageResponse getImageByPath(String url);
    String deleteImageByPath(String path);
    String deleteImageById(String id);
    String deleteImageByName(String name);

    List<StegaImageResponse> getAllImage();
    List<StegaImage> getAllImageObj();
    StegaImageResponse getByName(String name);
    StegaImage getByNameObj(String name);
    StegaImageResponse getById(String id);
    StegaImage getByIdObj(String id);

}
