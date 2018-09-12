package com.gov.budker.dao;

import com.gov.budker.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PrkProkerDao {

    PrkProker findById(BigDecimal id);

    void savePrkProkerEntity(PrkProker budget);

    void saveCompleteProker(PrkProker budget, List<PrkIkuTwTarget> listIkuTarget, List<PrkMonthTarget> listMonthTarget, List<PrkUserPic> listPic);

    public BigDecimal saveOrUpdate(PrkProker budget);

    void deletePrkProkerEntity(BigDecimal Id);

    List<PrkProker> findAllPrkProkerEntity();

    List<PrkProker> findPrkProkerEntity(BigDecimal PrkProkerEntityId);

    List<PrkProker> getProkerByDivisionId(BigDecimal divisionId, BigDecimal year);

    Map<String, Object> getProkerDetail(BigDecimal year, List month);

    List<DsbProkerListDto> listDtoProker(BigDecimal year);

    DsbPieDto getDataPiePrk(BigDecimal year, List monthList);

    List<RptProkerDto> getProkerReport(BigDecimal year, BigDecimal tw, BigDecimal month);

    List<RptProkerIkuDto> getProkerIkuReport(BigDecimal year, BigDecimal tw, BigDecimal month);

    List<RptProkerNonIkuDto> getProkerNonIkuReport(BigDecimal year, BigDecimal month);

}
