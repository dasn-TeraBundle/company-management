package com.innova.companymanagement.beans;

public enum UserRole {
    SYS_ADMIN("SYS_ADMIN"),
    ORG_ADMIN("ORG_ADMIN"),
    ORG_DEPT_ADMIN("ORG_DEPT_ADMIN"),
    EMPLOYEE("EMPLOYEE");

    private final String value;

    UserRole(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
