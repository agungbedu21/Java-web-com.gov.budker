package com.gov.budker.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cal_mst_delta_car", schema = "budproker", catalog = "")
public class CalMstDeltaCar {
    private BigDecimal deltaCarId;
    private BigDecimal year;
    private String carMinValue;
    private String carMaxValue;
    private String achievement;
    private BigDecimal isBprs;
    private BigDecimal status;
    private BigDecimal deletedStatus;

    @Id
    @GeneratedValue
    @Column(name = "delta_car_id")
    public BigDecimal getDeltaCarId() {
        return deltaCarId;
    }

    public void setDeltaCarId(BigDecimal deltaCarId) {
        this.deltaCarId = deltaCarId;
    }

    @Basic
    @Column(name = "achievement")
    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
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
    @Column(name = "car_max_value")
    public String getCarMaxValue() {
        return carMaxValue;
    }

    public void setCarMaxValue(String carMaxValue) {
        this.carMaxValue = carMaxValue;
    }

    @Basic
    @Column(name = "is_bprs")
    public BigDecimal getIsBprs() {
        return isBprs;
    }

    public void setIsBprs(BigDecimal isBprs) {
        this.isBprs = isBprs;
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
