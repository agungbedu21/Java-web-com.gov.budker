package com.gov.budker.model;


import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "rlz_trx_realization_month", schema = "budproker", catalog = "")
public class RlzTrxRealizationMonth {

  private BigDecimal realizationMonthId;
  private BigDecimal prokerId;
  private BigDecimal triwulan;
  private BigDecimal monthTgtId;
  private String target;
  private String realization;
  private String progress;
  private String subsequence;
  private BigDecimal rlzHeaderId;
  private BigDecimal status;
  private BigDecimal deletedStatus;
  private BigDecimal userCreted;
  private Date dateCreated;
  private BigDecimal userUpdated;
  private Date dateUpdated;
  private BigDecimal userDeleted;
  private Date dateDeleted;

  @Id
  @GeneratedValue
  @Column(name = "realization_month_id")
  public BigDecimal getRealizationMonthId() {
    return realizationMonthId;
  }

  public void setRealizationMonthId(BigDecimal realizationMonthId) {
    this.realizationMonthId = realizationMonthId;
  }

  @Formula("(select m.target from prk_month_target m where m.month_target_id = month_tgt_id )")
  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  @Basic
  @Column(name = "triwulan")
  public BigDecimal getTriwulan() {
    return triwulan;
  }

  public void setTriwulan(BigDecimal triwulan) {
    this.triwulan = triwulan;
  }

  @Basic
  @Column(name = "proker_id")
  public BigDecimal getProkerId() {
    return prokerId;
  }

  public void setProkerId(BigDecimal prokerId) {
    this.prokerId = prokerId;
  }

  @Basic
  @Column(name = "rlz_header_id")
  public BigDecimal getRlzHeaderId() {
    return rlzHeaderId;
  }

  public void setRlzHeaderId(BigDecimal rlzHeaderId) {
    this.rlzHeaderId = rlzHeaderId;
  }

  @Basic
  @Column(name = "month_tgt_id")
  public BigDecimal getMonthTgtId() {
    return monthTgtId;
  }

  public void setMonthTgtId(BigDecimal monthTgtId) {
    this.monthTgtId = monthTgtId;
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
  @Column(name = "progress")
  public String getProgress() {
    return progress;
  }

  public void setProgress(String progress) {
    this.progress = progress;
  }

  @Basic
  @Column(name = "subsequence")
  public String getSubsequence() {
    return subsequence;
  }

  public void setSubsequence(String subsequence) {
    this.subsequence = subsequence;
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

  @Basic
  @Column(name = "user_created")
  public BigDecimal getUserCreted() {
    return userCreted;
  }

  public void setUserCreted(BigDecimal userCreted) {
    this.userCreted = userCreted;
  }

  @Basic
  @Column(name = "date_created")
  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  @Basic
  @Column(name = "user_updated")
  public BigDecimal getUserUpdated() {
    return userUpdated;
  }

  public void setUserUpdated(BigDecimal userUpdated) {
    this.userUpdated = userUpdated;
  }

  @Basic
  @Column(name = "date_updated")
  public Date getDateUpdated() {
    return dateUpdated;
  }

  public void setDateUpdated(Date dateUpdated) {
    this.dateUpdated = dateUpdated;
  }

  @Basic
  @Column(name = "user_deleted")
  public BigDecimal getUserDeleted() {
    return userDeleted;
  }

  public void setUserDeleted(BigDecimal userDeleted) {
    this.userDeleted = userDeleted;
  }

  @Basic
  @Column(name = "date_deleted")
  public Date getDateDeleted() {
    return dateDeleted;
  }

  public void setDateDeleted(Date dateDeleted) {
    this.dateDeleted = dateDeleted;
  }

}
