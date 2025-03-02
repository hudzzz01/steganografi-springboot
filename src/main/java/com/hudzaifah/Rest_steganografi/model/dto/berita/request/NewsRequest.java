package com.hudzaifah.Rest_steganografi.model.dto.berita.request;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequest;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewsRequest {

    private String title;

    private String content;
    
    private String userAccountId;
}
