package com.gov.budker.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "prk_main_proker", schema = "budproker", catalog = "")
public class PrkMainProker {
    private BigDecimal mainProkerId;
    private BigDecimal directorateId;
    private BigDecimal divisionId;
    private String divisionName;
    private BigDecimal mainProkerYear;
    private String mainProkerName;
    private BigDecimal isIku;
    private String ikuCode;
    private BigDecimal status;
    private BigDecimal userCreated;
    private Date dateCreated;
    private BigDecimal userUpdated;
    private Date dateUpdated;
    private BigDecimal userDeleted;
    private Date dateDeleted;
    private BigDecimal deletedStatus;

    @Id
    @GeneratedValue
    @Column(name = "main_proker_id")
    public BigDecimal getMainProkerId() {
        return mainProkerId;
    }

    public void setMainProkerId(BigDecimal mainProkerId) {
        this.mainProkerId = mainProkerId;
    }

    @Basic
    @Column(name = "directorate_id")
    public BigDecimal getDirectorateId() {
        return directorateId;
    }

    public void setDirectorateId(BigDecimal directorateId) {
        this.directorateId = directorateId;
    }

    @Basic
    @Column(name = "division_id")
    public BigDecimal getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(BigDecimal divisionId) {
        this.divisionId = divisionId;
    }

    @Formula("(select m.division_name from app_mst_division m where m.division_id = division_id )")
    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Basic
    @Column(name = "main_proker_year")
    public BigDecimal getMainProkerYear() {
        return mainProkerYear;
    }

    public void setMainProkerYear(BigDecimal mainProkerYear) {
        this.mainProkerYear = mainProkerYear;
    }

    @Basic
    @Column(name = "main_proker_name")
    public String getMainProkerName() {
        return mainProkerName;
    }

    public void setMainProkerName(String mainProkerName) {
        this.mainProkerName = mainProkerName;
    }

    @Basic
    @Column(name = "is_iku")
    public BigDecimal getIsIku() {
        return isIku;
    }

    public void setIsIku(BigDecimal isIku) {
        this.isIku = isIku;
    }

    @Basic
    @Column(name = "iku_code")
    public String getIkuCode() {
        return ikuCode;
    }

    public void setIkuCode(String ikuCode) {
        this.ikuCode = ikuCode;
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
    @Column(name = "date_created")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
