package com.hudzaifah.Rest_steganografi.model.dto.steganografi.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StegaImageResponse {

    private String id;

    private String name;

    private String path;

    private Long size;

    private String key;

    private String secretMessage;

    private String contentType;

    private String actualImageId;
}
