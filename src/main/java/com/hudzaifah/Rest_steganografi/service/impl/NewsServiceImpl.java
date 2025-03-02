package com.hudzaifah.Rest_steganografi.service.impl;

import com.hudzaifah.Rest_steganografi.model.dto.berita.request.NewsRequest;
import com.hudzaifah.Rest_steganografi.model.dto.berita.request.NewsRequestPatch;
import com.hudzaifah.Rest_steganografi.model.dto.berita.response.NewsResponse;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.entity.News;
import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;
import com.hudzaifah.Rest_steganografi.repository.NewsRepository;
import com.hudzaifah.Rest_steganografi.service.NewsService;
import com.hudzaifah.Rest_steganografi.service.UserAccountService;
import com.hudzaifah.Rest_steganografi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    Mapper mapper;

    @Override
    public NewsResponse create(NewsRequest request) {
        UserAccount userAccount = userAccountService.readById(request.getUserAccountId());
        News news = mapper.toEntity(request);

        news.setUserAccount(userAccount);

        newsRepository.save(news);

        return mapper.toResponse(news);
    }

    @Override
    public NewsResponse readById(ByIdRequest request) {
        return mapper.toResponse(newsRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("News not found")));
    }

    @Override
    public NewsResponse update(NewsRequestPatch request) {

        News existingNews = newsRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("News not found"));

        if(request.getTitle() != null) existingNews.setTitle(request.getTitle());
        if(request.getContent() != null) existingNews.setContent(request.getContent());
        if(request.getUserAccountid() != null) existingNews.setUserAccount(userAccountService.readById(request.getUserAccountid()));

        newsRepository.save(existingNews);
        return mapper.toResponse(existingNews);
    }

    @Override
    public NewsResponse delete(ByIdRequest request) {

        News existingNews = newsRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("News not found"));

        newsRepository.delete(existingNews);

        return mapper.toResponse(existingNews);
    }

    @Override
    public NewsResponse readByTitle(String title) {
        return mapper.toResponse(newsRepository.findByTitle(title).orElseThrow(()-> new RuntimeException("News not found")));
    }

    @Override
    public List<NewsResponse> readAll() {
        return newsRepository.findAll().stream().map(mapper::toResponse).toList();
    }
}
