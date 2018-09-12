package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.*;
import com.gov.budker.dao.AppMstDivisionDao;
import com.gov.budker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/app/div")
public class AppMstDivisionController {

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private AppMstDirectorateDao daoDir;

    @Autowired
    private AppMstBankTypeDao daoBankType;

    @Autowired
    private AppMstDivisionBankDao divBankDao;

    @Autowired
    private AppMstRoleDivisionDao daoRoleDiv;

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
        mvc.addObject("menuName", "Manajemen Divisi");
        session.setAttribute("menuName", "Manajemen Divisi");
        return "/app/pages/app_mst_division";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstDivision.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        List<AppMstDivision> listMenuParent = new ArrayList<>();
        listMenuParent = daoDiv.findAllAppMstDivisionEntity();
        if (params != null && params.containsKey("divisionId")) {
            BigDecimal id = new BigDecimal(params.get("divisionId").toString());
            List<AppMstDivision> listDivision = new ArrayList<>();
            listDivision = daoDiv.findAppMstDivisionEntity(id);
            mvc.addObject("listDivision", listDivision);

            AppMstDivision data = daoDiv.findById(id);
            if (data != null) {
                mvc.addObject("dataDiv", data);
            }
            List<AppMstDivisionBank> listBankDiv = new ArrayList<>();
            listBankDiv = divBankDao.findAllAppMstDivisionBankEntity(id);
            mvc.addObject("listBankDiv", listBankDiv);

            List<AppMstRoleDivision> listRoleDiv = new ArrayList<>();
            listRoleDiv = daoRoleDiv.findAppMstRoleDivisionEntity(id);
            mvc.addObject("listRoleDiv", listRoleDiv);
        } else {
            List<AppMstDivision> listDivision = new ArrayList<>();
            listDivision = daoDiv.findAllAppMstDivisionEntity();
            mvc.addObject("listDivision", listDivision);
        }
        List<AppMstBankType> listBankType = new ArrayList<>();
        listBankType = daoBankType.findAllAppMstBankTypeEntity();
        mvc.addObject("listBankType", listBankType);

        List<AppMstDirectorate> listDirectorate = new ArrayList<>();
        listDirectorate = daoDir.findAllAppMstDirectorateEntity();
        mvc.addObject("listDirectorate", listDirectorate);
        mvc.setViewName("/app/partialPages/modal_app_mst_div");
        return mvc;
    }

    @RequestMapping(value = {"/getbydirid/{dirId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDivisionByDirectorateId(HttpSession session, HttpServletResponse response,
                                                          HttpServletRequest request,
                                                          @PathVariable(value = "dirId") BigDecimal dirId) {
        Map<String, Object> dataResult = new HashMap<>();
        List<AppMstDivision> listDiv = new ArrayList<>();
        listDiv = daoDiv.getDivisionByDirectorateId(dirId);
        dataResult.put("data", listDiv);
        return dataResult;
    }

    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params,
                                              @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstDivision dataDiv = new AppMstDivision();

        List bankTypeId = (List) params.get("bankTypeId");
        List divGetId = (List) params.get("divisionGet");
        AppMstDivisionBank divBank = new AppMstDivisionBank();
        AppMstRoleDivision divRole = new AppMstRoleDivision();
        List<AppMstRoleDivision> listDivRole = new ArrayList<>();
        List<AppMstDivisionBank> listDivBank = new ArrayList<>();
        try {
            if (isSave == 1) {
                dataDiv = new AppMstDivision();
                dataDiv.setDivisionName(params.get("divisionName").toString());
                dataDiv.setDirectorateId(new BigDecimal(params.get("directorateId").toString()));
                dataDiv.setStatus(new BigDecimal(params.get("status").toString()));
                dataDiv.setDeletedStatus(BigDecimal.ZERO);
                dataDiv.setDateCreated(new Date());
                BigDecimal divId = daoDiv.saveAppMstDivisionEntity(dataDiv);
                for (int i = 0; i < bankTypeId.size(); i++) {
                    divBank = new AppMstDivisionBank();
                    divBank.setBankTypeId(new BigDecimal(bankTypeId.get(i).toString()));
                    divBank.setDeletedStatus(BigDecimal.ZERO);
                    divBank.setDivisionId(divId);
                    divBank.setStatus(BigDecimal.ONE);
                    listDivBank.add(divBank);
                }
                divBankDao.saveListDivisionBank(listDivBank);
                for (int i = 0; i < divGetId.size(); i++) {
                    divRole = new AppMstRoleDivision();
                    divRole.setDivisionId(divId);
                    divRole.setDivisionGet(new BigDecimal(divGetId.get(i).toString()));
                    listDivRole.add(divRole);
                }
                if (divGetId.size() > 0) {
                    daoRoleDiv.saveEditDeleteAppMstRoleEntity(listDivRole);
                }
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal(params.get("divisionId").toString());
                dataDiv = daoDiv.findById(id);
                dataDiv.setDivisionName(params.get("divisionName").toString());
                dataDiv.setDirectorateId(new BigDecimal(params.get("directorateId").toString()));
                dataDiv.setStatus(new BigDecimal(params.get("status").toString()));
                dataDiv.setDeletedStatus(BigDecimal.ZERO);
                dataDiv.setDateUpdated(new Date());
                daoDiv.saveAppMstDivisionEntity(dataDiv);
                for (int i = 0; i < bankTypeId.size(); i++) {
                    divBank = new AppMstDivisionBank();
                    divBank.setBankTypeId(new BigDecimal(bankTypeId.get(i).toString()));
                    divBank.setDeletedStatus(BigDecimal.ZERO);
                    divBank.setDivisionId(id);
                    divBank.setStatus(BigDecimal.ONE);
                    listDivBank.add(divBank);
                }
                if (bankTypeId.size() > 0) {
                    divBankDao.saveListDivisionBank(listDivBank);
                }

                for (int i = 0; i < divGetId.size(); i++) {
                    divRole = new AppMstRoleDivision();
                    divRole.setDivisionId(id);
                    divRole.setDivisionGet(new BigDecimal(divGetId.get(i).toString()));
                    listDivRole.add(divRole);
                }
                if (divGetId.size() > 0) {
                    daoRoleDiv.saveEditDeleteAppMstRoleEntity(listDivRole);
                } else {
                    daoRoleDiv.deleteRoleByDivId(id);
                }

            } else {
                dataDiv = new AppMstDivision();
                BigDecimal id = new BigDecimal(params.get("divisionId").toString());
                dataDiv = daoDiv.findById(id);
                dataDiv.setDeletedStatus(BigDecimal.ONE);
                dataDiv.setDateDeleted(new Date());
                daoDiv.saveAppMstDivisionEntity(dataDiv);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
