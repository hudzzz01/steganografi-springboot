package com.hudzaifah.Rest_steganografi.model.dto.commond;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PagingResponse {
    private Integer totalPages;
    private Long totalElement;
    private Integer page;
    private Integer size;
    private Boolean hashNext;
    private Boolean hashPrevious;

}
