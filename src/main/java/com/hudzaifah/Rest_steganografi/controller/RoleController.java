package com.hudzaifah.Rest_steganografi.controller;

import com.hudzaifah.Rest_steganografi.constant.API_URL;
import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.dto.commond.CommondResponse;
import com.hudzaifah.Rest_steganografi.model.dto.role.request.RoleRequest;
import com.hudzaifah.Rest_steganografi.model.dto.role.response.RoleResponse;
import com.hudzaifah.Rest_steganografi.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = API_URL.ROLE_API)
@RequiredArgsConstructor
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid RoleRequest request) {
        RoleResponse response = service.create(request);

        CommondResponse<RoleResponse> responBody = new CommondResponse<>(HttpStatus.CREATED.value(), "berhasil membuat data", response);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("Content Type", "application/json")
                .body(responBody);
    }


    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        List<RoleResponse> response = service.readAll();
        CommondResponse<List<RoleResponse>> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil seluruh data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneById(@PathVariable String id){
        ByIdRequest request = ByIdRequest.builder().id(id).build();
        RoleResponse response = service.readById(request);
        CommondResponse<RoleResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengambil data  ", response);
        return ResponseEntity.ok(bodyResponse);
    }


    @PatchMapping("/update/patch")
    public ResponseEntity<?> patch(@RequestBody @Valid RoleRequest request){
        RoleResponse response = service.update(request);
        CommondResponse<RoleResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil mengupdate data", response);
        return ResponseEntity.ok(bodyResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody @Valid ByIdRequest request){
        RoleResponse response = service.delete(request);
        CommondResponse<RoleResponse> bodyResponse = new CommondResponse<>(HttpStatus.OK.value(), "berhasil menghapus data", response);
        return ResponseEntity.ok(bodyResponse);
    }
}
