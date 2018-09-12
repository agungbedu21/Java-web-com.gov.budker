package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "app_mst_employee_role", schema = "budproker", catalog = "")
public class AppMstEmployeeRole {
    private BigDecimal employeeRoleId;
    private BigDecimal employeeId;
    private BigDecimal roleId;
    private BigDecimal status;
    private BigDecimal userCreated;
    private Date dateCretaed;
    private BigDecimal userUpdated;
    private Date dateUpdated;
    private BigDecimal userDeleted;
    private Date dateDeleted;
    private BigDecimal deletedStatus;

    @Id
    @Column(name = "employee_role_id")
    public BigDecimal getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(BigDecimal employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    @Basic
    @Column(name = "employee_id")
    public BigDecimal getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigDecimal employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "role_id")
    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "status")
    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    @Basic
    @Column(name = "user_created")
    public BigDecimal getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(BigDecimal userCreated) {
        this.userCreated = userCreated;
    }

    @Basic
    @Column(name = "date_cretaed")
    public Date getDateCretaed() {
        return dateCretaed;
    }

    public void setDateCretaed(Date dateCretaed) {
        this.dateCretaed = dateCretaed;
    }

    @Basic
    @Column(name = "user_updated")
    public BigDecimal getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(BigDecimal userUpdated) {
        this.userUpdated = userUpdated;
    }

    @Basic
    @Column(name = "date_updated")
    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Basic
    @Column(name = "user_deleted")
    public BigDecimal getUserDeleted() {
        return userDeleted;
    }

    public void setUserDeleted(BigDecimal userDeleted) {
        this.userDeleted = userDeleted;
    }

    @Basic
    @Column(name = "date_deleted")
    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    @Basic
    @Column(name = "deleted_status")
    public BigDecimal getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(BigDecimal deletedStatus) {
        this.deletedStatus = deletedStatus;
    }
}
