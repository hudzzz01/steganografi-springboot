package com.hudzaifah.Rest_steganografi.controller.cloudinary;

import com.hudzaifah.Rest_steganografi.model.dto.commond.CommondResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.ActualImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.ActualImageResponse;
import com.hudzaifah.Rest_steganografi.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/cloudinary")
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(
            @RequestPart(name = "image") MultipartFile image
    ) {

        String response = "";
        try {
           response = cloudinaryService.uploadImage(image, "output", image.getOriginalFilename()+"hudzaifah");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }


        CommondResponse<String> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }
}
