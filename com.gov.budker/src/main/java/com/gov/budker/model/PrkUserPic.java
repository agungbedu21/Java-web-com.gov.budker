package com.gov.budker.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prk_user_pic", schema = "budproker", catalog = "")
public class PrkUserPic {
    private BigDecimal userPicId;
    private BigDecimal prokerId;
    private BigDecimal userId;


    @Id
    @GeneratedValue
    @Column(name = "user_pic_id")
    public BigDecimal getUserPicId() {
        return userPicId;
    }

    public void setUserPicId(BigDecimal userPicId) {
        this.userPicId = userPicId;
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
    @Column(name = "user_id")
    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

}
