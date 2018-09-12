package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "app_mst_role_division", schema = "budproker", catalog = "")
public class AppMstRoleDivision {

    private BigDecimal roleDivisionId;
    private BigDecimal divisionId;
    private BigDecimal divisionGet;


    @Id
    @Column(name = "role_division_id")
    @GeneratedValue
    public BigDecimal getRoleDivisionId() {
        return roleDivisionId;
    }

    public void setRoleDivisionId(BigDecimal roleDivisionId) {
        this.roleDivisionId = roleDivisionId;
    }

    @Basic
    @Column(name = "division_id")
    public BigDecimal getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(BigDecimal divisionId) {
        this.divisionId = divisionId;
    }

    @Basic
    @Column(name = "division_get")
    public BigDecimal getDivisionGet() {
        return divisionGet;
    }

    public void setDivisionGet(BigDecimal divisionGet) {
        this.divisionGet = divisionGet;
    }

}
