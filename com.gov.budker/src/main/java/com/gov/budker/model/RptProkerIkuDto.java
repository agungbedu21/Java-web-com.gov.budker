package com.gov.budker.model;

import java.math.BigDecimal;

public class RptProkerIkuDto {
    private String ikuCode;
    private String prokerName;
    private String picName;
    private String target;
    private String periode;
    private String realization;
    private String achievement;
    private String explanation;

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

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getRealization() {
        return realization;
    }

    public void setRealization(String realization) {
        this.realization = realization;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
