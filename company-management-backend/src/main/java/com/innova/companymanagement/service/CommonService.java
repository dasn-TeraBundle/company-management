package com.innova.companymanagement.service;

import com.innova.companymanagement.beans.UserRole;
import org.springframework.security.core.Authentication;

public interface CommonService {

    /**
     * This method fetches UserRole from the Authentication parameter used
     * to login to the system
     *
     * @param auth Authentication Parameters
     * @return
     */
    default UserRole getRole(Authentication auth) {
        return UserRole.valueOf(auth.getAuthorities().stream().findFirst().get().getAuthority());
    }
}
