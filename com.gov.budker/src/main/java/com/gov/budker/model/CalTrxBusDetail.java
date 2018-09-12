package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cal_trx_bus_detail", schema = "budproker", catalog = "")
public class CalTrxBusDetail {
    private BigDecimal detailId;
    private BigDecimal bankId;
    private BigDecimal riskProfileId;
    private BigDecimal carBusValue;
    private BigDecimal carBusId;
    private String achievement;


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
    @Column(name = "car_bus_value")
    public BigDecimal getCarBusValue() {
        return carBusValue;
    }

    public void setCarBusValue(BigDecimal carBusValue) {
        this.carBusValue = carBusValue;
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
    @Column(name = "risk_profile_id")
    public BigDecimal getRiskProfileId() {
        return riskProfileId;
    }

    public void setRiskProfileId(BigDecimal riskProfileId) {
        this.riskProfileId = riskProfileId;
    }

    @Basic
    @Column(name = "car_bus_id")
    public BigDecimal getCarBusId() {
        return carBusId;
    }

    public void setCarBusId(BigDecimal carBusId) {
        this.carBusId = carBusId;
    }

    @Basic
    @Column(name = "achievement")
    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }
}
