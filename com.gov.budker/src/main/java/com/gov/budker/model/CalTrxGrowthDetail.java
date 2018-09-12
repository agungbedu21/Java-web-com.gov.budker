package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cal_trx_growth_detail", schema = "budproker", catalog = "")
public class CalTrxGrowthDetail {
    private BigDecimal detailId;
    private BigDecimal bankId;
    private BigDecimal growthId;
    private BigDecimal rbbGrowth;
    private BigDecimal growthTarget;
    private String yoyCost;
    private String monthCost;
    private BigDecimal growthValue;
    private String lastYearValue;



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
    @Column(name = "last_year_value")
    public String getLastYearValue() {
        return lastYearValue;
    }

    public void setLastYearValue(String lastYearValue) {
        this.lastYearValue = lastYearValue;
    }

    @Basic
    @Column(name = "growth_target")
    public BigDecimal getGrowthTarget() {
        return growthTarget;
    }

    public void setGrowthTarget(BigDecimal growthTarget) {
        this.growthTarget = growthTarget;
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
    @Column(name = "growth_id")
    public BigDecimal getGrowthId() {
        return growthId;
    }

    public void setGrowthId(BigDecimal growthId) {
        this.growthId = growthId;
    }

    @Basic
    @Column(name = "rbb_growth")
    public BigDecimal getRbbGrowth() {
        return rbbGrowth;
    }

    public void setRbbGrowth(BigDecimal rbbGrowth) {
        this.rbbGrowth = rbbGrowth;
    }

    @Basic
    @Column(name = "yoy_cost")
    public String getYoyCost() {
        return yoyCost;
    }

    public void setYoyCost(String yoyCost) {
        this.yoyCost = yoyCost;
    }

    @Basic
    @Column(name = "month_cost")
    public String getMonthCost() {
        return monthCost;
    }

    public void setMonthCost(String monthCost) {
        this.monthCost = monthCost;
    }

    @Basic
    @Column(name = "growth_value")
    public BigDecimal getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(BigDecimal growthValue) {
        this.growthValue = growthValue;
    }
}
