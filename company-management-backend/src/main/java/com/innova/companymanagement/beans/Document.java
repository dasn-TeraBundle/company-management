package com.innova.companymanagement.beans;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "DOCUMENTS")
public class Document {

    @Id
    private String id;
    @Column(name = "FILE_NAME")
    private String filename;
    @Column(name = "MIME_TYPE")
    private String mimeType;
    @ManyToOne
    @JoinColumn(name = "COMPANY", nullable = true)
    private Company company;
    @OneToOne
    @JoinColumn(name = "DEPARTMENT", nullable = true)
    private Department department;
    @OneToOne
    @JoinColumn(name = "EMPLOYEE", nullable = true)
    private User employee;
    @Column(name = "UPLOADED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedDate;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @PrePersist
    private void preInsert() {
        this.id = "" + System.currentTimeMillis();
        this.uploadedDate = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.modifiedDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
