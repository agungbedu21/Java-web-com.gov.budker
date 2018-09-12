package com.gov.budker.model;


import java.math.BigDecimal;

public class RptReportBprsDto {
    private String divisionName;
    private String bankName;
    private int supStatus;
    private float car;
    private int isBalance;
    private BigDecimal ach;
    private Integer ikuTarget;


    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getSupStatus() {
        return supStatus;
    }

    public void setSupStatus(int supStatus) {
        this.supStatus = supStatus;
    }

    public float getCar() {
        return car;
    }

    public void setCar(float car) {
        this.car = car;
    }

    public int getIsBalance() {
        return isBalance;
    }

    public void setIsBalance(int isBalance) {
        this.isBalance = isBalance;
    }

    public BigDecimal getAch() {
        return ach;
    }

    public void setAch(BigDecimal ach) {
        this.ach = ach;
    }

    public Integer getIkuTarget() {
        return ikuTarget;
    }

    public void setIkuTarget(Integer ikuTarget) {
        this.ikuTarget = ikuTarget;
    }
}
