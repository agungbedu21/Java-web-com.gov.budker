package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cal_trx_finishing_detail", schema = "budproker", catalog = "")
public class CalTrxFinishingDetail {
    private BigDecimal detailId;
    private BigDecimal bankId;
    private String problems;
    private String realization;
    private String explanation;
    private BigDecimal finishingId;

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
    @Column(name = "explanation")
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Basic
    @Column(name = "realization")
    public String getRealization() {
        return realization;
    }

    public void setRealization(String realization) {
        this.realization = realization;
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
    @Column(name = "problems")
    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    @Basic
    @Column(name = "finishing_id")
    public BigDecimal getFinishingId() {
        return finishingId;
    }

    public void setFinishingId(BigDecimal finishingId) {
        this.finishingId = finishingId;
    }
}
