package com.hudzaifah.Rest_steganografi.model.dto.berita.request;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequestPatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewsRequestPatch {

    @NotBlank
    @NotEmpty
    private String id;

    private String title;

    private String content;

    private String userAccountid;
}
