package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "app_mst_bank_type", schema = "budproker", catalog = "")
public class AppMstBankType {
    private BigDecimal bankTypeId;
    private String bankTypeName;
    private BigDecimal status;
    private BigDecimal deletedStatus;

    @Id
    @GeneratedValue
    @Column(name = "bank_type_id")
    public BigDecimal getBankTypeId() {
        return bankTypeId;
    }

    public void setBankTypeId(BigDecimal bankTypeId) {
        this.bankTypeId = bankTypeId;
    }


    @Basic
    @Column(name = "bank_type_name")
    public String getBankTypeName() {
        return bankTypeName;
    }

    public void setBankTypeName(String bankTypeName) {
        this.bankTypeName = bankTypeName;
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
