package com.hudzaifah.Rest_steganografi.repository;

import com.hudzaifah.Rest_steganografi.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News,String> {
    Optional<News> findByTitle(String title);
}
