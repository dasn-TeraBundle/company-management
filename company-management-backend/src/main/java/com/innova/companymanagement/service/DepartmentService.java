package com.innova.companymanagement.service;

import com.innova.companymanagement.beans.Company;
import com.innova.companymanagement.beans.Department;
import com.innova.companymanagement.beans.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface DepartmentService extends CommonService {

    Department create(Authentication auth, Department department);

    Department findById(String id);

    Department findByAdmin(User user);
    Department findByAdmin(String email);
    List<Department> findAllByCompany(Company company);
    List<Department> findAllByLoggedInUser(Authentication auth);
    Department update(Authentication auth, Department department);

    void delete(Authentication auth, String id);
    void delete(Authentication auth, Department department);

    default Department update(Department oldDept, Department newDept) {
        oldDept.setName(newDept.getName());

        return oldDept;
    }
}
