package com.gov.budker.model;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "rlz_trx_realization_iku", schema = "budproker", catalog = "")
public class RlzTrxRealizationIku {

  private BigDecimal realizationIkuId;
  private BigDecimal prokerId;
  private BigDecimal ikuTwId;
  private String target;
  private String periode;
  private String realization;
  private String achievement;
  private String explanation;
  private BigDecimal status;
  private BigDecimal deletedStatus;
  private BigDecimal userCreated;
  private Date dateCreated;
  private BigDecimal userUpdated;
  private Date dateUpdated;
  private BigDecimal userDeleted;
  private Date dateDeleted;


  @Id
  @GeneratedValue
  @Column(name = "realization_iku_id")
  public BigDecimal getRealizationIkuId() {
    return realizationIkuId;
  }

  public void setRealizationIkuId(BigDecimal realizationIkuId) {
    this.realizationIkuId = realizationIkuId;
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
  @Column(name = "iku_tw_id")
  public BigDecimal getIkuTwId() {
    return ikuTwId;
  }

  public void setIkuTwId(BigDecimal ikuTwId) {
    this.ikuTwId = ikuTwId;
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
  @Column(name = "periode")
  public String getPeriode() {
    return periode;
  }

  public void setPeriode(String periode) {
    this.periode = periode;
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
  @Column(name = "achievement")
  public String getAchievement() {
    return achievement;
  }

  public void setAchievement(String achievement) {
    this.achievement = achievement;
  }

  @Basic
  @Column(name = "explanation")
  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
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
  public BigDecimal getUserCreated() {
    return userCreated;
  }

  public void setUserCreated(BigDecimal userCreted) {
    this.userCreated = userCreated;
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
