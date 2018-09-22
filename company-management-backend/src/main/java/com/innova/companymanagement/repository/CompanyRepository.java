package com.innova.companymanagement.repository;

import com.innova.companymanagement.beans.Company;
import com.innova.companymanagement.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {

    Company findByAdmin(User user);
}
