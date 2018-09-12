package com.gov.budker.model;


import java.math.BigDecimal;

public class RptReportBusDto {
    private String divisionName;
    private String bankName;
    private float carBusValue;
    private String riskProfile;
    private String carMin;
    private Double deltaCar;
    private String ach;
    private Integer ikuTarget;

    public Integer getIkuTarget() {
        return ikuTarget;
    }

    public void setIkuTarget(Integer ikuTarget) {
        this.ikuTarget = ikuTarget;
    }

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

    public float getCarBusValue() {
        return carBusValue;
    }

    public void setCarBusValue(float carBusValue) {
        this.carBusValue = carBusValue;
    }

    public String getRiskProfile() {
        return riskProfile;
    }

    public void setRiskProfile(String riskProfile) {
        this.riskProfile = riskProfile;
    }

    public String getCarMin() {
        return carMin;
    }

    public void setCarMin(String carMin) {
        this.carMin = carMin;
    }

    public Double getDeltaCar() {
        return deltaCar;
    }

    public void setDeltaCar(Double deltaCar) {
        this.deltaCar = deltaCar;
    }

    public String getAch() {
        return ach;
    }

    public void setAch(String ach) {
        this.ach = ach;
    }
}
