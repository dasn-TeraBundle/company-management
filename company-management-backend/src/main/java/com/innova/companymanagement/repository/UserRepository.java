package com.innova.companymanagement.repository;

import com.innova.companymanagement.beans.Department;
import com.innova.companymanagement.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByDepartment(Department department);
    List<User> findAllByDepartmentIn(List<Department> department);
}
