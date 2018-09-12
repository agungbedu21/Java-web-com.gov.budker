package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cal_trx_bprs_detail", schema = "budproker", catalog = "")
public class CalTrxBprsDetail {
    private BigDecimal detailId;
    private BigDecimal bankId;
    private BigDecimal supervisionStatus;
    private BigDecimal carValue;
    private BigDecimal carBprsId;
    private BigDecimal isBalance;

    @Id
    @GeneratedValue
    @Column(name = "detail_id")
    public BigDecimal getDetailId() {
        return detailId;
    }

    public void setDetailId(BigDecimal detailId) {
        this.detailId = detailId;
    }

    @Basic
    @Column(name = "car_value")
    public BigDecimal getCarValue() {
        return carValue;
    }

    public void setCarValue(BigDecimal carValue) {
        this.carValue = carValue;
    }

    @Basic
    @Column(name = "bank_id")
    public BigDecimal getBankId() {
        return bankId;
    }

    public void setBankId(BigDecimal bankId) {
        this.bankId = bankId;
    }

    @Basic
    @Column(name = "supervision_status")
    public BigDecimal getSupervisionStatus() {
        return supervisionStatus;
    }

    public void setSupervisionStatus(BigDecimal supervisionStatus) {
        this.supervisionStatus = supervisionStatus;
    }

    @Basic
    @Column(name = "car_bprs_id")
    public BigDecimal getCarBprsId() {
        return carBprsId;
    }

    public void setCarBprsId(BigDecimal carBprsId) {
        this.carBprsId = carBprsId;
    }

    @Basic
    @Column(name = "is_balance")
    public BigDecimal getIsBalance() {
        return isBalance;
    }

    public void setIsBalance(BigDecimal isBalance) {
        this.isBalance = isBalance;
    }
}
