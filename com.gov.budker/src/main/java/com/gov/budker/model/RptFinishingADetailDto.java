package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;

public class RptFinishingADetailDto {
    private String bankName;
    private String problems;
    private Integer ikuTarget;
    private String realization;
    private String explanation;

    public Integer getIkuTarget() {
        return ikuTarget;
    }

    public void setIkuTarget(Integer ikuTarget) {
        this.ikuTarget = ikuTarget;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getRealization() {
        return realization;
    }

    public void setRealization(String realization) {
        this.realization = realization;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
