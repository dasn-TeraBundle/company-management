package com.innova.companymanagement.service;

import com.innova.companymanagement.beans.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends CommonService, UserDetailsService {

    User findByEmail(String email);
    List<User> findByLoggedInUser(Authentication auth);

    User add(Authentication auth, User user);

    User update(Authentication auth, User user);

    void delete(Authentication auth, String id);
    void delete(Authentication auth, User user);
}
