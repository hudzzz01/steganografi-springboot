package com.hudzaifah.Rest_steganografi.service.impl;

import com.hudzaifah.Rest_steganografi.model.entity.ImageUserAccount;
import com.hudzaifah.Rest_steganografi.model.error.ResouceNotFound;
import com.hudzaifah.Rest_steganografi.repository.ImageUserAccountRepository;
import com.hudzaifah.Rest_steganografi.service.ImageUserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ImageUserAccountServiceImpl implements ImageUserAccountService {

    @Value("${user-image-path}")
    private Path path;

    @Autowired
    private ImageUserAccountRepository imageUserAccountRepository;


    @Override
    public ImageUserAccount create(MultipartFile multipartFile,String flag) {
        try {
            // TODO: Validate MultipartFile
            if (!List.of("image/jpeg", "image/png", "image/gif", "image/jpg").contains(multipartFile.getContentType())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File type not supported");
            }
            Path directoryPath = Paths.get(this.path.toString(), flag);
//                System.out.println(directoryPath);
//                System.out.println(flag);
            File directory = new File(directoryPath.toString());
            if (!directory.exists()) {
                directory.mkdirs();
                log.info("Directory created: {}", directory.getAbsolutePath());
            }




            // TODO: Generate MultipartFile to Image and save to Disk
            String uniqueFileName = System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
            Path filePath = Paths.get(directoryPath.toString(),uniqueFileName);
            Files.write(filePath, multipartFile.getBytes());

            // TODO: Save Image to Database
            ImageUserAccount imageUserAccount = ImageUserAccount.builder()
                    .name(uniqueFileName)
                    .path(filePath.toString())
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType())
                    .build();

            return imageUserAccountRepository.saveAndFlush(imageUserAccount);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteById(String id) {
        ImageUserAccount imageUserAccount = imageUserAccountRepository.findById(id).orElseThrow(()-> new ResouceNotFound("ImageUserAccount not found"));
        imageUserAccountRepository.delete(imageUserAccount);
    }
}
