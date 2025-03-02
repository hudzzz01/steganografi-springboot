package com.hudzaifah.Rest_steganografi.model.dto.commond;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommondResponse<T> {
    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private String timeStamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PagingResponse paging;

    public CommondResponse(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timeStamp = LocalDateTime.now().toString();
    }

    public CommondResponse(Integer status, String message, T data, PagingResponse paging) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timeStamp = LocalDateTime.now().toString();
        this.paging = paging;
    }
}