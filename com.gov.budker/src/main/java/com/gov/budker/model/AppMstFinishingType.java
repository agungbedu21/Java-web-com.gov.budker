package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "app_mst_finishing_type", schema = "budproker", catalog = "")
public class AppMstFinishingType {
    private BigDecimal finishingTypeId;
    private String finishingTypeName;
    private BigDecimal status;
    private BigDecimal deletedStatus;

    @Id
    @GeneratedValue
    @Column(name = "finishing_type_id")
    public BigDecimal getFinishingTypeId() {
        return finishingTypeId;
    }

    public void setFinishingTypeId(BigDecimal finishingTypeId) {
        this.finishingTypeId = finishingTypeId;
    }


    @Basic
    @Column(name = "finishing_type_name")
    public String getFinishingTypeName() {
        return finishingTypeName;
    }

    public void setFinishingTypeName(String finishingTypeName) {
        this.finishingTypeName = finishingTypeName;
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
