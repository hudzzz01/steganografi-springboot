package com.hudzaifah.Rest_steganografi.repository;

import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.StegaImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StegaImageRepository extends JpaRepository<StegaImage,String> {
    Optional<StegaImage> findByPath(String url);

    Optional<StegaImage> findByName(String name);
}
