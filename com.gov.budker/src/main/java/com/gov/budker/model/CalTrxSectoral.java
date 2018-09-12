package com.gov.budker.model;


import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "cal_trx_sectoral", schema = "budproker", catalog = "")
public class CalTrxSectoral {

    private BigDecimal calSectoralId;
    private BigDecimal divisionId;
    private String divName;
    private BigDecimal sectoralId;
    private String sectoralName;
    private String year;
    private BigDecimal triwulan;
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
    @Column(name = "cal_sectoral_id")
    public BigDecimal getCalSectoralId() {
        return calSectoralId;
    }

    public void setCalSectoralId(BigDecimal calSectoralId) {
        this.calSectoralId = calSectoralId;
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
    @Column(name = "sectoral_id")
    public BigDecimal getSectoralId() {
        return sectoralId;
    }

    public void setSectoralId(BigDecimal sectoralId) {
        this.sectoralId = sectoralId;
    }

    @Formula("(select m.sector_name from app_mst_sector m where m.sector_id = sectoral_id )")
    public String getSectoralName() {
        return sectoralName;
    }

    public void setSectoralName(String sectoralName) {
        this.sectoralName = sectoralName;
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


    @Formula("(select ((sum(m.realization)/sum(m.target))*100) from cal_trx_sectoral_detail m where m.sectoral_id = cal_sectoral_id )")
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
