package com.hudzaifah.Rest_steganografi.model.dto.steganografi.steganografiservice;

import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.ActualImageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.StegaImageResponse;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.ActualImage;
import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.StegaImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputStegaImage {
    // text, key text, file stega, actual image
    private String hiddenMessage;
    private String key;
    private StegaImageResponse stegaImageResponse;
    private ActualImageResponse actualImageResponse;

}

