package com.hudzaifah.Rest_steganografi.service;

import com.hudzaifah.Rest_steganografi.model.dto.commond.ByIdRequest;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequest;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.request.UserAccountRequestPatch;
import com.hudzaifah.Rest_steganografi.model.dto.userAccount.response.UserAccountResponse;
import com.hudzaifah.Rest_steganografi.model.entity.UserAccount;
import com.hudzaifah.Rest_steganografi.repository.UserAccountRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserAccountService {
   UserAccountResponse create(UserAccountRequest request);
   UserAccountResponse readById(ByIdRequest request);
   UserAccountResponse updatePatch(UserAccountRequestPatch request);
   UserAccountResponse delete(ByIdRequest request);

   List<UserAccountResponse> readAll();

   UserAccount readById(String id);

   String checkUsernameAndPassword(String username, String password);

}
