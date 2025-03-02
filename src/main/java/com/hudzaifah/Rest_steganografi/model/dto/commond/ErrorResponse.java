package com.hudzaifah.Rest_steganografi.model.dto.commond;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse<T> {
    private Integer status;
    private String message;
    private String timeStamp;

    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now().toString();
    }
}
