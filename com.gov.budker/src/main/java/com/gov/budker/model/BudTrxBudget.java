package com.gov.budker.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "bud_trx_budget", schema = "budproker", catalog = "")
public class BudTrxBudget {
    private BigDecimal budgetId;
    private BigDecimal budgetDirectorateId;
    private BigDecimal budgetDivisionId;
    private BigDecimal budgetYear;
    private String budgetValue;
    private String budgetRealization;
    private String budgetBalance;
    private String dirName;
    private String divName;
    private BigDecimal status;
    private BigDecimal deletedStatus;
    private BigDecimal userCreated;
    private Date dateCreated;
    private BigDecimal userUpdated;
    private Date dateUpdated;
    private BigDecimal userDeleted;
    private Date dateDeleted;

    @Id
    @Column(name = "budget_id")
    @GeneratedValue
    public BigDecimal getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(BigDecimal budgetId) {
        this.budgetId = budgetId;
    }

    @Formula("(select m.directorate_name from app_mst_directorate m where m.directorate_id = budget_directorate_id )")
    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    @Formula("(select m.division_name from app_mst_division m where m.division_id = budget_division_id )")
    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    @Basic
    @Column(name = "budget_value")
    public String getBudgetValue() {
        return budgetValue;
    }

    public void setBudgetValue(String budgetValue) {
        this.budgetValue = budgetValue;
    }

    @Formula("(select sum(m.realization) from rlz_trx_realization_month m where m.proker_id in " +
            "(select n.proker_id from prk_proker n where n.division_id = budget_division_id and n.proker_year=budget_year) )")
    public String getBudgetRealization() {
        return budgetRealization;
    }

    public void setBudgetRealization(String budgetRealization) {
        this.budgetRealization = budgetRealization;
    }

    @Basic
    @Column(name = "budget_directorate_id")
    public BigDecimal getBudgetDirectorateId() {
        return budgetDirectorateId;
    }

    public void setBudgetDirectorateId(BigDecimal budgetDirectorateId) {
        this.budgetDirectorateId = budgetDirectorateId;
    }

    @Basic
    @Column(name = "budget_division_id")
    public BigDecimal getBudgetDivisionId() {
        return budgetDivisionId;
    }

    public void setBudgetDivisionId(BigDecimal budgetDivisionId) {
        this.budgetDivisionId = budgetDivisionId;
    }

    @Basic
    @Column(name = "budget_year")
    public BigDecimal getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(BigDecimal budgetYear) {
        this.budgetYear = budgetYear;
    }

    @Formula("(select (a.budget_value-a.budget_realization) from bud_trx_budget a where a.budget_id = budget_id )")
    public String getBudgetBalance() {
        return budgetBalance;
    }

    public void setBudgetBalance(String budgetBalance) {
        this.budgetBalance = budgetBalance;
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
    @Column(name = "deleted_status")
    public BigDecimal getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(BigDecimal deletedStatus) {
        this.deletedStatus = deletedStatus;
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
}
