package com.innova.companymanagement.beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "DEPARTMENTS")
public class Department {

    @Id
    private String id;
    @NotNull
    @Size(min = 5, max = 20, message = "Department Name can be max 20 chars")
    private String name;
    @OneToOne
    @JoinColumn(name = "COMP_DEPT_ADMIN")
    private User admin;
    @ManyToOne
    @JoinColumn(name = "COMPANY")
    private Company company;

    @PrePersist
    private void preInsert() {
        id = company.getId() + "_" + name.substring(0, 3);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
