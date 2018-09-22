package com.innova.companymanagement.service;

import com.innova.companymanagement.beans.Company;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CompanyService extends CommonService {

    Company create(Authentication auth, Company company);

    Company findByID(String id);

    Company findByAdmin(String email);

    Company findByAdmin(Authentication auth);

    Company update(Authentication auth, Company company);

    List<Company> findByLoggedUser(Authentication auth);

    void delete(Authentication auth, Company company);
}
