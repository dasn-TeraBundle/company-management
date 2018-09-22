package com.innova.companymanagement.service.impl;

import com.innova.companymanagement.beans.Company;
import com.innova.companymanagement.beans.Department;
import com.innova.companymanagement.beans.User;
import com.innova.companymanagement.beans.UserRole;
import com.innova.companymanagement.exceptions.UnauthorizedAccessException;
import com.innova.companymanagement.repository.UserRepository;
import com.innova.companymanagement.service.CompanyService;
import com.innova.companymanagement.service.DepartmentService;
import com.innova.companymanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CompanyService companyService;
    private DepartmentService departmentService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CompanyService companyService, DepartmentService departmentService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyService = companyService;
        this.departmentService = departmentService;
    }

    @Autowired


    @PostConstruct
    @Transactional
    private void init() {
        boolean isEmpty = userRepository.findAll().isEmpty();
        if (isEmpty) {
            User user = new User("asd@asd.asd", passwordEncoder.encode("1234"), "SYS", "ADMINJ", UserRole.SYS_ADMIN);
            userRepository.save(user);
            System.out.println("----INSERTED----");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findById(email).orElse(null);
    }

    @Override
    public List<User> findByLoggedInUser(Authentication auth) {
        UserRole role = getRole(auth);
        UserDetails ud = (UserDetails)auth.getPrincipal();

        if (role == UserRole.SYS_ADMIN)
            return userRepository.findAll();
        else if(role == UserRole.ORG_ADMIN) {
            Company company = companyService.findByAdmin(auth);
            List<Department> depts = departmentService.findAllByCompany(company);
            return userRepository.findAllByDepartmentIn(depts);
        } else if (role == UserRole.ORG_DEPT_ADMIN) {
            Department dept = departmentService.findByAdmin(ud.getUsername());
            return userRepository.findAllByDepartment(dept);
        } else
            throw new UnauthorizedAccessException("You don't have rights to list users");
    }

    @Override
    @Transactional
    public User add(Authentication auth, User user) {
        UserDetails ud = (UserDetails)auth.getPrincipal();
        UserRole role = getRole(auth);
        String pwd = passwordEncoder.encode("1234");
        user.setPassword(pwd);

        if (role == UserRole.SYS_ADMIN)
            return userRepository.save(user);
        else if (role == UserRole.ORG_ADMIN) {
            if (user.getDepartment() != null)
                return userRepository.save(user);
            else
                throw new IllegalArgumentException("Company Admin must choose department of a user");
        } else if (role == UserRole.ORG_DEPT_ADMIN) {
            Department dept = departmentService.findByAdmin(ud.getUsername());
            if (dept == null)
                throw new UnauthorizedAccessException("You don't have rights to add users to this department");
            user.setDepartment(dept);
            return userRepository.save(user);
        } else
            throw new UnauthorizedAccessException("Yo cannot create users");
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOGGER.debug("USERNAME USED : " + s);
        User user = findByEmail(s);
        if (user == null) {
            LOGGER.debug("No User Found");
            throw new UsernameNotFoundException("No user with matching username");
        }
        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getGrantedAuthority(user));
        LOGGER.debug(ud.getUsername() + " -- " + ud.getPassword());
        return ud;
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthority(User user) {
        Collection<? extends GrantedAuthority> authorities;
        authorities = Arrays.asList(() -> user.getRole().name());
        return authorities;
    }
}
