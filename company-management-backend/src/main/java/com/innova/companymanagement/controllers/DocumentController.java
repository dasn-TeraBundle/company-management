package com.innova.companymanagement.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.innova.companymanagement.beans.Document;
import com.innova.companymanagement.service.DocumentService;

@RestController
@RequestMapping("/rest/docs")
public class DocumentController {

	private DocumentService documentService;

	@Autowired
	public DocumentController(DocumentService documentService) {
		super();
		this.documentService = documentService;
	}
	
	@PostMapping("/add")
	public Document add(Authentication auth,
			            @RequestParam(value = "file")MultipartFile file) throws IOException {
		return documentService.create(auth, file);
	}
	
	@GetMapping("/list")
	public Map<String, List<Document>> list(Authentication auth) {
		return documentService.findByLoggedInUser(auth);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(Authentication auth,
			           @PathVariable("id") String id) {
		documentService.delete(auth, id);
	}
}
