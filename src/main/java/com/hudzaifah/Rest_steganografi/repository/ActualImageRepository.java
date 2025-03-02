package com.hudzaifah.Rest_steganografi.repository;

import com.hudzaifah.Rest_steganografi.model.entity.steganografi_feature.ActualImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActualImageRepository extends JpaRepository<ActualImage,String> {
    Optional<ActualImage> findByPath(String url);

    Optional<ActualImage> findByName(String name);
}
