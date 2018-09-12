package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstBankDao;
import com.gov.budker.model.AppMstBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/app/bank")
public class AppMstBankController {

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
        session.setAttribute("menuName", "Daftar Bank");
        return "/app/pages/app_mst_bank";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstBank.class);
        return result;
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


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params,
                                              @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstBank dataSectoral = new AppMstBank();
        try {
            if (isSave == 1) {
                dataSectoral = new AppMstBank();
                dataSectoral.setBankName(params.get("bankName").toString());
                dataSectoral.setStatus(new BigDecimal(params.get("status").toString()));
                dataSectoral.setDeletedStatus(BigDecimal.ZERO);
                daoBank.saveEditDeleteAppMstBankEntity(dataSectoral);
            } else if (isSave == 2) {
                dataSectoral = new AppMstBank();
                dataSectoral = daoBank.findById(new BigDecimal(params.get("bankId").toString()));
                dataSectoral.setBankName(params.get("bankName").toString());
                dataSectoral.setStatus(new BigDecimal(params.get("status").toString()));
                dataSectoral.setDeletedStatus(BigDecimal.ZERO);
                daoBank.saveEditDeleteAppMstBankEntity(dataSectoral);
            } else {
                dataSectoral = new AppMstBank();
                BigDecimal id = new BigDecimal(params.get("bankId").toString());
                dataSectoral = daoBank.findById(id);
                dataSectoral.setDeletedStatus(BigDecimal.ONE);
                daoBank.saveEditDeleteAppMstBankEntity(dataSectoral);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
