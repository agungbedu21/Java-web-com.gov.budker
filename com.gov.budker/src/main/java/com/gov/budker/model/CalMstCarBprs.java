package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "cal_mst_car_bprs", schema = "budproker", catalog = "")
public class CalMstCarBprs {

    private BigDecimal bprsId;
    private String year;
    private String carBprsMin;
    private BigDecimal status;
    private BigDecimal deletedStatus;


    @Id
    @GeneratedValue
    @Column(name = "bprs_id")
    public BigDecimal getBprsId() {
        return bprsId;
    }

    public void setBprsId(BigDecimal bprsId) {
        this.bprsId = bprsId;
    }


    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    @Basic
    @Column(name = "car_bprs_min")
    public String getCarBprsMin() {
        return carBprsMin;
    }

    public void setCarBprsMin(String carBprsMin) {
        this.carBprsMin = carBprsMin;
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
