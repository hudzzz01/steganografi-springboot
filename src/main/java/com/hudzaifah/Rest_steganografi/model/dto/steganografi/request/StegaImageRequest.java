package com.hudzaifah.Rest_steganografi.model.dto.steganografi.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StegaImageRequest {

    private MultipartFile stegaImage;

    private String actualImageId;
}
