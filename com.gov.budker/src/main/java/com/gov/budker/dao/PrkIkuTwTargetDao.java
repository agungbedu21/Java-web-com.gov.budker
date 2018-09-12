package com.gov.budker.dao;

import com.gov.budker.model.PrkIkuTwTarget;

import java.math.BigDecimal;
import java.util.List;

public interface PrkIkuTwTargetDao {

    PrkIkuTwTarget findById(BigDecimal id);

    void saveEditDeletePrkIkuTwTargetEntity(PrkIkuTwTarget ikuTwEntity);

    List<PrkIkuTwTarget> findAllPrkIkuTwTargetEntity();

    List<PrkIkuTwTarget> findPrkIkuTwTargetByprokerId(BigDecimal prokerId);
}
