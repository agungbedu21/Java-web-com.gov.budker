package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;

public class RptGrowthDto {
    private String bankName;
    private BigDecimal growthId;
    private float rbbGrowth;
    private float ikuTarget;
    private float growthTarget;
    private String yoyCost;
    private String monthCost;
    private float growthValue;
    private String lastYearValue;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getGrowthId() {
        return growthId;
    }

    public void setGrowthId(BigDecimal growthId) {
        this.growthId = growthId;
    }

    public float getRbbGrowth() {
        return rbbGrowth;
    }

    public void setRbbGrowth(float rbbGrowth) {
        this.rbbGrowth = rbbGrowth;
    }

    public float getIkuTarget() {
        return ikuTarget;
    }

    public void setIkuTarget(float ikuTarget) {
        this.ikuTarget = ikuTarget;
    }

    public float getGrowthTarget() {
        return growthTarget;
    }

    public void setGrowthTarget(float growthTarget) {
        this.growthTarget = growthTarget;
    }

    public String getYoyCost() {
        return yoyCost;
    }

    public void setYoyCost(String yoyCost) {
        this.yoyCost = yoyCost;
    }

    public String getMonthCost() {
        return monthCost;
    }

    public void setMonthCost(String monthCost) {
        this.monthCost = monthCost;
    }

    public float getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(float growthValue) {
        this.growthValue = growthValue;
    }

    public String getLastYearValue() {
        return lastYearValue;
    }

    public void setLastYearValue(String lastYearValue) {
        this.lastYearValue = lastYearValue;
    }
}
