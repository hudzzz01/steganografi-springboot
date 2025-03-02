package com.hudzaifah.Rest_steganografi.controller;

import com.hudzaifah.Rest_steganografi.constant.API_URL;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.dto.commond.CommondResponse;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequest;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequestPatch;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.response.UserAccountResponse;
import com.hudzaifah.Rest_steganografi.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = API_URL.USER_ACCOUNT_API)
@RequiredArgsConstructor
public class UserAccountController {

    @Autowired
    private UserAccountService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserAccountRequest request) {
        UserAccountResponse response = service.create(request);

        CommondResponse<UserAccountResponse> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }
    




    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(){
        List<UserAccountResponse> response = service.readAll();
        CommondResponse<List<UserAccountResponse>> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil seluruh data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneById(@PathVariable String id){
        ByIdRequest request = ByIdRequest.builder().id(id).build();
        UserAccountResponse response = service.readById(request);
        CommondResponse<UserAccountResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil data  ", response);
        return ResponseEntity.ok(bodyResponse);
    }


    @PatchMapping("/update/patch")
    public ResponseEntity<?> patch(@RequestBody @Valid UserAccountRequestPatch request){
        UserAccountResponse response = service.updatePatch(request);
        CommondResponse<UserAccountResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengupdate data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody @Valid ByIdRequest request){
        UserAccountResponse response = service.delete(request);
        CommondResponse<UserAccountResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil menghapus data", response);
        return ResponseEntity.ok(bodyResponse);
    }


}
