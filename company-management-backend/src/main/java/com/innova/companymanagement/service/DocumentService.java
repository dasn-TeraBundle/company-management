package com.innova.companymanagement.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.innova.companymanagement.beans.Document;

public interface DocumentService extends CommonService {

    Document create(Authentication auth, MultipartFile file) throws IOException;

    Document findById(String id);

    Map<String, List<Document>> findByLoggedInUser(Authentication auth);

    void delete(Authentication auth, String id);
}
