package com.hudzaifah.Rest_steganografi.model.dto.berita.response;

import com.hudzaifah.Rest_steganografi.model.dto.userAccount.response.UserAccountResponse;
import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsResponse {
    private String id;

    private String title;

    private String content;

    private UserAccountResponse userAccountResponse;
}
