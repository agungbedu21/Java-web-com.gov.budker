package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.dao.AppMstSectorDao;
import com.gov.budker.model.AppMstSector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cal/sector")
public class CalMstSectorController {

    @Autowired
    private AppMstSectorDao daoSectoral;

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
        session.setAttribute("menuName", "Manajemen Sektor");
        return "app/pages/cal_mst_sector";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstSector.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("sectorId")) {
            BigDecimal id = new BigDecimal(params.get("sectorId").toString());
            AppMstSector data = daoSectoral.findById(id);
            if (data != null) {
                mvc.addObject("dataList", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_cal_mst_sector");
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
        AppMstSector dataSectoral = new AppMstSector();
        try {
            if (isSave == 1) {
                dataSectoral = new AppMstSector();
                dataSectoral.setSectorName(params.get("sectorName").toString());
                dataSectoral.setStatus(new BigDecimal(params.get("status").toString()));
                dataSectoral.setDeletedStatus(BigDecimal.ZERO);
                daoSectoral.saveEditDeleteAppMstSectorEntity(dataSectoral);
            } else if (isSave == 2) {
                dataSectoral = new AppMstSector();
                dataSectoral = daoSectoral.findById(new BigDecimal(params.get("sectorId").toString()));
                dataSectoral.setSectorName(params.get("sectorName").toString());
                dataSectoral.setStatus(new BigDecimal(params.get("status").toString()));
                dataSectoral.setDeletedStatus(BigDecimal.ZERO);
                daoSectoral.saveEditDeleteAppMstSectorEntity(dataSectoral);
            } else {
                dataSectoral = new AppMstSector();
                BigDecimal id = new BigDecimal(params.get("sectorId").toString());
                dataSectoral = daoSectoral.findById(id);
                dataSectoral.setDeletedStatus(BigDecimal.ONE);
                daoSectoral.saveEditDeleteAppMstSectorEntity(dataSectoral);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
