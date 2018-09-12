package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstBankDao;
import com.gov.budker.dao.AppMstBankTypeDao;
import com.gov.budker.dao.AppMstBankTypeDetailDao;
import com.gov.budker.model.AppMstBank;
import com.gov.budker.model.AppMstBankType;
import com.gov.budker.model.AppMstBankTypeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app/banktype")
public class AppMstBankTypeController {

    @Autowired
    private AppMstBankTypeDao daoBankType;

    @Autowired
    private AppMstBankTypeDetailDao daoDetail;

    @Autowired
    private AppMstBankDao daoBank;

    @Autowired
    private MessageSource messageSource;

    /*
     * Add a new Menu.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
//        try {
//            int userId = Integer.parseInt(session.getAttribute("userid").toString());
//        } catch (Exception e) {
//            return "redirect:/login";
//        }
        ModelAndView mvc = new ModelAndView();
        session.setAttribute("menuName", "Tipe Bank");
        return "/app/pages/app_mst_bank_type";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstBankType.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("bankTypeId")) {
            BigDecimal id = new BigDecimal(params.get("bankTypeId").toString());
            AppMstBankType data = daoBankType.findById(id);
            if (data != null) {
                mvc.addObject("dataList", data);
            }
            List<AppMstBankTypeDetail> listDet = new ArrayList<>();
            listDet = daoDetail.findAppMstBankTypeDetailEntityByBankTypeId(id);
            mvc.addObject("listBankType", listDet);
        }
        List<AppMstBank> listBank = new ArrayList<>();
        listBank = daoBank.findAllAppMstBankEntity();
        mvc.addObject("listBank", listBank);
        mvc.setViewName("/app/partialPages/modal_app_mst_bank_type");
        return mvc;
    }


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params,
                                              @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstBankType bankTypeData = new AppMstBankType();
        List bankIdList = new ArrayList();
        bankIdList = (List) params.get("bankList");
        AppMstBankTypeDetail typeDetail = new AppMstBankTypeDetail();
        List<AppMstBankTypeDetail> listDetail = new ArrayList<>();
        try {
            if (isSave == 1) {
                bankTypeData = new AppMstBankType();
                bankTypeData.setBankTypeName(params.get("bankTypeName").toString());
                bankTypeData.setStatus(new BigDecimal(params.get("status").toString()));
                bankTypeData.setDeletedStatus(BigDecimal.ZERO);
                BigDecimal typeId = daoBankType.saveEditDeleteAppMstBankTypeEntity(bankTypeData);
                for (int i = 0; i < bankIdList.size(); i++) {
                    typeDetail = new AppMstBankTypeDetail();
                    typeDetail.setBankId(new BigDecimal(bankIdList.get(i).toString()));
                    typeDetail.setDeletedStatus(BigDecimal.ZERO);
                    typeDetail.setStatus(BigDecimal.ONE);
                    typeDetail.setTypeId(typeId);
                    listDetail.add(typeDetail);
                }
                daoDetail.saveEditDeleteAppMstBankTypeListEntity(listDetail);
            } else if (isSave == 2) {
                bankTypeData = new AppMstBankType();
                bankTypeData = daoBankType.findById(new BigDecimal(params.get("bankTypeId").toString()));
                bankTypeData.setBankTypeName(params.get("bankTypeName").toString());
                bankTypeData.setStatus(new BigDecimal(params.get("status").toString()));
                bankTypeData.setDeletedStatus(BigDecimal.ZERO);
                daoBankType.saveEditDeleteAppMstBankTypeEntity(bankTypeData);
                for (int i = 0; i < bankIdList.size(); i++) {
                    typeDetail = new AppMstBankTypeDetail();
                    typeDetail.setBankId(new BigDecimal(bankIdList.get(i).toString()));
                    typeDetail.setDeletedStatus(BigDecimal.ZERO);
                    typeDetail.setStatus(BigDecimal.ONE);
                    typeDetail.setTypeId(bankTypeData.getBankTypeId());
                    listDetail.add(typeDetail);
                }
                daoDetail.saveEditDeleteAppMstBankTypeListEntity(listDetail);
            } else {
                bankTypeData = new AppMstBankType();
                BigDecimal id = new BigDecimal(params.get("bankTypeId").toString());
                bankTypeData = daoBankType.findById(id);
                bankTypeData.setDeletedStatus(BigDecimal.ONE);
                daoBankType.saveEditDeleteAppMstBankTypeEntity(bankTypeData);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
