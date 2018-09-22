package com.innova.companymanagement.service.impl;


import com.innova.companymanagement.beans.Company;
import com.innova.companymanagement.beans.User;
import com.innova.companymanagement.beans.UserRole;
import com.innova.companymanagement.exceptions.UnauthorizedAccessException;
import com.innova.companymanagement.repository.CompanyRepository;
import com.innova.companymanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        super();
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(Authentication auth, Company company) {
        UserRole role = getRole(auth);

        if (role == UserRole.SYS_ADMIN)
            return companyRepository.save(company);
        else
            throw new UnauthorizedAccessException("You don't have right to create company");
    }

    @Override
    public Company findByID(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company update(Authentication auth, Company company) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Authentication auth, Company company) {
        companyRepository.delete(company);
    }

    @Override
    public Company findByAdmin(String email) {
        User user = new User();
        user.setEmail(email);
        return companyRepository.findByAdmin(user);
    }

    @Override
    public Company findByAdmin(Authentication auth) {
        String email = auth.getName();
        return findByAdmin(email);
    }

    @Override
    public List<Company> findByLoggedUser(Authentication auth) {
        UserDetails ud = (UserDetails)auth.getPrincipal();
        UserRole role = getRole(auth);

        if (role == UserRole.SYS_ADMIN)
            return companyRepository.findAll();
        else if (role == UserRole.ORG_ADMIN) {
            List<Company> companies = new ArrayList<>();
            companies.add(findByAdmin(auth));
            return companies;
        }
        return null;
    }
}
