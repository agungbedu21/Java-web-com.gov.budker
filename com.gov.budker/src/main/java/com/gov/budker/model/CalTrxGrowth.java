package com.gov.budker.model;


import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "cal_trx_growth", schema = "budproker", catalog = "")
public class CalTrxGrowth {

    private BigDecimal calGrowthId;
    private BigDecimal divisionId;
    private String divName;
    private String year;
    private BigDecimal triwulan;
    private BigDecimal ikuTarget;
    private BigDecimal totalYoy;
    private BigDecimal totalMonth;
    private BigDecimal growthPercentage;
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
    @Column(name = "cal_growth_id")
    public BigDecimal getCalGrowthId() {
        return calGrowthId;
    }

    public void setCalGrowthId(BigDecimal calGrowthId) {
        this.calGrowthId = calGrowthId;
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


    @Formula("(select sum(a.yoy_cost) from cal_trx_growth_detail a where a.growth_id = cal_growth_id and a.growth_target=1)")
    public BigDecimal getTotalYoy() {
        return totalYoy;
    }

    public void setTotalYoy(BigDecimal totalYoy) {
        this.totalYoy = totalYoy;
    }


    @Formula("(select sum(a.month_cost) from cal_trx_growth_detail a where a.growth_id = cal_growth_id and a.growth_target=1)")
    public BigDecimal getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(BigDecimal totalMonth) {
        this.totalMonth = totalMonth;
    }

    @Basic
    @Column(name = "growth_percentage")
    public BigDecimal getGrowthPercentage() {
        return growthPercentage;
    }

    public void setGrowthPercentage(BigDecimal growthPercentage) {
        this.growthPercentage = growthPercentage;
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
