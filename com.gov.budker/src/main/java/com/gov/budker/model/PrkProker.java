package com.gov.budker.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "prk_proker", schema = "budproker", catalog = "")
public class PrkProker {
    private BigDecimal prokerId;
    private BigDecimal directorateId;
    private String dirName;
    private BigDecimal divisionId;
    private String divName;
    private BigDecimal prokerYear;
    private BigDecimal isMainProker;
    private BigDecimal mainProkerId;
    private String mainProkerName;
    private String subMainProkerName;
    private BigDecimal isIku;
    private String ikuCode;
    private String picUserName;
    private BigDecimal userDivisionId;
    private String userDivName;
    private Date dateTarget;
    private String prokerBudget;
    private BigDecimal status;
    private BigDecimal userCreated;
    private Date dateCreated;
    private BigDecimal userUpdated;
    private Date dateUpdated;
    private BigDecimal userDeleted;
    private Date dateDeleted;
    private BigDecimal deletedStatus;

    //from table budget
    private BigDecimal totalRealization;
    private BigDecimal achievement;


    @Id
    @GeneratedValue
    @Column(name = "proker_id")
    public BigDecimal getProkerId() {
        return prokerId;
    }

    public void setProkerId(BigDecimal prokerId) {
        this.prokerId = prokerId;
    }

    @Formula("(select m.employee_name from app_mst_employee m where m.employee_id = (select b.user_id from prk_user_pic b " +
            "where b.proker_id=proker_id limit 1) )")
    public String getPicUserName() {
        return picUserName;
    }

    public void setPicUserName(String picUserName) {
        this.picUserName = picUserName;
    }

    @Formula("(select m.directorate_name from app_mst_directorate m where m.directorate_id = directorate_id )")
    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    @Formula("(select m.division_name from app_mst_division m where m.division_id = division_id )")
    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    @Formula("(select sum(m.realization) from rlz_trx_realization_month m where m.proker_id = proker_id )")
    public BigDecimal getTotalRealization() {
        return totalRealization;
    }

    public void setTotalRealization(BigDecimal totalRealization) {
        this.totalRealization = totalRealization;
    }

    @Formula("(select a.realization from rlz_trx_realization_iku a where a.proker_id = proker_id and a.realization!='' order by a.realization_iku_id desc limit 1 )")
    public BigDecimal getAchievement() {
        return achievement;
    }

    public void setAchievement(BigDecimal achievement) {
        this.achievement = achievement;
    }

    @Basic
    @Column(name = "main_proker_name")
    public String getMainProkerName() {
        return mainProkerName;
    }

    public void setMainProkerName(String mainProkerName) {
        this.mainProkerName = mainProkerName;
    }

    @Formula("(select m.division_name from app_mst_division m where m.division_id = user_division_id )")
    public String getUserDivName() {
        return userDivName;
    }

    public void setUserDivName(String userDivName) {
        this.userDivName = userDivName;
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

    @Basic
    @Column(name = "proker_year")
    public BigDecimal getProkerYear() {
        return prokerYear;
    }

    public void setProkerYear(BigDecimal prokerYear) {
        this.prokerYear = prokerYear;
    }

    @Basic
    @Column(name = "is_main_proker")
    public BigDecimal getIsMainProker() {
        return isMainProker;
    }

    public void setIsMainProker(BigDecimal isMainProker) {
        this.isMainProker = isMainProker;
    }

    @Basic
    @Column(name = "sub_main_proker_name")
    public String getSubMainProkerName() {
        return subMainProkerName;
    }

    public void setSubMainProkerName(String subMainProkerName) {
        this.subMainProkerName = subMainProkerName;
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
    @Column(name = "user_division_id")
    public BigDecimal getUserDivisionId() {
        return userDivisionId;
    }

    public void setUserDivisionId(BigDecimal userDivisionId) {
        this.userDivisionId = userDivisionId;
    }

    @Basic
    @Column(name = "date_target")
    public Date getDateTarget() {
        return dateTarget;
    }

    public void setDateTarget(Date dateTarget) {
        this.dateTarget = dateTarget;
    }

    //    @Formula("(select m.budget_value from bud_trx_budget m where m.deleted_status=0 and m.budget_division_id = division_id)")
    @Basic
    @Column(name = "proker_budget")
    public String getProkerBudget() {
        return prokerBudget;
    }

    public void setProkerBudget(String prokerBudget) {
        this.prokerBudget = prokerBudget;
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

//    @Formula("(select m.budget_realization from bud_trx_budget m where m.budget_division_id = division_id )")
//    public String getBudgetRealization() {
//        return budgetRealization;
//    }
//
//    public void setBudgetRealization(String budgetRealization) {
//        this.budgetRealization = budgetRealization;
//    }
//
//    @Formula("(select (m.budget_value-m.budget_realization) from bud_trx_budget m where m.budget_division_id = division_id )")
//    public String getBudgetBalance() {
//        return budgetBalance;
//    }
//
//    public void setBudgetBalance(String budgetBalance) {
//        this.budgetBalance = budgetBalance;
//    }
}
