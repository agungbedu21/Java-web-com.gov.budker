package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cal_trx_sectoral_detail", schema = "budproker", catalog = "")
public class CalTrxSectoralDetail {
    private BigDecimal detailId;
    private BigDecimal bankId;
    private String target;
    private String realization;
    private BigDecimal sectoralId;

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
    @Column(name = "target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Basic
    @Column(name = "sectoral_id")
    public BigDecimal getSectoralId() {
        return sectoralId;
    }

    public void setSectoralId(BigDecimal sectoralId) {
        this.sectoralId = sectoralId;
    }
}
