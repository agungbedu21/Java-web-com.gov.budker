package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "app_mst_division_bank", schema = "budproker", catalog = "")
public class AppMstDivisionBank {
    private BigDecimal divisionBankId;
    private BigDecimal bankTypeId;
    private BigDecimal divisionId;
    private BigDecimal status;
    private BigDecimal deletedStatus;

    @Id
    @GeneratedValue
    @Column(name = "division_bank_id")
    public BigDecimal getDivisionBankId() {
        return divisionBankId;
    }

    public void setDivisionBankId(BigDecimal divisiondivisionId) {
        this.divisionBankId = divisiondivisionId;
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
    @Column(name = "bank_type_id")
    public BigDecimal getBankTypeId() {
        return bankTypeId;
    }

    public void setBankTypeId(BigDecimal bankTypeId) {
        this.bankTypeId = bankTypeId;
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
