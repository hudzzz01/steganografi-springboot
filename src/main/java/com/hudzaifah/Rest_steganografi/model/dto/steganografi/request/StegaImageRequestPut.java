package com.hudzaifah.Rest_steganografi.model.dto.steganografi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StegaImageRequestPut {

    private String id;

    private MultipartFile newStegaImage;

    private String actualImageId;
}
