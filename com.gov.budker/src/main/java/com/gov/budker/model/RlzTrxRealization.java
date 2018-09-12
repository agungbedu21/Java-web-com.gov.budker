package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "rlz_trx_realization", schema = "budproker", catalog = "")
public class RlzTrxRealization {

  private BigDecimal realizationId;
  private String prokerId;
  private Date documentDate;
  private BigDecimal documentType;
  private Date approveDate;
  private BigDecimal sumDay;
  private BigDecimal isOntime;
  private String averageValue;


  @Id
  @GeneratedValue
  @Column(name = "realization_id")
  public BigDecimal getRealizationId() {
    return realizationId;
  }

  public void setRealizationId(BigDecimal realizationId) {
    this.realizationId = realizationId;
  }

  @Basic
  @Column(name = "proker_id")
  public String getProkerId() {
    return prokerId;
  }

  public void setProkerId(String prokerId) {
    this.prokerId = prokerId;
  }

  @Basic
  @Column(name = "document_date")
  public Date getDocumentDate() {
    return documentDate;
  }

  public void setDocumentDate(Date documentDate) {
    this.documentDate = documentDate;
  }

  @Basic
  @Column(name = "document_type")
  public BigDecimal getDocumentType() {
    return documentType;
  }

  public void setDocumentType(BigDecimal documentType) {
    this.documentType = documentType;
  }

  @Basic
  @Column(name = "approve_date")
  public Date getApproveDate() {
    return approveDate;
  }

  public void setApproveDate(Date approveDate) {
    this.approveDate = approveDate;
  }

  @Basic
  @Column(name = "sum_day")
  public BigDecimal getSumDay() {
    return sumDay;
  }

  public void setSumDay(BigDecimal sumDay) {
    this.sumDay = sumDay;
  }

  @Basic
  @Column(name = "is_ontime")
  public BigDecimal getIsOntime() {
    return isOntime;
  }

  public void setIsOntime(BigDecimal isOntime) {
    this.isOntime = isOntime;
  }

  @Basic
  @Column(name = "average_value")
  public String getAverageValue() {
    return averageValue;
  }

  public void setAverageValue(String averageValue) {
    this.averageValue = averageValue;
  }

}
