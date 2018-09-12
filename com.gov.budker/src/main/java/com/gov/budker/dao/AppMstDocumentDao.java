package com.gov.budker.dao;

import com.gov.budker.model.AppMstDocument;

import java.math.BigDecimal;
import java.util.List;

public interface AppMstDocumentDao {

    AppMstDocument findById(BigDecimal id);

    void saveEditDeleteAppMstDocumentEntity(AppMstDocument appMstDocument);

    List<AppMstDocument> findAllAppMstDocumentEntity();

}
