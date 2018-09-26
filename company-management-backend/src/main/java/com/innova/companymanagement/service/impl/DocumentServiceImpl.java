package com.innova.companymanagement.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.innova.companymanagement.beans.Company;
import com.innova.companymanagement.beans.Department;
import com.innova.companymanagement.beans.Document;
import com.innova.companymanagement.beans.User;
import com.innova.companymanagement.beans.UserRole;
import com.innova.companymanagement.exceptions.UnauthorizedAccessException;
import com.innova.companymanagement.repository.DocumentRepository;
import com.innova.companymanagement.service.CompanyService;
import com.innova.companymanagement.service.DepartmentService;
import com.innova.companymanagement.service.DocumentService;
import com.innova.companymanagement.service.UserService;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final String DOC_UPLOAD_PATH = "D:/CM_Docs/";

    private DocumentRepository docRepository;
    private CompanyService companyService;
    private DepartmentService departmentService;
    private UserService userService;

    @Autowired
    public DocumentServiceImpl(DocumentRepository docRepository, CompanyService companyService,
                               DepartmentService departmentService, UserService userService) {
        super();
        this.docRepository = docRepository;
        this.companyService = companyService;
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        File path = new File(DOC_UPLOAD_PATH);
        if (!path.exists())
            path.mkdirs();
    }

    @Override
    @Transactional
    public Document create(Authentication auth, MultipartFile file) throws IOException {
        UserDetails ud = (UserDetails) auth.getPrincipal();
        UserRole role = getRole(auth);

        Document doc = new Document();
        doc.setFilename(file.getOriginalFilename());
        doc.setMimeType(file.getContentType());

        if (role == UserRole.SYS_ADMIN)
            doc = docRepository.save(doc);
        else if (role == UserRole.ORG_ADMIN) {
            Company company = companyService.findByAdmin(auth);

            if (company != null) {
                doc.setCompany(company);
                doc = docRepository.save(doc);
            } else
                throw new UnauthorizedAccessException("You don't have access to upload doc");
        } else if (role == UserRole.ORG_DEPT_ADMIN) {
            Department dept = departmentService.findByAdmin(ud.getUsername());

            if (dept != null) {
                doc.setCompany(dept.getCompany());
                doc.setDepartment(dept);
                doc = docRepository.save(doc);
            } else
                throw new UnauthorizedAccessException("You don't have access to upload doc for this department");
        } else if (role == UserRole.EMPLOYEE) {
            User user = userService.findByEmail(ud.getUsername());

            doc.setCompany(user.getDepartment().getCompany());
            doc.setDepartment(user.getDepartment());
            doc.setEmployee(user);
            doc = docRepository.save(doc);
        }

        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream())) {
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(DOC_UPLOAD_PATH + doc.getId()))) {
                byte buf[] = new byte[4096];
                int read = -1;

                while ((read = bis.read(buf)) != -1)
                    bos.write(buf);
            }
        }

        return doc;
    }

    @Override
    public Document findById(String id) {
        return docRepository.findById(id).orElse(null);
    }

    @Override
    public Map<String, List<Document>> findByLoggedInUser(Authentication auth) {
        UserDetails ud = (UserDetails) auth.getPrincipal();
        UserRole role = getRole(auth);
        Map<String, List<Document>> docs = new HashMap<>();

        if (role == UserRole.SYS_ADMIN)
            docs.put("ALL", docRepository.findAll());
        else if (role == UserRole.ORG_ADMIN) {
            docs.put("ALL_GLOBAL", docRepository.findAllByCompany(null));
            Company company = companyService.findByAdmin(ud.getUsername());

            if (company != null)
                docs.put("ALL_COMPANY", docRepository.findAllByCompany(company));
        } else if (role == UserRole.ORG_DEPT_ADMIN) {
            docs.put("ALL_GLOBAL", docRepository.findAllByCompany(null));
            Department dept = departmentService.findByAdmin(ud.getUsername());

            if (dept != null) {
                Company company = dept.getCompany();
                docs.put("ALL_COMPANY_GLOBAL", docRepository.findAllByCompanyAndDepartment(company, null));
                docs.put("ALL_COMPANY_DEPT", docRepository.findAllByCompanyAndDepartment(company, dept));
            }
        } else if (role == UserRole.EMPLOYEE) {
            docs.put("ALL_GLOBAL", docRepository.findAllByCompany(null));
            User user = userService.findByEmail(ud.getUsername());
            Department dept = user.getDepartment();

            if (dept != null) {
                Company company = dept.getCompany();
                docs.put("ALL_COMPANY_GLOBAL", docRepository.findAllByCompanyAndDepartment(company, null));
                docs.put("ALL_COMPANY_DEPT_GLOBAL", docRepository.findAllByCompanyAndDepartmentAndEmployee(company, dept, null));
            }

            docs.put("MY_DOCS", docRepository.findAllByEmployee(user));
        }

        return docs;
    }

    @Override
    @Transactional
    public void delete(Authentication auth, String id) {
        UserDetails ud = (UserDetails) auth.getPrincipal();
        UserRole role = getRole(auth);

        Document doc = docRepository.findById(id).orElse(null);

        if (doc != null) {
            if (role == UserRole.SYS_ADMIN)
                delete(doc);
            else if (role == UserRole.ORG_ADMIN) {
                Company company = companyService.findByAdmin(auth);

                if (company != null) {
                    if (company == doc.getCompany())
                        delete(doc);
                    else
                        throw new UnauthorizedAccessException("You don't have access to delete this doc");
                } else
                    throw new UnauthorizedAccessException("You don't have access to delete this doc");
            } else if (role == UserRole.ORG_DEPT_ADMIN) {
                Department dept = departmentService.findByAdmin(ud.getUsername());

                if (dept != null) {
                    if (dept == doc.getDepartment())
                        delete(doc);
                    else
                        throw new UnauthorizedAccessException("You don't have access to delete this doc");
                } else
                    throw new UnauthorizedAccessException("You don't have access to delete this doc");
            } else if (role == UserRole.EMPLOYEE) {
                User user = userService.findByEmail(ud.getUsername());

                if (doc.getEmployee() == user)
                    delete(doc);
                else
                    throw new UnauthorizedAccessException("You don't have access to delete this doc");
            }

        } else
            throw new IllegalArgumentException("No document with ID : " + id);

    }


    @Transactional
    private void delete(Document doc) {
        docRepository.delete(doc);
        try {
            Files.deleteIfExists(Paths.get(DOC_UPLOAD_PATH + doc.getId()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
