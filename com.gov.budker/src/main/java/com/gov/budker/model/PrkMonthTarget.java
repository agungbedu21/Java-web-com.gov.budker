package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "prk_month_target", schema = "budproker", catalog = "")
public class PrkMonthTarget {
    private BigDecimal monthTargetId;
    private BigDecimal month;
    private BigDecimal prokerId;
    private String target;
    private BigDecimal status;
    private BigDecimal deletedStatus;

    @Id
    @GeneratedValue
    @Column(name = "month_target_id")
    public BigDecimal getMonthTargetId() {
        return monthTargetId;
    }

    public void setMonthTargetId(BigDecimal monthTargetId) {
        this.monthTargetId = monthTargetId;
    }

    @Basic
    @Column(name = "proker_id")
    public BigDecimal getProkerId() {
        return prokerId;
    }

    public void setProkerId(BigDecimal prokerId) {
        this.prokerId = prokerId;
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
    @Column(name = "target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
