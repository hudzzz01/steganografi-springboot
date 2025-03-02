package com.hudzaifah.Rest_steganografi.model.dto.steganografi.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActualImageRequest {

    private MultipartFile actualImage;

}
