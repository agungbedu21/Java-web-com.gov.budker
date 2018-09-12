package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstDocumentDao;
import com.gov.budker.model.AppMstDocument;
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
@RequestMapping("/app/doc")
public class AppMstDocumentController {

    @Autowired
    private AppMstDocumentDao daoDoc;

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
        session.setAttribute("menuName", "Master Dokumen");
        return "app/pages/app_mst_document";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstDocument.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("docId")) {
            BigDecimal id = new BigDecimal(params.get("docId").toString());
            AppMstDocument data = daoDoc.findById(id);
            if (data != null) {
                mvc.addObject("dataList", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_app_mst_document");
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
        AppMstDocument dataDoc = new AppMstDocument();
        try {
            if (isSave == 1) {
                dataDoc = new AppMstDocument();
                dataDoc.setDocName(params.get("docName").toString());
                dataDoc.setStatus(new BigDecimal(params.get("status").toString()));
                dataDoc.setDeletedStatus(BigDecimal.ZERO);
                daoDoc.saveEditDeleteAppMstDocumentEntity(dataDoc);
            } else if (isSave == 2) {
                dataDoc = new AppMstDocument();
                dataDoc = daoDoc.findById(new BigDecimal(params.get("docId").toString()));
                dataDoc.setDocName(params.get("docName").toString());
                dataDoc.setStatus(new BigDecimal(params.get("status").toString()));
                dataDoc.setDeletedStatus(BigDecimal.ZERO);
                daoDoc.saveEditDeleteAppMstDocumentEntity(dataDoc);
            } else {
                dataDoc = new AppMstDocument();
                BigDecimal id = new BigDecimal(params.get("docId").toString());
                dataDoc = daoDoc.findById(id);
                dataDoc.setDeletedStatus(BigDecimal.ONE);
                daoDoc.saveEditDeleteAppMstDocumentEntity(dataDoc);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
