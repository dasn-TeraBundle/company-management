package com.innova.companymanagement.service.impl;

import com.innova.companymanagement.beans.Company;
import com.innova.companymanagement.beans.Department;
import com.innova.companymanagement.beans.User;
import com.innova.companymanagement.beans.UserRole;
import com.innova.companymanagement.exceptions.UnauthorizedAccessException;
import com.innova.companymanagement.repository.DepartmentRepository;
import com.innova.companymanagement.service.CompanyService;
import com.innova.companymanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private CompanyService companyService;
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(CompanyService companyService, DepartmentRepository departmentRepository) {
        super();
        this.companyService = companyService;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Authentication auth, Department department) {
        UserRole role = getRole(auth);

        if (role == UserRole.SYS_ADMIN)
            return departmentRepository.save(department);
        else if (role == UserRole.ORG_ADMIN) {
            Company company = companyService.findByAdmin(auth);
            if (company != null) {
                department.setCompany(company);
                return departmentRepository.save(department);
            }
            throw new UnauthorizedAccessException("Yo don't have rights to create departments for this company");
        } else
            throw new UnauthorizedAccessException("You don't have right to create department");
    }

    @Override
    public Department findById(String id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department findByAdmin(User user) {
        return departmentRepository.findByAdmin(user);
    }

    @Override
    public Department findByAdmin(String email) {
        User user = new User();
        user.setEmail(email);
        return findByAdmin(user);
    }

    @Override
    public List<Department> findAllByCompany(Company company) {
        return departmentRepository.findAllByCompany(company);
    }

    @Override
    public List<Department> findAllByLoggedInUser(Authentication auth) {
        UserDetails ud = (UserDetails) auth.getPrincipal();
        UserRole role = getRole(auth);

        if (role == UserRole.SYS_ADMIN)
            return departmentRepository.findAll();
        else if (role == UserRole.ORG_ADMIN) {
            Company company = companyService.findByAdmin(auth);
            return departmentRepository.findAllByCompany(company);
        } else if (role == UserRole.ORG_DEPT_ADMIN) {
            List<Department> depts = new ArrayList<>();
            depts.add(findByAdmin(ud.getUsername()));
            return depts;
        }
        return null;
    }

    @Override
    public Department update(Authentication auth, Department department) {
        Department dept = findById(department.getId());
        UserRole role = getRole(auth);

        if (dept != null ) {
        	if (role == UserRole.SYS_ADMIN)
                return departmentRepository.save(update(dept, department));
            else if (role == UserRole.ORG_ADMIN) {
                Company company = companyService.findByAdmin(auth);
                if (company != null) {
                    if (company == department.getCompany())
                        return departmentRepository.save(update(dept, department));
                }
                throw new UnauthorizedAccessException("Yo don't have rights to create departments for this company");
            } else
                throw new UnauthorizedAccessException("You don't have right to create department");
        } else
        	throw new IllegalArgumentException("Department you are trying to update doesn't exist");
        
    }

    @Override
	public void delete(Authentication auth, String id) {
    	Department dept = findById(id);
        UserRole role = getRole(auth);

        if (dept != null ) {
        	if (role == UserRole.SYS_ADMIN)
                departmentRepository.deleteById(id);
            else if (role == UserRole.ORG_ADMIN) {
                Company company = companyService.findByAdmin(auth);
                if (company != null) {
                    if (company == dept.getCompany())
                        departmentRepository.deleteById(id);
                } else
                    throw new UnauthorizedAccessException("Yo don't have rights to delete departments for this company");
            } else
                throw new UnauthorizedAccessException("You don't have right to delete department");
        } else
        	throw new IllegalArgumentException("You are trying to delete a Department that doesn't exist");
	}
    
    @Override
    public void delete(Authentication auth, Department department) {
        Department dept = findById(department.getId());
        UserRole role = getRole(auth);

        if (dept != null ) {
        	if (role == UserRole.SYS_ADMIN)
                departmentRepository.delete(department);
            else if (role == UserRole.ORG_ADMIN) {
                Company company = companyService.findByAdmin(auth);
                if (company != null) {
                    if (company == department.getCompany())
                        departmentRepository.delete(department);
                }
                throw new UnauthorizedAccessException("Yo don't have rights to delete departments for this company");
            } else
                throw new UnauthorizedAccessException("You don't have right to delete department");
        } else
        	throw new IllegalArgumentException("You are trying to delete a Department that doesn't exist");
        
    }

}
