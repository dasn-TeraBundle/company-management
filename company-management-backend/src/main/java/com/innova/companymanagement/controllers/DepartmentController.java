package com.innova.companymanagement.controllers;

import com.innova.companymanagement.beans.Department;
import com.innova.companymanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'ORG_ADMIN')")
    public Department add(Authentication auth,
                          @Valid @RequestBody Department department) {
        return departmentService.create(auth, department);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'ORG_ADMIN', 'ORG_DEPT_ADMIN')")
    public List<Department> list(Authentication auth) {
        return departmentService.findAllByLoggedInUser(auth);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'ORG_ADMIN')")
    public Department update(Authentication auth,
                          @Valid @RequestBody Department department) {
        return departmentService.update(auth, department);
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'ORG_ADMIN')")
    public void delete(Authentication auth,
                          @PathVariable("id") String id) {
        departmentService.delete(auth, id);
    }
}
