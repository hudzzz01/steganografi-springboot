package com.hudzaifah.Rest_steganografi.controller;

import com.hudzaifah.Rest_steganografi.constant.API_URL;
import com.hudzaifah.Rest_steganografi.model.dto.berita.request.NewsRequest;
import com.hudzaifah.Rest_steganografi.model.dto.berita.request.NewsRequestPatch;
import com.hudzaifah.Rest_steganografi.model.dto.berita.response.NewsResponse;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.dto.commond.CommondResponse;
import com.hudzaifah.Rest_steganografi.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = API_URL.NEWS_API)
@RequiredArgsConstructor
public class NewsController {

    @Autowired
    NewsService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid NewsRequest request) {
        NewsResponse response = service.create(request);

        CommondResponse<NewsResponse> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }


    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        List<NewsResponse> response = service.readAll();
        CommondResponse<List<NewsResponse>> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil seluruh data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneById(@PathVariable String id){
        ByIdRequest request = ByIdRequest.builder().id(id).build();
        NewsResponse response = service.readById(request);
        CommondResponse<NewsResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @GetMapping("/getTitle/{title}")
    public ResponseEntity<?> getOneByTitle(@PathVariable String title){
        NewsResponse response = service.readByTitle(title);
        CommondResponse<NewsResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil data ", response);
        return ResponseEntity.ok(bodyResponse);
    }


    @PatchMapping("/update/patch")
    public ResponseEntity<?> patch(@RequestBody @Valid NewsRequestPatch request){
        NewsResponse response = service.update(request);
        CommondResponse<NewsResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengupdate data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody @Valid ByIdRequest request){
        NewsResponse response = service.delete(request);
        CommondResponse<NewsResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil menghapus data", response);
        return ResponseEntity.ok(bodyResponse);
    }

}
