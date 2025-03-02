package com.hudzaifah.Rest_steganografi.model.dto.steganografi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ActualImageRequestPut {
    private String id;

    private MultipartFile newActualImage;
}
