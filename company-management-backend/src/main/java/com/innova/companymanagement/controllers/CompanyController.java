package com.innova.companymanagement.controllers;

import com.innova.companymanagement.beans.Company;
import com.innova.companymanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/company")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        super();
        this.companyService = companyService;
    }


    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN')")
    public Company add(Authentication auth,
                       @Valid @RequestBody Company company) {
        return companyService.create(auth, company);
    }

    @GetMapping("/list")
    public List<Company> list(Authentication auth) {
        return companyService.findByLoggedUser(auth);
    }
}

