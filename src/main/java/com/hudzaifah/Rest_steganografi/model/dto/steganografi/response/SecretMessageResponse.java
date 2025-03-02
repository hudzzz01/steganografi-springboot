package com.hudzaifah.Rest_steganografi.model.dto.steganografi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretMessageResponse {
    private String secretMessage;
}
