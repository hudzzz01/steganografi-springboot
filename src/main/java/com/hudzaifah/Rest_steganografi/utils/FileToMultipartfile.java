package com.hudzaifah.Rest_steganografi.utils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FileToMultipartfile {
    public static MultipartFile convertFileToMultipartFile(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile(
                "file",                  // Nama field
                file.getName(),          // Nama file
                "image/jpeg",            // Tipe konten (sesuaikan dengan jenis file)
                input                    // FileInputStream sebagai konten
        );
    }

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(convFile);
        return convFile;
    }
}
