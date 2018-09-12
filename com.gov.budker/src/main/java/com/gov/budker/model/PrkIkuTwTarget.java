package com.gov.budker.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Where(clause = "deleted_status = 0")
@Table(name = "prk_iku_tw_target", schema = "budproker", catalog = "")
public class PrkIkuTwTarget {
  private BigDecimal ikuTwId;
  private BigDecimal ikuTw;
  private BigDecimal prokerId;
  private String ikuTarget;
  private BigDecimal status;
  private BigDecimal deletedStatus;

  @Id
  @GeneratedValue
  @Column(name = "iku_tw_id")
  public BigDecimal getIkuTwId() {
    return ikuTwId;
  }

  public void setIkuTwId(BigDecimal ikuTwId) {
    this.ikuTwId = ikuTwId;
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
  @Column(name = "iku_tw")
  public BigDecimal getIkuTw() {
    return ikuTw;
  }

  public void setIkuTw(BigDecimal ikuTw) {
    this.ikuTw = ikuTw;
  }

  @Basic
  @Column(name = "iku_target")
  public String getIkuTarget() {
    return ikuTarget;
  }

  public void setIkuTarget(String ikuTarget) {
    this.ikuTarget = ikuTarget;
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
