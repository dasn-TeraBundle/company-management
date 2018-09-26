package com.innova.companymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import com.innova.companymanagement.beans.Company;
import com.innova.companymanagement.beans.Department;
import com.innova.companymanagement.beans.Document;
import com.innova.companymanagement.beans.User;

public interface DocumentRepository extends JpaRepository<Document, String>{

	List<Document> findAllByCompany(Company company);
	List<Document> findAllByCompanyAndDepartment(Company company, Department department);
	List<Document> findAllByCompanyAndDepartmentAndEmployee(Company company, Department department, User user);
	List<Document> findAllByEmployee(User user);
}
