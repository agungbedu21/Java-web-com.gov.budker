package com.gov.budker.model;


import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "cal_trx_car_bprs", schema = "budproker", catalog = "")
public class CalTrxCarBprs {

    private BigDecimal calCarBprsId;
    private BigDecimal divisionId;
    private String divName;
    private String year;
    private BigDecimal triwulan;
    private BigDecimal ikuTarget;
    private BigDecimal achAvg;
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
    @Column(name = "cal_car_bprs_id")
    public BigDecimal getCalCarBprsId() {
        return calCarBprsId;
    }

    public void setCalCarBprsId(BigDecimal calCarBprsId) {
        this.calCarBprsId = calCarBprsId;
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
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    @Basic
    @Column(name = "triwulan")
    public BigDecimal getTriwulan() {
        return triwulan;
    }

    public void setTriwulan(BigDecimal triwulan) {
        this.triwulan = triwulan;
    }


    @Basic
    @Column(name = "iku_target")
    public BigDecimal getIkuTarget() {
        return ikuTarget;
    }

    public void setIkuTarget(BigDecimal ikuTarget) {
        this.ikuTarget = ikuTarget;
    }


    @Formula("(((select count(a.detail_id) from cal_trx_bprs_detail a where a.is_balance=1 and a.car_bprs_id = cal_car_bprs_id)/" +
            "(select count(b.detail_id) from cal_trx_bprs_detail b where b.car_bprs_id = cal_car_bprs_id))/iku_target)")
    public BigDecimal getAchAvg() {
        return achAvg;
    }

    public void setAchAvg(BigDecimal achAvg) {
        this.achAvg = achAvg;
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
