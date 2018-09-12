package com.gov.budker.model;

import java.util.Date;

public class RptProkerNonIkuDto {
    private String prokerName;
    private String picName;
    private Date dateTarget;
    private String progress;

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

    public Date getDateTarget() {
        return dateTarget;
    }

    public void setDateTarget(Date dateTarget) {
        this.dateTarget = dateTarget;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
