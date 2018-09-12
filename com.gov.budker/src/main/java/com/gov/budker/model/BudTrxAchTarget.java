package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "bud_trx_ach_target", schema = "budproker", catalog = "")
public class BudTrxAchTarget {
    private BigDecimal budTargetId;
    private BigDecimal budgetId;
    private BigDecimal budTargetTw;
    private String achTargetValue;
    private String achTargetPercentage;
    private BigDecimal status;
    private BigDecimal deletedStatus;

    @Id
    @Column(name = "bud_target_id")
    @GeneratedValue
    public BigDecimal getBudTargetId() {
        return budTargetId;
    }

    public void setBudTargetId(BigDecimal budTargetId) {
        this.budTargetId = budTargetId;
    }

    @Basic
    @Column(name = "ach_target_value")
    public String getAchTargetValue() {
        return achTargetValue;
    }

    public void setAchTargetValue(String achTargetValue) {
        this.achTargetValue = achTargetValue;
    }

    @Basic
    @Column(name = "ach_target_percentage")
    public String getAchTargetPercentage() {
        return achTargetPercentage;
    }

    public void setAchTargetPercentage(String achTargetPercentage) {
        this.achTargetPercentage = achTargetPercentage;
    }

    @Basic
    @Column(name = "budget_id")
    public BigDecimal getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(BigDecimal budgetId) {
        this.budgetId = budgetId;
    }

    @Basic
    @Column(name = "bud_target_tw")
    public BigDecimal getBudTargetTw() {
        return budTargetTw;
    }

    public void setBudTargetTw(BigDecimal budTargetTw) {
        this.budTargetTw = budTargetTw;
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

}
