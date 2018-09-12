package com.gov.budker.model;


import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "cal_trx_finishing", schema = "budproker", catalog = "")
public class CalTrxFinishing {

    private BigDecimal finishingId;
    private BigDecimal divisionId;
    private String divName;
    private BigDecimal finishingType;
    private String finishingTypeName;
    private String year;
    private BigDecimal month;
    private BigDecimal ikuTarget;
    private BigDecimal achTotal;
    private BigDecimal achIku;
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
    @Column(name = "finishing_id")
    public BigDecimal getFinishingId() {
        return finishingId;
    }

    public void setFinishingId(BigDecimal finishingId) {
        this.finishingId = finishingId;
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
    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    @Basic
    @Column(name = "finishing_type")
    public BigDecimal getFinishingType() {
        return finishingType;
    }

    public void setFinishingType(BigDecimal finishingType) {
        this.finishingType = finishingType;
    }

    @Formula("(select m.finishing_type_name from app_mst_finishing_type m where m.finishing_type_id = finishing_type )")
    public String getFinishingTypeName() {
        return finishingTypeName;
    }

    public void setFinishingTypeName(String finishingTypeName) {
        this.finishingTypeName = finishingTypeName;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    @Basic
    @Column(name = "month")
    public BigDecimal getMonth() {
        return month;
    }

    public void setMonth(BigDecimal month) {
        this.month = month;
    }


    @Basic
    @Column(name = "iku_target")
    public BigDecimal getIkuTarget() {
        return ikuTarget;
    }

    public void setIkuTarget(BigDecimal ikuTarget) {
        this.ikuTarget = ikuTarget;
    }


    @Formula("(select ((sum(m.realization)/sum(m.problems))*100) from cal_trx_finishing_detail m where m.finishing_id = finishing_id )")
    public BigDecimal getAchTotal() {
        return achTotal;
    }

    public void setAchTotal(BigDecimal achTotal) {
        this.achTotal = achTotal;
    }


    @Basic
    @Column(name = "ach_iku")
    public BigDecimal getAchIku() {
        return achIku;
    }

    public void setAchIku(BigDecimal achIku) {
        this.achIku = achIku;
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
