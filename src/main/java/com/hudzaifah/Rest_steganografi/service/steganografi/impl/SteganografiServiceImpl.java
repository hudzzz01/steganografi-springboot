package com.hudzaifah.Rest_steganografi.service.steganografi.impl;

import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.ActualImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.StegaImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.ActualImageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.SecretMessageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.StegaImageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.steganografiservice.OutputStegaImage;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.ActualImage;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.StegaImage;
import com.hudzaifah.Rest_steganografi.service.steganografi.ActualImageService;
import com.hudzaifah.Rest_steganografi.service.steganografi.StegaImageService;
import com.hudzaifah.Rest_steganografi.service.steganografi.SteganografiService;
import com.hudzaifah.Rest_steganografi.utils.FileToMultipartfile;
import com.kentung.service.SteganoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class SteganografiServiceImpl implements SteganografiService {

    @Autowired
    SteganoService serviceSteganografi;

    @Autowired
    StegaImageService serviceStegaImage;

    @Autowired
    ActualImageService serviceActualImage;


    @Override
    public OutputStegaImage insertTextToImage(MultipartFile image, String message) {
        ActualImageRequest request = ActualImageRequest.builder().actualImage(image).build();
        ActualImageResponse actualImageResponse = serviceActualImage.saveImage(request);

        //todo : create stegafile
        File fileActualImageInput = new File(actualImageResponse.getPath());
        //        System.out.println(message);
        String[] result = serviceSteganografi.stegano(message, fileActualImageInput);

        String key = result[1];
        String fileName = result[2];

        if (result[0] == "0") throw new RuntimeException("Kesalahan membuat image steganografi");

        //todo : create stegaimage
        File outputDir = new File("OutputImage");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File stegaImage = new File(Path.of("OutputImage/" + result[2]).toUri());

//        System.out.println(stegaImage.exists());
//        System.out.println(stegaImage);

        //todo : save stegaimage to db
        MultipartFile stegaImageMultipart;
        try {
            stegaImageMultipart = FileToMultipartfile.convertFileToMultipartFile(stegaImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StegaImageRequest stegaImageRequest = StegaImageRequest.builder()
                .stegaImage(stegaImageMultipart)
                .actualImageId(actualImageResponse.getId())
                .build();

        //todo : delete in output folder
        System.out.println(stegaImage);
        if (!stegaImage.delete()) throw new RuntimeException("Gagal menghapus outputfile");


        StegaImageResponse stegaImageResponse = serviceStegaImage.saveImage(stegaImageRequest, key, message);

        //todo : linking the stegano image with actual image

        StegaImage savedStegaImage = serviceStegaImage.getByIdObj(stegaImageResponse.getId());
        ActualImage savedActualImage = serviceActualImage.getByIdObj(actualImageResponse.getId());

        savedActualImage.setStegaImage(savedStegaImage);
        ActualImageResponse actualImageResponseSaved = serviceActualImage.save(savedActualImage);

        savedStegaImage.setActualImage(savedActualImage);
        StegaImageResponse stegaImageResponseSaved = serviceStegaImage.save(savedStegaImage);

        //todo : return

        OutputStegaImage outputStegaImage = OutputStegaImage.builder()
                .key(stegaImageResponseSaved.getKey())
                .hiddenMessage(stegaImageResponseSaved.getSecretMessage())
                .stegaImageResponse(stegaImageResponseSaved)
                .actualImageResponse(actualImageResponseSaved)
                .build();

        return outputStegaImage;
    }

    @Override
    public SecretMessageResponse extractTextFromImage(MultipartFile image, Integer key) {

        File stegaImage;
        try {
            stegaImage = FileToMultipartfile.convertMultipartFileToFile(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] result = serviceSteganografi.extrackMessage(stegaImage, key);

        String isSucces = result[0];
        String secretMessage = result[1];

        if (!isSucces.equals("1")) throw new RuntimeException("gagal mengekrak pesan");
        return SecretMessageResponse.builder().secretMessage(secretMessage).build();
    }
}