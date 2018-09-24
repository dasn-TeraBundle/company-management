package com.innova.companymanagement.beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "COMPANIES")
public class Company {

    @Id
    private String id;
    @NotNull
    @Size(min = 5, max = 30, message = "Name should be minimum 5 and maximum 30 chars")
    private String name;
    @OneToOne
    @JoinColumn(name = "ADMIN_USER")
    private User admin;

    public Company() {
    }

    public Company(String id, @NotNull String name, User admin) {
        super();
        this.id = id;
        this.name = name;
        this.admin = admin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", admin=" + admin.getEmail() +
                '}';
    }
}
