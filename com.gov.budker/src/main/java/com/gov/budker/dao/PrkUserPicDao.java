package com.gov.budker.dao;

import com.gov.budker.model.PrkUserPic;

import java.math.BigDecimal;
import java.util.List;

public interface PrkUserPicDao {

    PrkUserPic findById(BigDecimal id);

    BigDecimal saveEditDeletePrkUserPicEntity(PrkUserPic prkUserPic);

    List<PrkUserPic> findAllPrkUserPicEntity(BigDecimal prokerId);

    void saveListDivisionBank(List<PrkUserPic> listUserPic);

}
