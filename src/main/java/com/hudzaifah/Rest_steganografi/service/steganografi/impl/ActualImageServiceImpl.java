package com.hudzaifah.Rest_steganografi.service.steganografi.impl;

import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.ActualImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.ActualImageResponse;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.ActualImage;
import com.hudzaifah.Rest_steganografi.model.error.ResouceNotFound;
import com.hudzaifah.Rest_steganografi.repository.ActualImageRepository;
import com.hudzaifah.Rest_steganografi.service.steganografi.ActualImageService;
import com.hudzaifah.Rest_steganografi.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ActualImageServiceImpl implements ActualImageService {

    @Autowired
    private ActualImageRepository repository;

    @Autowired
    private Mapper mapper;

    @Value("${actual-image-path}")
    private Path path;


    @Override
    public ActualImageResponse saveImage(ActualImageRequest request) {


        Path directoryPath = Paths.get(this.path.toString());
        File directory = new File(directoryPath.toString());
        if (!directory.exists()) {
            directory.mkdirs();
            log.info("Directory created: {}", directory.getAbsolutePath());
        }




        // TODO: Generate MultipartFile to Image and save to Disk
        String uniqueFileName = System.currentTimeMillis() + "-" + request.getActualImage().getOriginalFilename();
        Path filePath = Paths.get(directoryPath.toString(),uniqueFileName);
        try {
            Files.write(filePath, request.getActualImage().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        

        ActualImage actualImage = ActualImage.builder()
                .name(uniqueFileName)
                .contentType(request.getActualImage().getContentType())
                .path(filePath.toString())
                .size(request.getActualImage().getSize())
                .stegaImage(null)
                .build();
        repository.save(actualImage);
        
        return mapper.toResponse(actualImage);
    }

    @Override
    public ActualImage saveImage(MultipartFile file) {

        Path directoryPath = Paths.get(this.path.toString());
        File directory = new File(directoryPath.toString());
        if (!directory.exists()) {
            directory.mkdirs();
            log.info("Directory created: {}", directory.getAbsolutePath());
        }




        // TODO: Generate MultipartFile to Image and save to Disk
        String uniqueFileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(directoryPath.toString(),uniqueFileName);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ActualImage actualImage = ActualImage.builder()
                .name(uniqueFileName)
                .contentType(file.getContentType())
                .path(filePath.toString())
                .size(file.getSize())
                .stegaImage(null)
                .build();
        repository.save(actualImage);
        return actualImage;
    }

    @Override
    public ActualImageResponse save(ActualImage actualImage) {
        actualImage = repository.saveAndFlush(actualImage);
        return mapper.toResponse(actualImage);
    }

    @Override
    public ActualImage getImageByPathObj(String url) {
        ActualImage actualImage = repository.findByPath(url).orElseThrow(() -> new ResouceNotFound("Image not found"));
        return actualImage;
    }

    @Override
    public ActualImageResponse getImageByPath(String url) {
        ActualImage actualImage = repository.findByPath(url).orElseThrow(() -> new ResouceNotFound("Image not found"));
        return mapper.toResponse(actualImage);
    }

    @Override
    public String deleteImageByPath(String path) {
        ActualImage actualImage = this.getImageByPathObj(path);
        repository.delete(actualImage);
        return actualImage.getPath();
    }

    @Override
    public String deleteImageById(String id) {
        ActualImage actualImage = this.getByIdObj(id);

        //todo : delete image file

        File image = new File(actualImage.getPath());
        System.out.println(image);
        if (image.exists()) {
            System.out.println("image exis");
            if (image.delete()) {
                System.out.println("File berhasil dihapus: " + image.getName());
            } else {
                System.out.println("Gagal menghapus file. Pastikan file ada dan tidak sedang digunakan.");
            }
        }
        System.out.println("setelah di hapus");
        System.out.println(image);


        repository.delete(actualImage);
        return actualImage.getPath();
    }

    @Override
    public String deleteImageByName(String name) {
        ActualImage actualImage = this.getByNameObj(name);


        //todo : delete image file

        File image = new File(actualImage.getPath());
        System.out.println(image);
        if (image.exists()) {
            System.out.println("image exis");
            if (image.delete()) {
                System.out.println("File berhasil dihapus: " + image.getName());
            } else {
                System.out.println("Gagal menghapus file. Pastikan file ada dan tidak sedang digunakan.");
            }
        }
        System.out.println("setelah di hapus");

        repository.delete(actualImage);
        return actualImage.getPath();
    }

    @Override
    public List<ActualImageResponse> getAllImage() {
        List<ActualImage> actualImages = repository.findAll();
        return actualImages.stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<ActualImage> getAllImageObj() {
        return repository.findAll();
    }

    @Override
    public ActualImageResponse getByName(String name) {
        ActualImage actualImage = repository.findByName(name).orElseThrow(() -> new ResouceNotFound("Image not found"));
        return mapper.toResponse(actualImage);
    }

    @Override
    public ActualImage getByNameObj(String name) {
        ActualImage actualImage = repository.findByName(name).orElseThrow(() -> new ResouceNotFound("Image not found"));
        return actualImage;
    }

    @Override
    public ActualImageResponse getById(String id) {
        ActualImage actualImage = repository.findById(id).orElseThrow(() -> new ResouceNotFound("Image not found"));
        return mapper.toResponse(actualImage);
    }

    @Override
    public ActualImage getByIdObj(String id) {
        return repository.findById(id).orElseThrow(() -> new ResouceNotFound("Image not found"));
    }
}
