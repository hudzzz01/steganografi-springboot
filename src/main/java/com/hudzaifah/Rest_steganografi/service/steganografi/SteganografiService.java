package com.hudzaifah.Rest_steganografi.service.steganografi;

import com.hudzaifah.Rest_steganografi.model.dto.steganografi.response.SecretMessageResponse;
import com.hudzaifah.Rest_steganografi.model.dto.steganografi.steganografiservice.OutputStegaImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface SteganografiService {

    // text, key text, file stega, actual image
   OutputStegaImage insertTextToImage(MultipartFile image, String message);

    SecretMessageResponse extractTextFromImage(MultipartFile image, Integer key);

   //text
//   void extractTextFromImage(File image);


}
