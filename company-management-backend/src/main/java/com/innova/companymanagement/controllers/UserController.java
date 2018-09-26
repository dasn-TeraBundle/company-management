package com.innova.companymanagement.controllers;

import com.innova.companymanagement.beans.User;
import com.innova.companymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public User profile(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userService.findByEmail(userDetails.getUsername());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'ORG_ADMIN', 'ORG_DEPT_ADMIN')")
    public User add(Authentication auth,
                    @Valid @RequestBody User user) {
        return userService.add(auth, user);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN', 'ORG_ADMIN', 'ORG_DEPT_ADMIN')")
    public List<User> list(Authentication auth) {
        return userService.findByLoggedInUser(auth);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(Authentication auth,
                       @PathVariable("id") String id) {
        userService.delete(auth, id);
    }
}
