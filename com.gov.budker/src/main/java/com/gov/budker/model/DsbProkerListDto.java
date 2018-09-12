package com.gov.budker.model;


public class DsbProkerListDto {
    private String divisionName;
    private String ikuCode;
    private String prokerName;
    private Double achievement;

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getIkuCode() {
        return ikuCode;
    }

    public void setIkuCode(String ikuCode) {
        this.ikuCode = ikuCode;
    }

    public String getProkerName() {
        return prokerName;
    }

    public void setProkerName(String prokerName) {
        this.prokerName = prokerName;
    }

    public Double getAchievement() {
        return achievement;
    }

    public void setAchievement(Double achievement) {
        this.achievement = achievement;
    }
}
