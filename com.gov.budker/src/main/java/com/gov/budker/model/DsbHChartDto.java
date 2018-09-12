package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;


public class DsbHChartDto {
    private String prokerBudget;
    private String divisionName;
    private Double realization;

    public String getProkerBudget() {
        return prokerBudget;
    }

    public void setProkerBudget(String prokerBudget) {
        this.prokerBudget = prokerBudget;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Double getRealization() {
        return realization;
    }

    public void setRealization(Double realization) {
        this.realization = realization;
    }
}
