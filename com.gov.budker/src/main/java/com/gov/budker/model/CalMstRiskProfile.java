package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cal_mst_risk_profile", schema = "budproker", catalog = "")
public class CalMstRiskProfile {
    private BigDecimal riskProfileId;
    private BigDecimal year;
    private String riskProfileName;
    private String carMinValue;
    private BigDecimal status;
    private BigDecimal deletedStatus;

    @Id
    @GeneratedValue
    @Column(name = "risk_profile_id")
    public BigDecimal getRiskProfileId() {
        return riskProfileId;
    }

    public void setRiskProfileId(BigDecimal riskProfileId) {
        this.riskProfileId = riskProfileId;
    }
    
    @Basic
    @Column(name = "year")
    public BigDecimal getYear() {
        return year;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }

    @Basic
    @Column(name = "car_min_value")
    public String getCarMinValue() {
        return carMinValue;
    }

    public void setCarMinValue(String carMinValue) {
        this.carMinValue = carMinValue;
    }

    @Basic
    @Column(name = "risk_profile_name")
    public String getRiskProfileName() {
        return riskProfileName;
    }

    public void setRiskProfileName(String riskProfileName) {
        this.riskProfileName = riskProfileName;
    }

    @Basic
    @Column(name = "status")
    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    @Basic
    @Column(name = "deleted_status")
    public BigDecimal getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(BigDecimal deletedStatus) {
        this.deletedStatus = deletedStatus;
    }
}
