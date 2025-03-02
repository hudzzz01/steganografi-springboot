package com.hudzaifah.Rest_steganografi.service.steganografi.impl;

import com.hudzaifah.Rest_steganografi.model.dto.steganografi.request.StegaImageRequest;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.StegaImageResponse;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.StegaImage;
import com.hudzaifah.Rest_steganografi.model.error.ResouceNotFound;
import com.hudzaifah.Rest_steganografi.repository.StegaImageRepository;
import com.hudzaifah.Rest_steganografi.service.steganografi.StegaImageService;
import com.hudzaifah.Rest_steganografi.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class StegaImageServiceImpl implements StegaImageService {

    @Autowired
    private StegaImageRepository repository;

    @Autowired
    Mapper mapper;

    @Value("${stegano-image-path}")
    private Path path;

    @Override
    public StegaImageResponse saveImage(StegaImageRequest request) {
        Path directoryPath = Paths.get(this.path.toString());
        File directory = new File(directoryPath.toString());
        if (!directory.exists()) {
            directory.mkdirs();
            log.info("Directory created: {}", directory.getAbsolutePath());
        }

        // TODO: Generate MultipartFile to Image and save to Disk
        String uniqueFileName = System.currentTimeMillis() + "-" + request.getStegaImage().getOriginalFilename();
        Path filePath = Paths.get(directoryPath.toString(), uniqueFileName);
        try {
            Files.write(filePath, request.getStegaImage().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StegaImage stegaImage = StegaImage.builder()
                .name(uniqueFileName)
                .contentType(request.getStegaImage().getContentType())
                .path(filePath.toString())
                .size(request.getStegaImage().getSize())
                .build();
        repository.save(stegaImage);

        return mapper.toResponse(stegaImage);
    }

    @Override
    public StegaImageResponse saveImage(StegaImageRequest request, String key) {
        Path directoryPath = Paths.get(this.path.toString());
        File directory = new File(directoryPath.toString());
        if (!directory.exists()) {
            directory.mkdirs();
            log.info("Directory created: {}", directory.getAbsolutePath());
        }

        // TODO: Generate MultipartFile to Image and save to Disk
        String uniqueFileName = System.currentTimeMillis() + "-" + request.getStegaImage().getOriginalFilename();
        Path filePath = Paths.get(directoryPath.toString(), uniqueFileName);
        try {
            Files.write(filePath, request.getStegaImage().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StegaImage stegaImage = StegaImage.builder()
                .name(uniqueFileName)
                .contentType(request.getStegaImage().getContentType())
                .path(filePath.toString())
                .key(key)
                .size(request.getStegaImage().getSize())
                .build();
        repository.save(stegaImage);

        return mapper.toResponse(stegaImage);
    }

    @Override
    public StegaImageResponse save(StegaImage stegaImage) {
        stegaImage = repository.saveAndFlush(stegaImage);
        return mapper.toResponse(stegaImage);
    }

    @Override
    public StegaImageResponse saveImage(StegaImageRequest request, String key, String secretMessage) {
        Path directoryPath = Paths.get(this.path.toString());
        File directory = new File(directoryPath.toString());
        if (!directory.exists()) {
            directory.mkdirs();
            log.info("Directory created: {}", directory.getAbsolutePath());
        }

        // TODO: Generate MultipartFile to Image and save to Disk
        String uniqueFileName = System.currentTimeMillis() + "-" + request.getStegaImage().getOriginalFilename();
        Path filePath = Paths.get(directoryPath.toString(), uniqueFileName);
        try {
            Files.write(filePath, request.getStegaImage().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StegaImage stegaImage = StegaImage.builder()
                .name(uniqueFileName)
                .contentType(request.getStegaImage().getContentType())
                .path(filePath.toString())
                .key(key)
                .size(request.getStegaImage().getSize())
                .secretMessage(secretMessage)
                .build();
        repository.save(stegaImage);

        return mapper.toResponse(stegaImage);
    }


    @Override
    public StegaImage saveImage(MultipartFile file) {

        Path directoryPath = Paths.get(this.path.toString());
        File directory = new File(directoryPath.toString());
        if (!directory.exists()) {
            directory.mkdirs();
            log.info("Directory created: {}", directory.getAbsolutePath());
        }

        // TODO: Generate MultipartFile to Image and save to Disk
        String uniqueFileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(directoryPath.toString(), uniqueFileName);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StegaImage stegaImage = StegaImage.builder()
                .name(uniqueFileName)
                .contentType(file.getContentType())
                .path(filePath.toString())
                .size(file.getSize())
                .build();
        repository.save(stegaImage);

        return stegaImage;
    }


    @Override
    public StegaImage getImageByPathObj(String url) {
        StegaImage stegaImage = repository.findByPath(url).orElseThrow(()-> new ResouceNotFound("Image not found"));
        return stegaImage;
    }

    @Override
    public StegaImageResponse getImageByPath(String url) {
        StegaImage stegaImage = repository.findByPath(url).orElseThrow(()-> new ResouceNotFound("Image not found"));
        return mapper.toResponse(stegaImage);
    }

    @Override
    public String deleteImageByPath(String path) {
        StegaImage stegaImage = this.getImageByPathObj(path);
        repository.delete(stegaImage);
        return stegaImage.getPath();
    }

    @Override
    public String deleteImageById(String id) {
        StegaImage stegaImage = this.getByIdObj(id);
        repository.delete(stegaImage);
        return mapper.toResponse(stegaImage).getId();
    }

    @Override
    public String deleteImageByName(String name) {
        StegaImage stegaImage = this.getByNameObj(name);

        // TODO: Delete image file
        File image = new File(stegaImage.getPath());
        if (image.exists()) {
            if (image.delete()) {
                log.info("File berhasil dihapus: {}", image.getName());
            } else {
                log.warn("Gagal menghapus file. Pastikan file ada dan tidak sedang digunakan.");
            }
        }

        repository.delete(stegaImage);
        return stegaImage.getPath();
    }


    @Override
    public List<StegaImageResponse> getAllImage() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<StegaImage> getAllImageObj() {
        return repository.findAll();
    }

    @Override
    public StegaImageResponse getByName(String name) {
        StegaImage stegaImage = repository.findByName(name).orElseThrow(()-> new ResouceNotFound("Image not found"));
        return mapper.toResponse(stegaImage);
    }

    @Override
    public StegaImage getByNameObj(String name) {
        return repository.findByName(name).orElseThrow(()-> new ResouceNotFound("Image not found"));
    }

    @Override
    public StegaImageResponse getById(String id) {
        StegaImage stegaImage = repository.findById(id).orElseThrow(()-> new ResouceNotFound("Image not found"));
        return mapper.toResponse(stegaImage);
    }

    @Override
    public StegaImage getByIdObj(String id) {
        return repository.findById(id).orElseThrow(()-> new ResouceNotFound("Image not found"));
    }
}
