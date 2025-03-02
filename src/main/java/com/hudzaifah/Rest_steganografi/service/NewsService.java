package com.hudzaifah.Rest_steganografi.service;

import com.hudzaifah.Rest_steganografi.model.dto.berita.request.NewsRequest;
import com.hudzaifah.Rest_steganografi.model.dto.berita.request.NewsRequestPatch;
import com.hudzaifah.Rest_steganografi.model.dto.berita.response.NewsResponse;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.entity.News;


import java.util.List;

public interface NewsService {

    NewsResponse create(NewsRequest request);
    NewsResponse readById(ByIdRequest request);
    NewsResponse update(NewsRequestPatch request);
    NewsResponse delete(ByIdRequest request);

    NewsResponse readByTitle(String name);


    List<NewsResponse> readAll();

}
