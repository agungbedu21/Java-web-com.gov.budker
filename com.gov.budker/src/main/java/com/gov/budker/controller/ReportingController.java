package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.*;
import com.gov.budker.model.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/rep/reporting")
public class ReportingController {

    @Autowired
    private AppMstBankDao daoBank;

    @Autowired
    private AppMstDivisionDao daovid;

    @Autowired
    private CalTrxCarBprsDao daoBprs;

    @Autowired
    private CalTrxFinishingDetailDao daoFinishing;

    @Autowired
    private CalTrxGrowthDetailDao daoGrowth;

    @Autowired
    private BudTrxBudgetDao daobudget;

    @Autowired
    private CalTrxCarBusDao daoBus;

    @Autowired
    private PrkProkerDao daoproker;

    private DateTimeGenerator dg = new DateTimeGenerator();

    @Autowired
    private MessageSource messageSource;
    private RptBudgetDto bus2;

    /*
     * Add a new Menu.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public ModelAndView listEmployee(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
//        try {
//            int userId = Integer.parseInt(session.getAttribute("userid").toString());
//        } catch (Exception e) {
//            return "redirect:/login";
//        }
        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Laporan");
        mvc.addObject("listYear", dg.getYear());
        mvc.addObject("listTriwulan", dg.getTriwulan());
        mvc.addObject("listDivision", daovid.findAllAppMstDivisionEntity());
        mvc.setViewName("/reporting/pages/rep_reporting_base");
        return mvc;
    }

    @RequestMapping(value = {"/growth"}, method = RequestMethod.GET)
    public ModelAndView listReportGrowth(HttpSession session, HttpServletResponse response, HttpServletRequest request) {

        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Laporan");
        mvc.addObject("listYear", dg.getYear());
        mvc.addObject("listTriwulan", dg.getTriwulan());
//        mvc.addObject("listDivision", daovid.findAllAppMstDivisionEntity());
        mvc.setViewName("/reporting/pages/rep_reporting_growth");
        return mvc;
    }

    @RequestMapping(value = {"/finishinga"}, method = RequestMethod.GET)
    public ModelAndView listReportFinishingA(HttpSession session, HttpServletResponse response, HttpServletRequest request) {

        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Laporan");
        mvc.addObject("listYear", dg.getYear());
        mvc.addObject("listTriwulan", dg.getTriwulan());
//        mvc.addObject("listDivision", daovid.findAllAppMstDivisionEntity());
        mvc.setViewName("/reporting/pages/rep_reporting_finishinga");
        return mvc;
    }


    @RequestMapping(value = {"/finishingb"}, method = RequestMethod.GET)
    public ModelAndView listReportFinishingB(HttpSession session, HttpServletResponse response, HttpServletRequest request) {

        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Laporan");
        mvc.addObject("listYear", dg.getYear());
        mvc.addObject("listTriwulan", dg.getTriwulan());
//        mvc.addObject("listDivision", daovid.findAllAppMstDivisionEntity());
        mvc.setViewName("/reporting/pages/rep_reporting_finishingb");
        return mvc;
    }

    @RequestMapping(value = {"/lb"}, method = RequestMethod.GET)
    public ModelAndView listReportLB(HttpSession session, HttpServletResponse response, HttpServletRequest request) {

        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Laporan");
        mvc.addObject("listYear", dg.getYear());
        mvc.addObject("listTriwulan", dg.getTriwulan());
        mvc.addObject("listMonth", dg.getMonth());
        mvc.setViewName("/reporting/pages/rep_reporting_lb");
        return mvc;
    }


    @RequestMapping(value = {"/getDataTableReport"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getDataTableReport(@RequestBody(required = false) Map<String, Object> params,
                                                  HttpSession session) {

        Map<String, Object> dataResult = new HashMap<>();
        BigDecimal year = null;
        BigDecimal div = null;
        BigDecimal tw = null;

        try {
            year = new BigDecimal(params.get("year").toString());
        } catch (Exception ex) {
        }
        try {
            div = new BigDecimal(params.get("division").toString());
        } catch (Exception ex) {
        }
        try {
            tw = new BigDecimal(params.get("triwulan").toString());
        } catch (Exception ex) {
        }
        List<RptReportBprsDto> listReportBprs = new ArrayList<>();
        listReportBprs = daoBprs.getListReportBprs(div, year, tw);
        List<RptReportBusDto> listReportgrowth = new ArrayList<>();
        listReportgrowth = daoBus.getListReportBus(div, year, tw);
        dataResult.put("bus", listReportgrowth);
        dataResult.put("bprs", listReportBprs);
        return dataResult;
    }

    @RequestMapping(value = {"/growth/getDataTableReport"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getDataTableReportGrowth(@RequestBody(required = false) Map<String, Object> params,
                                                        HttpSession session) {

        Map<String, Object> dataResult = new HashMap<>();
        BigDecimal year = null;
        BigDecimal div = null;
        BigDecimal tw = null;

        try {
            year = new BigDecimal(params.get("year").toString());
        } catch (Exception ex) {
        }
        try {
            tw = new BigDecimal(params.get("triwulan").toString());
        } catch (Exception ex) {
        }
        List<RptGrowthDto> listResult = daoGrowth.getListReportGrowth(div, year, tw);
        dataResult.put("growth", listResult);
        return dataResult;
    }

    @RequestMapping(value = {"/lb/getDataTableReport"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getDataTableReportLB(@RequestBody(required = false) Map<String, Object> params,
                                                    HttpSession session) {

        Map<String, Object> dataResult = new HashMap<>();
        BigDecimal year = null;
        BigDecimal month = null;
        BigDecimal tw = null;

        try {
            month = new BigDecimal(params.get("month").toString());
        } catch (Exception ex) {
        }
        try {
            year = new BigDecimal(params.get("year").toString());
        } catch (Exception ex) {
        }

        List<RptBudgetDto> listResult = daobudget.getBudgetReport(year, tw);
        List<RptProkerDto> listProker = daoproker.getProkerReport(year, tw, month);
        List<RptProkerIkuDto> listProkerIku = daoproker.getProkerIkuReport(year, tw, month);
        List<RptProkerNonIkuDto> listProkerNonIku = daoproker.getProkerNonIkuReport(year, month);
        dataResult.put("budget", listResult);
        dataResult.put("proker", listProker);
        dataResult.put("prokerIku", listProkerIku);
        dataResult.put("prokerNonIku", listProkerNonIku);
        return dataResult;
    }

    @RequestMapping(value = {"/finishinga/getDataTableReport"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getDataTableReportFinishingA(@RequestBody(required = false) Map<String, Object> params,
                                                            HttpSession session) {

        Map<String, Object> dataResult = new HashMap<>();
        BigDecimal year = null;
        BigDecimal div = null;
        BigDecimal tw = null;

        try {
            year = new BigDecimal(params.get("year").toString());
        } catch (Exception ex) {
        }

        try {
            tw = new BigDecimal(params.get("triwulan").toString());
        } catch (Exception ex) {
        }
        List<RptFinishingADetailDto> listResult = daoFinishing.getListFinishingA(div, year, tw);
        dataResult.put("finishing", listResult);
        return dataResult;
    }

    @RequestMapping(value = {"/finishingb/getDataTableReport"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getDataTableReportFinishingB(@RequestBody(required = false) Map<String, Object> params,
                                                            HttpSession session) {

        Map<String, Object> dataResult = new HashMap<>();
        BigDecimal year = null;
        BigDecimal div = null;
        BigDecimal tw = null;

        try {
            year = new BigDecimal(params.get("year").toString());
        } catch (Exception ex) {
        }
        try {
            tw = new BigDecimal(params.get("triwulan").toString());
        } catch (Exception ex) {
        }
        List<RptFinishingADetailDto> listResult = daoFinishing.getListFinishingB(div, year, tw);
        dataResult.put("finishing", listResult);
        return dataResult;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("bankId")) {
            BigDecimal id = new BigDecimal(params.get("bankId").toString());
            AppMstBank data = daoBank.findById(id);
            if (data != null) {
                mvc.addObject("dataList", data);
            }
        }

        mvc.setViewName("/app/partialPages/modal_app_mst_bank");
        return mvc;
    }


    @RequestMapping(value = {"/downloadExcel/{divId}/{year}/{triwulan}"}, method = RequestMethod.GET)
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response,
                              @PathVariable(value = "divId") BigDecimal divId,
                              @PathVariable(value = "year") BigDecimal year,
                              @PathVariable(value = "triwulan") BigDecimal tw) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();


        response.setHeader("Content-Disposition", "attachment; filename=Report-CarBus-CarBprs-(" + new Date() + ").xlsx");

        List<RptReportBusDto> listGrowthData = daoBus.getListReportBus(divId, year, tw);
        List<RptReportBprsDto> listBprs = daoBprs.getListReportBprs(divId, year, tw);

        XSSFSheet growthSheet = workbook.createSheet("CAR BUS");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        Row rowHeader = growthSheet.createRow(0);

        Cell cellTitle = rowHeader.createCell(0);
        cellTitle.setCellStyle(titleStyle);
        cellTitle.setCellValue("Laporan CAR BUS Tahun " + year.toString());
        growthSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

        rowHeader = growthSheet.createRow(1);
        Cell cellHeader = rowHeader.createCell(0);
        cellHeader.setCellValue("CAR BUS");
        growthSheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));

        cellHeader = rowHeader.createCell(6);
        cellHeader.setCellValue("Target");

        if (listGrowthData.size() > 0) {
            cellHeader = rowHeader.createCell(7);
            cellHeader.setCellValue(listGrowthData.get(0).getIkuTarget() + "0%");
        } else {
            cellHeader = rowHeader.createCell(7);
            cellHeader.setCellValue("0%");
        }


        int rowNum = 3;
        int colnum = 0;
        Row row = growthSheet.createRow(rowNum++);

        Cell cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Divisi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Bank");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("CAR growth");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Profil Resiko");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Car Minimum");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Delta Car");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pencapaian");

        if (listGrowthData.size() <= 0) {
            row = growthSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            growthSheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
        }
        int count = 1;
        for (RptReportBusDto growth : listGrowthData) {
            colnum = 0;
            row = growthSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getDivisionName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getBankName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getCarBusValue() + "%");

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getRiskProfile());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getCarMin() + "%");

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getDeltaCar() + "%");

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getAch() + "%");
        }
        if (listGrowthData.size() > 0) {
            row = growthSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Capaian IKU");
            growthSheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 6));

            cell = row.createCell(7);
            cell.setCellValue(listGrowthData.get(0).getAch().toString() + "%");
        }

        // car bprs

        XSSFSheet bprsSheet = workbook.createSheet("CAR BPRS");

        rowHeader = bprsSheet.createRow(0);
        Cell cellTitleB = rowHeader.createCell(0);
        cellTitleB.setCellStyle(titleStyle);
        cellTitleB.setCellValue("Laporan CAR BPRS Tahun " + year.toString());
        bprsSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        rowHeader = bprsSheet.createRow(1);
        cellHeader = rowHeader.createCell(0);
        cellHeader.setCellValue("CAR BPRS");
        bprsSheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));

        cellHeader = rowHeader.createCell(3);
        cellHeader.setCellValue("Target");


        if (listBprs.size() > 0) {
            cellHeader = rowHeader.createCell(4);
            cellHeader.setCellValue(listBprs.get(0).getIkuTarget() + "%");
        } else {
            cellHeader = rowHeader.createCell(4);
            cellHeader.setCellValue("0%");
        }

        rowNum = 2;
        colnum = 0;
        row = bprsSheet.createRow(rowNum++);

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Divisi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Status Pengawasan");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("CAR");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Sesuai");


        if (listBprs.size() <= 0) {
            row = bprsSheet.createRow(rowNum++);
            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            growthSheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));
        }
        count = 1;
        for (RptReportBprsDto bprs : listBprs) {
            colnum = 0;
            row = bprsSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(bprs.getDivisionName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            String supStatus = null;
            if (bprs.getIsBalance() == 1) {
                supStatus = "Normal";
            } else {
                supStatus = "Intensif";
            }
            cell.setCellValue(supStatus);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(bprs.getCar() + "%");

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            String isBalance = null;
            if (bprs.getIsBalance() == 1) {
                isBalance = "Ya";
            } else {
                isBalance = "Tidak";
            }
            cell.setCellValue(isBalance);

        }
        if (listBprs.size() > 0) {
            row = bprsSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Capaian IKU");
            bprsSheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 3));

            cell = row.createCell(4);
            cell.setCellValue(listBprs.get(0).getAch().toString() + "%");
        }


        for (int i = 0; i < 9; i++) {
            growthSheet.autoSizeColumn(i);
            bprsSheet.autoSizeColumn(i);
        }
        OutputStream stream = response.getOutputStream();
        workbook.write(stream); // Write workbook to response.
        stream.flush();
        stream.close();
        workbook.close();
    }

    @RequestMapping(value = {"/growth/downloadExcel/{divId}/{year}/{triwulan}"}, method = RequestMethod.GET)
    public void downloadExcelGrowth(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable(value = "divId") BigDecimal divId,
                                    @PathVariable(value = "year") BigDecimal year,
                                    @PathVariable(value = "triwulan") BigDecimal tw) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();


        response.setHeader("Content-Disposition", "attachment; filename=Report-Growth-(" + new Date() + ").xlsx");

        List<RptGrowthDto> listGrowthData = daoGrowth.getListReportGrowth(divId, year, tw);

        XSSFSheet growthSheet = workbook.createSheet("Laporan Pertumbuhan");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        double c = 0, d = 0, e = 0;
        double realisasi = 0;
        double ach = 0;
        for (RptGrowthDto growth : listGrowthData) {
            if (growth.getGrowthTarget() == 1) {
                if (growth.getLastYearValue() != null) {
                    c += Double.parseDouble(growth.getLastYearValue());
                }
                d += Double.parseDouble(growth.getYoyCost());
                e += Double.parseDouble(growth.getMonthCost());
            }
        }
        realisasi = ((e - d) / d * 100);
        float ikuTarget = listGrowthData.get(0).getIkuTarget();
        ach = (realisasi / ikuTarget) * 100;
        if (ach > 110) {
            ach = 110;
        }
        Row rowHeader = growthSheet.createRow(0);

        Cell cellTitle = rowHeader.createCell(0);
        cellTitle.setCellStyle(titleStyle);
        cellTitle.setCellValue("LAPORAN PERTUMBUHAN BANK");
        growthSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

        rowHeader = growthSheet.createRow(1);
        Cell cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Tahun");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(year.toString());

        cellHeader = rowHeader.createCell(6);
        cellHeader.setCellValue("Triwulan");

        cellHeader = rowHeader.createCell(7);
        cellHeader.setCellValue(tw.toString());

        //================
        rowHeader = growthSheet.createRow(3);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Realisasi");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(realisasi);

        rowHeader = growthSheet.createRow(4);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Target Iku");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(listGrowthData.get(0).getIkuTarget());

        rowHeader = growthSheet.createRow(5);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Pencapaian");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(ach);

        int rowNum = 6;
        int colnum = 0;
        Row row = growthSheet.createRow(rowNum++);

        Cell cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Bank");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pertumbuhan RBB");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Target Tumbuh");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pembiayaan Desember Y-1");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pemnbiayaan Bulan Y-1");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pembiayaan Bulan Y");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pembiayaan Yoy");

        if (listGrowthData.size() <= 0) {
            row = growthSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            growthSheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 7));
        }
        int count = 1;
        for (RptGrowthDto growth : listGrowthData) {
            colnum = 0;
            row = growthSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getBankName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getRbbGrowth());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            if (growth.getGrowthTarget() == 1) {
                cell.setCellValue("Ya");
            } else {
                cell.setCellValue("Tidak");
            }
            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            if (growth.getLastYearValue() != null) {
                cell.setCellValue(growth.getLastYearValue());
            } else {
                cell.setCellValue("-");
            }
            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getYoyCost());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getMonthCost());


            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(growth.getGrowthValue());

        }
        if (listGrowthData.size() > 0) {
            row = growthSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Total Pembiayaan (Target Tumbuh = Ya)");
            growthSheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 3));

            cell = row.createCell(4);
            cell.setCellValue(c);
            cell = row.createCell(5);
            cell.setCellValue(d);
            cell = row.createCell(6);
            cell.setCellValue(e);
        }

        for (int i = 0; i < 9; i++) {
            growthSheet.autoSizeColumn(i);
        }
        OutputStream stream = response.getOutputStream();
        workbook.write(stream); // Write workbook to response.
        stream.flush();
        stream.close();
        workbook.close();
    }

    @RequestMapping(value = {"/finishinga/downloadExcel/{divId}/{year}/{triwulan}"}, method = RequestMethod.GET)
    public void downloadExcelFinishingA(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable(value = "divId") BigDecimal divId,
                                        @PathVariable(value = "year") BigDecimal year,
                                        @PathVariable(value = "triwulan") BigDecimal tw) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();


        response.setHeader("Content-Disposition", "attachment; filename=Report-Hasil-Pemeriksaan-(" + new Date() + ").xlsx");

        List<RptFinishingADetailDto> listFinishingData = daoFinishing.getListFinishingA(divId, year, tw);

        XSSFSheet finSheet = workbook.createSheet("Laporan Hasil Pemeriksaan");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        double c = 0, d = 0;
        double realisasi = 0;
        double ach = 0;
        for (RptFinishingADetailDto fin : listFinishingData) {
            c += Double.parseDouble(fin.getProblems());
            d += Double.parseDouble(fin.getRealization());
        }
        realisasi = ((d / c) * 100);
        ach = (realisasi / listFinishingData.get(0).getIkuTarget()) * 100;
        if (ach > 110) {
            ach = 110;
        }
        Row rowHeader = finSheet.createRow(0);

        Cell cellTitle = rowHeader.createCell(0);
        cellTitle.setCellStyle(titleStyle);
        cellTitle.setCellValue("LAPORAN HASIL PEMERIKSAAN");
        finSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        rowHeader = finSheet.createRow(1);
        Cell cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Tahun");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(year.toString());

        cellHeader = rowHeader.createCell(4);
        cellHeader.setCellValue("Triwulan");

        cellHeader = rowHeader.createCell(5);
        cellHeader.setCellValue(tw.toString());

        //================
        rowHeader = finSheet.createRow(3);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Realisasi");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(realisasi);

        rowHeader = finSheet.createRow(4);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Target Iku");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(listFinishingData.get(0).getIkuTarget());

        rowHeader = finSheet.createRow(5);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Pencapaian");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(ach);

        int rowNum = 6;
        int colnum = 0;
        Row row = finSheet.createRow(rowNum++);

        Cell cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Bank");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Komitmen yang Harus Ditindaklanjuti Bank");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Komitmen yang Sudah Ditindaklanjuti Bank");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pencapaian");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Keterangan");

        if (listFinishingData.size() <= 0) {
            row = finSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            finSheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 5));
        }
        int count = 1;
        for (RptFinishingADetailDto fin : listFinishingData) {
            colnum = 0;
            row = finSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fin.getBankName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fin.getProblems());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fin.getRealization());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            double a = 0, b = 0;
            a = Double.parseDouble(fin.getRealization());
            b = Double.parseDouble(fin.getProblems());
            cell.setCellValue((a / b) * 100);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fin.getExplanation());

        }
        if (listFinishingData.size() > 0) {
            row = finSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Total ");
            finSheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 1));

            cell = row.createCell(2);
            cell.setCellValue(c);
            cell = row.createCell(3);
            cell.setCellValue(d);
        }

        for (int i = 0; i < 9; i++) {
            finSheet.autoSizeColumn(i);
        }
        OutputStream stream = response.getOutputStream();
        workbook.write(stream); // Write workbook to response.
        stream.flush();
        stream.close();
        workbook.close();
    }

    @RequestMapping(value = {"/finishingb/downloadExcel/{divId}/{year}/{triwulan}"}, method = RequestMethod.GET)
    public void downloadExcelFinishingB(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable(value = "divId") BigDecimal divId,
                                        @PathVariable(value = "year") BigDecimal year,
                                        @PathVariable(value = "triwulan") BigDecimal tw) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();


        response.setHeader("Content-Disposition", "attachment; filename=Report-Permasalahan-SP-(" + new Date() + ").xlsx");

        List<RptFinishingADetailDto> listFinishingData = daoFinishing.getListFinishingB(divId, year, tw);

        XSSFSheet finSheet = workbook.createSheet("Laporan Permasalahan SP");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        double c = 0, d = 0;
        double realisasi = 0;
        double ach = 0;
        for (RptFinishingADetailDto fin : listFinishingData) {
            c += Double.parseDouble(fin.getProblems());
            d += Double.parseDouble(fin.getRealization());
        }
        realisasi = ((d / c) * 100);
        ach = (realisasi / listFinishingData.get(0).getIkuTarget()) * 100;
        if (ach > 110) {
            ach = 110;
        }
        Row rowHeader = finSheet.createRow(0);

        Cell cellTitle = rowHeader.createCell(0);
        cellTitle.setCellStyle(titleStyle);
        cellTitle.setCellValue("LAPORAN PERMASALAHAN SP");
        finSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        rowHeader = finSheet.createRow(1);
        Cell cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Tahun");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(year.toString());

        cellHeader = rowHeader.createCell(4);
        cellHeader.setCellValue("Triwulan");

        cellHeader = rowHeader.createCell(5);
        cellHeader.setCellValue(tw.toString());

        //================
        rowHeader = finSheet.createRow(3);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Realisasi");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(realisasi);

        rowHeader = finSheet.createRow(4);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Target Iku");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(listFinishingData.get(0).getIkuTarget());

        rowHeader = finSheet.createRow(5);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Pencapaian");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(ach);

        int rowNum = 6;
        int colnum = 0;
        Row row = finSheet.createRow(rowNum++);

        Cell cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Bank");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Komitmen yang Harus Ditindaklanjuti Bank");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Komitmen yang Sudah Ditindaklanjuti Bank");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pencapaian");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Keterangan");

        if (listFinishingData.size() <= 0) {
            row = finSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            finSheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 5));
        }
        int count = 1;
        for (RptFinishingADetailDto fin : listFinishingData) {
            colnum = 0;
            row = finSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fin.getBankName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fin.getProblems());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fin.getRealization());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            double a = 0, b = 0;
            a = Double.parseDouble(fin.getRealization());
            b = Double.parseDouble(fin.getProblems());
            cell.setCellValue((a / b) * 100);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fin.getExplanation());

        }
        if (listFinishingData.size() > 0) {
            row = finSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Total ");
            finSheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 1));

            cell = row.createCell(2);
            cell.setCellValue(c);
            cell = row.createCell(3);
            cell.setCellValue(d);
        }

        for (int i = 0; i < 9; i++) {
            finSheet.autoSizeColumn(i);
        }
        OutputStream stream = response.getOutputStream();
        workbook.write(stream); // Write workbook to response.
        stream.flush();
        stream.close();
        workbook.close();
    }

    @RequestMapping(value = {"/lb/downloadExcel/{year}/{month}"}, method = RequestMethod.GET)
    public void downloadExcelLb(HttpServletRequest request, HttpServletResponse response,
                                @PathVariable(value = "year") BigDecimal year,
                                @PathVariable(value = "month") BigDecimal month) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();


        response.setHeader("Content-Disposition", "attachment; filename=Report-Permasalahan-SP-(" + new Date() + ").xlsx");

        List<RptBudgetDto> listBudGetData = daobudget.getBudgetReport(year, month);

        Double tw = (Double.parseDouble(month.toString()) / 3);
        tw = Math.ceil(tw);

        XSSFSheet finSheet = workbook.createSheet("Laporan Bulanan");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        Row rowHeader = finSheet.createRow(0);

        Cell cellTitle = rowHeader.createCell(0);
        cellTitle.setCellStyle(titleStyle);
        cellTitle.setCellValue("LAPORAN ANGGARAN DAN PROGRAM KERJA");
        finSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        rowHeader = finSheet.createRow(1);
        Cell cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Tahun");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(year.toString());

        rowHeader = finSheet.createRow(2);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Bulan");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue(month.toString());

        rowHeader = finSheet.createRow(3);
        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Divisi");
        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue("WAS1, WAS2, WAS3, IPAS");

        finSheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 5));
        rowHeader = finSheet.createRow(4);
        cellHeader = rowHeader.createCell(0);
        cellHeader.setCellStyle(titleStyle);
        cellHeader.setCellValue("TOTAL ANGGARAN");

        int rowNum = 5;
        int colnum = 0;
        Row row = finSheet.createRow(rowNum++);

        Cell cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("TW");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nominal Anggaran");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Persen Anggaran");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nominal Realisasi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Subsequences");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Persen Realisasi");

        if (listBudGetData.size() <= 0) {
            row = finSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            finSheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 5));
        }
        int count = 1;
        DecimalFormat df = new DecimalFormat("#.##########");
        for (int i = 0; i < tw; i++) {
            RptBudgetDto bud = listBudGetData.get(i);
            RptBudgetDto bud2 = listBudGetData.get(i + 4);
            colnum = 0;
            row = finSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(bud.getFieldA());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(bud.getFieldB());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(bud2.getFieldA());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(bud2.getFieldB());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            double res = (bud2.getFieldA() / bud.getFieldA()) * 100;
            cell.setCellValue(df.format(res));

        }

// Anggaran Proker

        rowNum = rowNum + 2;
        finSheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
        rowHeader = finSheet.createRow(rowNum);
        cellHeader = rowHeader.createCell(0);
        cellHeader.setCellStyle(titleStyle);
        cellHeader.setCellValue("ANGGARAN PROGRAM KERJA");

        colnum = 0;
        rowNum = rowNum + 1;
        row = finSheet.createRow(rowNum++);

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Kode IKU");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Proker");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Rencana Anggaran");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Realisasi Anggaran");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Subsequence");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Persen Anggaran");

        List<RptProkerDto> listProker = daoproker.getProkerReport(year, null, month);

        if (listProker.size() <= 0) {
            row = finSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            finSheet.addMergedRegion(new CellRangeAddress(rowNum++, rowNum++, 0, 5));
        }


        count = 1;
        for (RptProkerDto prk : listProker) {
            colnum = 0;
            row = finSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(prk.getIkuCode());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(prk.getProkerName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(prk.getProkerBudget());
            } catch (Exception ex) {
                cell.setCellValue("");
            }

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(prk.getRealization());
            } catch (Exception ex) {
                cell.setCellValue("");
            }

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(prk.getSubsequence());
            } catch (Exception ex) {
                cell.setCellValue("");
            }

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            double bud = 1;
            try {
                bud = Double.parseDouble(prk.getProkerBudget());
            } catch (Exception ex) {
                bud = 1;
            }

            double prkk = 1;
            try {
                prkk = prk.getRealization();
            } catch (Exception ex) {
                prkk = 1;
            }
            double res = (prkk / bud) * 100;
            cell.setCellValue(df.format(res));

        }

        //  Proker Iku

        rowNum = rowNum + 2;
        finSheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
        rowHeader = finSheet.createRow(rowNum);
        cellHeader = rowHeader.createCell(0);
        cellHeader.setCellStyle(titleStyle);
        cellHeader.setCellValue("PROGRAM KERJA IKU");

        colnum = 0;
        rowNum = rowNum + 1;
        row = finSheet.createRow(rowNum++);

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Kode IKU");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Proker");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("PIC");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Target");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Period");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Realisasi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Pencapaian");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Penjelasan");

        List<RptProkerIkuDto> listProkerIku = daoproker.getProkerIkuReport(year, null, month);

        if (listProkerIku.size() <= 0) {
            row = finSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            finSheet.addMergedRegion(new CellRangeAddress(rowNum++, rowNum++, 0, 5));
        }


        count = 1;
        int i = 0;
        for (int j = 0; j < tw; j++) {
            RptProkerIkuDto prk = listProkerIku.get(i);

            double target = 0, real = 0, ach = 0;
            for (int n = 0; n < tw; n++) {
                try {
                    target = target + Double.parseDouble(listProkerIku.get(n + i).getTarget());
                } catch (Exception ex) {
                    target = target + 0;
                }
                try {
                    real = real + Double.parseDouble(listProkerIku.get(n + i).getRealization());
                } catch (Exception ex) {
                    real = real + 0;
                }
                try {
                    ach = ach + Double.parseDouble(listProkerIku.get(n + i).getAchievement());
                } catch (Exception ex) {
                    ach = ach + 0;
                }
            }
            colnum = 0;
            row = finSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(prk.getIkuCode());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(prk.getProkerName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(prk.getPicName());
            } catch (Exception ex) {
                cell.setCellValue("");
            }

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(target);
            } catch (Exception ex) {
                cell.setCellValue("");
            }

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(prk.getPeriode());
            } catch (Exception ex) {
                cell.setCellValue("");
            }

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(real);
            } catch (Exception ex) {
                cell.setCellValue("");
            }

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(ach);
            } catch (Exception ex) {
                cell.setCellValue("");
            }

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            try {
                cell.setCellValue(prk.getExplanation());
            } catch (Exception ex) {
                cell.setCellValue("");
            }

        }


        //  Proker NON Iku

        rowNum = rowNum + 2;
        finSheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
        rowHeader = finSheet.createRow(rowNum);
        cellHeader = rowHeader.createCell(0);
        cellHeader.setCellStyle(titleStyle);
        cellHeader.setCellValue("PROGRAM KERJA NON IKU");

        colnum = 0;
        rowNum = rowNum + 1;
        row = finSheet.createRow(rowNum++);

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Proker");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("PIC");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Target Waktu Penyelesaian");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Rencana M-1");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Rencana M");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Rencana M+1");


        List<RptProkerNonIkuDto> listProkerNonIku = daoproker.getProkerNonIkuReport(year, month);

        if (listProkerNonIku.size() <= 0) {
            row = finSheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Tidak ada data");
            finSheet.addMergedRegion(new CellRangeAddress(rowNum++, rowNum++, 0, 5));
        }


        count = 1;
        i = 0;
        int mt = Integer.parseInt(month.toString());
        double len = Math.ceil(listProkerNonIku.size() / 12);
        for (int j = 0; j < len; j++) {
            String progress = "-", progressM = "-", progressP = "-";
            for (int n = 0; n < mt; n++) {
                if (mt == 1) {
                    progressM = "-";
                    try {
                        progress = listProkerNonIku.get(n + i).getProgress();
                    } catch (Exception ex) {
                        progress = "-";
                    }
                    if (i < 11) {
                        try {
                            progressP = listProkerNonIku.get(n + i).getProgress();
                        } catch (Exception ex) {
                            progressP = "-";
                        }
                    }
                } else if (mt == 12) {
                    if (i > 0) {
                        try {
                            progressM = listProkerNonIku.get((mt - 2) + i).getProgress();
                        } catch (Exception ex) {
                            progressM = "-";
                        }
                    }
                    try {
                        progress = listProkerNonIku.get((mt - 1) + i).getProgress();
                    } catch (Exception ex) {
                        progress = "-";
                    }
                } else {
                    if (n > 0) {
                        try {
                            progressM = listProkerNonIku.get(n + i).getProgress();
                        } catch (Exception ex) {
                            progressM = "-";
                        }
                    }

                    try {
                        progress = listProkerNonIku.get(n + i).getProgress();
                    } catch (Exception ex) {
                        progress = "-";
                    }

                    try {
                        progress = listProkerNonIku.get(n + i).getProgress();
                    } catch (Exception ex) {
                        progress = "-";
                    }

                }
            }
            colnum = 0;
            row = finSheet.createRow(rowNum++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count++);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(listProkerNonIku.get(i).getProkerName());

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(listProkerNonIku.get(i).getPicName());


            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            Date date = listProkerNonIku.get(i).getDateTarget();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(date);
            cell.setCellValue(dateString);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(progressM);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(progress);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(progressP);

        }
        for (i = 0; i < 9; i++) {
            finSheet.autoSizeColumn(i);
        }
        OutputStream stream = response.getOutputStream();
        workbook.write(stream); // Write workbook to response.
        stream.flush();
        stream.close();
        workbook.close();
    }
}
