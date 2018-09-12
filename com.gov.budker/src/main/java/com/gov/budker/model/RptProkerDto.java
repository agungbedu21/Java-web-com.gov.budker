package com.gov.budker.model;

public class RptProkerDto {
    private String ikuCode;
    private String prokerName;
    private String prokerBudget;
    private Double realization;
    private Double subsequence;

    public String getIkuCode() {
        return ikuCode;
    }

    public void setIkuCode(String ikuCode) {
        this.ikuCode = ikuCode;
    }

    public Double getSubsequence() {
        return subsequence;
    }

    public void setSubsequence(Double subsequence) {
        this.subsequence = subsequence;
    }

    public String getProkerName() {
        return prokerName;
    }

    public void setProkerName(String prokerName) {
        this.prokerName = prokerName;
    }

    public String getProkerBudget() {
        return prokerBudget;
    }

    public void setProkerBudget(String prokerBudget) {
        this.prokerBudget = prokerBudget;
    }

    public Double getRealization() {
        return realization;
    }

    public void setRealization(Double realization) {
        this.realization = realization;
    }

}
