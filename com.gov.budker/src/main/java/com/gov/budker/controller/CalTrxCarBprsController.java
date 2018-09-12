package com.gov.budker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.budker.common.DataTableDao;
import com.gov.budker.common.DataTableRequest;
import com.gov.budker.common.DataTableResult;
import com.gov.budker.common.DateTimeGenerator;
import com.gov.budker.dao.*;
import com.gov.budker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/cal/bprs")
public class CalTrxCarBprsController {

    @Autowired
    private CalTrxCarBprsDao daoCalTrxCarBprs;

    @Autowired
    private CalTrxBprsDetailDao bprsDetailDao;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private CalMstDeltaCarDao daoDeltaCar;

    @Autowired
    private CalMstDeltaCarBprsDao daoDeltaCarBprs;


    @Autowired
    private AppMstBankDao daoBank;

    @Autowired
    private MessageSource messageSource;

    private DateTimeGenerator dtg;

    /*
     * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployee(HttpSession session,
                               HttpServletResponse response, HttpServletRequest request) {
//        try {
//            session.getAttribute("menuList");
//        } catch (Exception e) {
//            return "redirect:/login";
//        }
        session.setAttribute("menuName", "CAR BPRS");
        return "cal/pages/cal_car_bprs";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable,
                                           HttpSession session) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, CalTrxCarBprs.class);
        if ("1".equals(session.getAttribute("roleId").toString())) {
            return result;
        }
        List<CalTrxCarBprs> listData = result.getData();
        List<CalTrxCarBprs> listDatanew = new ArrayList<>();
        BigDecimal id = new BigDecimal(session.getAttribute("divisionId").toString());

        for (CalTrxCarBprs prk : listData) {
            if (id.equals(prk.getDivisionId())) {
                listDatanew.add(prk);
            }
        }
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        for (int i = 0; i < listDivId.size(); i++) {
            for (CalTrxCarBprs prk : listData) {
                if (listDivId.get(i).toString().equals(prk.getDivisionId().toString())) {
                    listDatanew.add(prk);
                }
            }
        }

        result.setData(listDatanew);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();

        List<AppMstDivision> listDivision = new ArrayList<>();
        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");

        if (session.getAttribute("roleId").toString().equals("1")) {
            listDivision = daoDiv.findAllAppMstDivisionEntity();
            mvc.addObject("listDivision", listDivision);
        } else {
            //get division list with access security
            listDivision.add(daoDiv.findById(divId));
            //getListAccess available division
            for (int i = 0; i < listDivId.size(); i++) {
                listDivision.add(daoDiv.findById(new BigDecimal(listDivId.get(i).toString())));
            }
            mvc.addObject("listDivision", listDivision);
        }
        if (params != null && params.containsKey("calCarBprsId")) {
            BigDecimal id = new BigDecimal(params.get("calCarBprsId").toString());
            CalTrxCarBprs data = daoCalTrxCarBprs.findById(id);
            Boolean isAllowed = Boolean.FALSE;
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxCarBprs", data);
                    isAllowed = Boolean.TRUE;
                } else {
                    if (data.getDivisionId().equals(divId)) {
                        isAllowed = Boolean.TRUE;
                    } else {
                        for (int i = 0; i < listDivId.size(); i++) {
                            if (listDivId.get(i).equals(data.getDivisionId())) {
                                isAllowed = Boolean.TRUE;
                                break;
                            }
                        }
                    }
                    if (isAllowed) {
                        mvc.addObject("dataCalTrxCarBprs", data);
                    } else {
                        return mvc;
                    }
                }
            }
        }

        dtg = new DateTimeGenerator();
        mvc.addObject("listTriwulan", dtg.getTriwulan());
        mvc.addObject("listYears", dtg.getYear());


        mvc.setViewName("cal/partialPages/modal_cal_car_bprs");
        return mvc;
    }

    @RequestMapping(value = {"/modalview"}, method = RequestMethod.POST)
    public ModelAndView getModalView(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        Boolean isAllowed = Boolean.FALSE;

        if (params != null && params.containsKey("calCarBprsId")) {
            BigDecimal id = new BigDecimal(params.get("calCarBprsId").toString());
            CalTrxCarBprs data = daoCalTrxCarBprs.findById(id);

            CalMstCarBprs carMinimum = new CalMstCarBprs();
            carMinimum = daoDeltaCarBprs.findByActiveByYear(data.getYear());
            mvc.addObject("carMin", carMinimum.getCarBprsMin());
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxCarBprs", data);
                    isAllowed = Boolean.TRUE;
                } else {
                    if (data.getDivisionId().equals(divId)) {
                        isAllowed = Boolean.TRUE;
                    } else {
                        for (int i = 0; i < listDivId.size(); i++) {
                            if (listDivId.get(i).equals(data.getDivisionId())) {
                                isAllowed = Boolean.TRUE;
                                break;
                            }
                        }
                    }
                    if (isAllowed) {
                        mvc.addObject("dataCalTrxCarBprs", data);
                    } else {
                        return mvc;
                    }
                }
            }
            List<AppMstBank> listBank = new ArrayList<>();
            listBank = daoBank.findAppMstBankByDivisionId(data.getDivisionId());
            mvc.addObject("bankList", listBank);

            List<CalTrxBprsDetail> listDetail = bprsDetailDao.findCalTrxBprsDetailByBprsId(id);
            mvc.addObject("listDetail", listDetail);
        } else {
            return mvc;
        }
        dtg = new DateTimeGenerator();
        mvc.addObject("listTriwulan", dtg.getTriwulan());

        mvc.setViewName("/cal/partialPages/modal_cal_car_bprs_view");
        return mvc;
    }


    /*
     * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params, HttpSession session,
                                              @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        CalTrxCarBprs CalTrxCarBprs = new CalTrxCarBprs();
        Boolean status = Boolean.FALSE;
        String message = "";
        try {
            if (isSave == 1) {
                CalTrxCarBprs = new CalTrxCarBprs();
                CalTrxCarBprs.setCalCarBprsId(null);
                CalTrxCarBprs.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                CalTrxCarBprs.setYear(params.get("year").toString());
                CalTrxCarBprs.setTriwulan(new BigDecimal(params.get("triwulan").toString()));
                CalTrxCarBprs.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                CalTrxCarBprs.setUserCreated(new BigDecimal(session.getAttribute("userId").toString()));
                CalTrxCarBprs.setDeletedStatus(BigDecimal.ZERO);
                CalTrxCarBprs.setDateCreated(new Date());
                if (daoCalTrxCarBprs.cekAvailableData(CalTrxCarBprs.getDivisionId(), CalTrxCarBprs.getYear(),
                        CalTrxCarBprs.getTriwulan())) {
                    daoCalTrxCarBprs.saveCalTrxCarBprsEntity(CalTrxCarBprs);
                    status = Boolean.TRUE;
                } else {
                    message = "Data Telah Digunakan";
                }
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal(params.get("calCarBprsId").toString());
                CalTrxCarBprs = new CalTrxCarBprs();
                CalTrxCarBprs = daoCalTrxCarBprs.findById(id);
                CalTrxCarBprs.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                CalTrxCarBprs.setYear(params.get("year").toString());
                CalTrxCarBprs.setTriwulan(new BigDecimal(params.get("triwulan").toString()));
                CalTrxCarBprs.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                CalTrxCarBprs.setUserUpdated(new BigDecimal(session.getAttribute("userId").toString()));
                CalTrxCarBprs.setDeletedStatus(BigDecimal.ZERO);
                CalTrxCarBprs.setDateUpdated(new Date());
                if (daoCalTrxCarBprs.cekAvailableDataEdit(CalTrxCarBprs.getDivisionId(), CalTrxCarBprs.getYear(),
                        CalTrxCarBprs.getTriwulan(), CalTrxCarBprs.getCalCarBprsId())) {
                    daoCalTrxCarBprs.saveCalTrxCarBprsEntity(CalTrxCarBprs);
                    status = Boolean.TRUE;
                } else {
                    message = "Data Telah Digunakan";
                }
            } else {
                CalTrxCarBprs = new CalTrxCarBprs();
                BigDecimal id = new BigDecimal(params.get("calCarBprsId").toString());
                CalTrxCarBprs = daoCalTrxCarBprs.findById(id);
                CalTrxCarBprs.setDeletedStatus(BigDecimal.ONE);
                CalTrxCarBprs.setDateDeleted(new Date());
                CalTrxCarBprs.setUserDeleted(new BigDecimal(session.getAttribute("userId").toString()));
                daoCalTrxCarBprs.saveCalTrxCarBprsEntity(CalTrxCarBprs);
            }
            result.put("status", status);
            result.put("message", message);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/detail/saveOrUpdate"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> savesectoralDetail(@RequestBody Map<String, Object> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        CalTrxBprsDetail calTrxCarBprsDetail = new CalTrxBprsDetail();
        List<CalTrxBprsDetail> listDetail = new ArrayList<>();
        try {
            BigDecimal calBprsId = new BigDecimal(params.get("calCarBprsId").toString());
            BigDecimal minValue = new BigDecimal(params.get("minValue").toString());
            List carValue = (List) params.get("carValue");
            List supStatus = (List) params.get("supervisionStatus");
            List bankId = (List) params.get("bankId");
            List isBalance = (List) params.get("isBalance");
            List detailId = new ArrayList();
            if (params.containsKey("detailId")) {
                detailId = (List) params.get("detailId");
            }
            for (int i = 0; i < bankId.size(); i++) {
                calTrxCarBprsDetail = new CalTrxBprsDetail();
                if (detailId.size() > 0) {
                    calTrxCarBprsDetail.setDetailId(new BigDecimal(detailId.get(i).toString()));
                }
                calTrxCarBprsDetail.setBankId(new BigDecimal(bankId.get(i).toString()));
                calTrxCarBprsDetail.setCarBprsId(calBprsId);
                calTrxCarBprsDetail.setCarValue(new BigDecimal(carValue.get(i).toString()));
                calTrxCarBprsDetail.setIsBalance(new BigDecimal(isBalance.get(i).toString()));
                calTrxCarBprsDetail.setSupervisionStatus(new BigDecimal(supStatus.get(i).toString()));
                listDetail.add(calTrxCarBprsDetail);
            }
            bprsDetailDao.saveListFinishingDetail(listDetail);
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

//    @RequestMapping(value = {"/getbyid"}, method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> editAppMstCity(
//            @RequestBody Map<String, Object> params, ModelMap model) {
//        BigDecimal id = new BigDecimal(params.get("employeeId").toString());
//        Map<String, Object> dataResult = new HashMap<>();
//        CalTrxCarBprs dataBud = new CalTrxCarBprs();
//        dataBud = daoCalTrxCarBprs.findById(id);
//        dataResult.put("dataBud", dataBud);
//        return dataResult;
//    }

    @RequestMapping(value = {"/delete/{CalTrxCarBprsId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBudget(HttpSession session, @PathVariable(value = "CalTrxCarBprsId")
            BigDecimal CalTrxCarBprsId) {
        Map<String, Object> dataResult = new HashMap<>();

//        Boolean isAllowed = Boolean.FALSE;
//        if (session.getAttribute("roleId").toString().equals("1")) {
//            isAllowed = Boolean.TRUE;
//        } else {
//            BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
//            List listDivId = (List) session.getAttribute("listDivisionAccess");
//            CalTrxCarBprs bud = daoCalTrxCarBprs.findById(CalTrxCarBprsId);
//            if (divId.equals(bud.getDivisionId())) {
//                isAllowed = Boolean.TRUE;
//            } else {
//                for (int i = 0; i < listDivId.size(); i++) {
//                    if (listDivId.get(i).equals(bud.getDivisionId())) {
//                        isAllowed = Boolean.TRUE;
//                        break;
//                    }
//                }
//            }
//        }
        try {
            daoCalTrxCarBprs.deleteCalTrxCarBprsEntity(CalTrxCarBprsId,
                    new BigDecimal(session.getAttribute("empId").toString()));
            dataResult.put("status", Boolean.TRUE);
        } catch (Exception ex){
            dataResult.put("status", Boolean.FALSE);
            dataResult.put("message", "Gagal Dihapus!");
        }
        return dataResult;
    }
}
