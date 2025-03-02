package com.hudzaifah.Rest_steganografi.controller.steganografi_feature;


import com.hudzaifah.Rest_steganografi.constant.API_URL;

import com.hudzaifah.Rest_steganografi.model.dto.commond.CommondResponse;

import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.ActualImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.StegaImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.ActualImageResponse;

import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.SecretMessageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.StegaImageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.steganografiservice.OutputStegaImage;
import com.hudzaifah.Rest_steganografi.service.steganografi.ActualImageService;
import com.hudzaifah.Rest_steganografi.service.steganografi.StegaImageService;
import com.hudzaifah.Rest_steganografi.service.steganografi.SteganografiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Content;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = API_URL.STEGANOGRAFI_API)
@RequiredArgsConstructor
public class SteganoGrafiController {


    @Autowired
    private ActualImageService service;

    @Autowired
    private StegaImageService service2;

    @Autowired
    private SteganografiService service3;


    @PostMapping(value = "/save/actual-image", consumes = "multipart/form-data")
    public ResponseEntity<?> saveActualImage(
            @RequestPart(name = "actual-image") MultipartFile actualImage

    ) {
        ActualImageRequest request = ActualImageRequest.builder().actualImage(actualImage).build();
        System.out.println(actualImage);
        ActualImageResponse response = service.saveImage(request);
//        ActualImageResponse response = null;

        CommondResponse<ActualImageResponse> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }


    @GetMapping("/getAll/actual-image")
    public ResponseEntity<?> getAllActualImage() {
        List<ActualImageResponse> response = service.getAllImage();
        CommondResponse<List<ActualImageResponse>> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil seluruh data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @GetMapping("/get/actual-image/{name}")
    public ResponseEntity<?> getActualImageByName(@PathVariable String name) {
        ActualImageResponse response = service.getByName(name);
        CommondResponse<ActualImageResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil data  ", response);
        return ResponseEntity.ok(bodyResponse);
    }


    @DeleteMapping("/delete/actual-image/{name}")
    public ResponseEntity<?> deleteActualImageByName(@PathVariable String name) {
        String response = service.deleteImageByName(name);
        CommondResponse<String> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil menghapus data", response);
        return ResponseEntity.ok(bodyResponse);
    }


    @GetMapping("/download/actual-image/{name}")
    public ResponseEntity<?> downloadActualImageByName(@PathVariable String name) {
        ActualImageResponse response = service.getByName(name);

        Path imagePath = Paths.get(response.getPath()).normalize();
        Resource resource = null;
        try {
            resource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }


    @GetMapping("/read/actual-image/{name}")
    public ResponseEntity<?> readActualImageByName(@PathVariable String name) {
        ActualImageResponse response = service.getByName(name);

        Path imagePath = Paths.get(response.getPath()).normalize();
        Resource resource = null;
        try {
            resource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, response.getContentType())
                .body(resource);

    }

    //SteganoGrafi section

    @PostMapping(value = "/save/stegano-image", consumes = "multipart/form-data")
    public ResponseEntity<?> saveSteganoImage(
            @RequestPart(name = "stegano-image") MultipartFile stegaImage
    ) {
        StegaImageRequest request = StegaImageRequest.builder().stegaImage(stegaImage).build();
        System.out.println(stegaImage);
        StegaImageResponse response = service2.saveImage(request);
//        ActualImageResponse response = null;

        CommondResponse<StegaImageResponse> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }


    @GetMapping("/getAll/stegano-image")
    public ResponseEntity<?> getSteganoImage() {
        List<StegaImageResponse> response = service2.getAllImage();
        CommondResponse<List<StegaImageResponse>> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil seluruh data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @GetMapping("/get/stegano-image/{name}")
    public ResponseEntity<?> getSteganoImageByName(@PathVariable String name) {
        StegaImageResponse response = service2.getByName(name);
        CommondResponse<StegaImageResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil data  ", response);
        return ResponseEntity.ok(bodyResponse);
    }


    @DeleteMapping("/delete/stegano-image/{name}")
    public ResponseEntity<?> deleteSteganoImageByName(@PathVariable String name) {
        String response = service2.deleteImageByName(name);
        CommondResponse<String> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil menghapus data", response);
        return ResponseEntity.ok(bodyResponse);
    }


    @GetMapping("/download/stegano-image/{name}")
    public ResponseEntity<?> downloadSteganoImageByName(@PathVariable String name) {
        StegaImageResponse response = service2.getByName(name);

        Path imagePath = Paths.get(response.getPath()).normalize();
        Resource resource = null;
        try {
            resource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }




    @GetMapping("/read/stegano-image/{name}")
    public ResponseEntity<?> readSteganoImageByName(@PathVariable String name) {
        StegaImageResponse response = service2.getByName(name);

        Path imagePath = Paths.get(response.getPath()).normalize();
        Resource resource = null;
        try {
            resource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, response.getContentType())
                .body(resource);

    }

    //steganografi service


    @PostMapping(value = "/steganografi/insertTextToImage", consumes = "multipart/form-data")
    public ResponseEntity<?> insertTextToImage(
            @RequestPart(name = "actual-image") MultipartFile actualImage,
            @RequestPart(name = "message") String message
    ) {
        System.out.println(actualImage);
        System.out.println(message);

        OutputStegaImage response = service3.insertTextToImage(actualImage,message);




        CommondResponse<OutputStegaImage> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil menyisipkan pesan", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }

    @PostMapping(value = "/steganografi/extractSecretTextFromImage", consumes = "multipart/form-data")
    public ResponseEntity<?> extractSecretTextFromoImage(
            @RequestPart(name = "stega-image") MultipartFile stegaImage,
            @RequestPart(name = "integer-key") String key
    ) {
        System.out.println(stegaImage);
        System.out.println(key);

        Integer keyInt = 0;

        try{
            keyInt = Integer.valueOf(key);
        }catch (Exception e){
            throw new RuntimeException("Masukan key dengan angka : " + e);
        }

        SecretMessageResponse response = service3.extractTextFromImage(stegaImage,keyInt);




        CommondResponse<SecretMessageResponse> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil mengektraks pesan", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }


}
