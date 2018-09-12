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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/cal/sectoral")
public class CalTrxSectoralController {

    @Autowired
    private CalTrxSectoralDao daoCalTrxSectoral;

    @Autowired
    private CalTrxSectoralDetailDao sectoralDetailDao;

    @Autowired
    private AppMstDivisionDao daoDiv;

    @Autowired
    private AppMstSectorDao daoSec;

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
        session.setAttribute("menuName", "Sektoral");
        return "/cal/pages/cal_sectoral";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable, HttpSession session) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, CalTrxSectoral.class);
        if ("1".equals(session.getAttribute("roleId").toString())) {
            return result;
        }
        List<CalTrxSectoral> listData = result.getData();
        List<CalTrxSectoral> listDatanew = new ArrayList<>();
        BigDecimal id = new BigDecimal(session.getAttribute("divisionId").toString());
        for (CalTrxSectoral prk : listData) {
            if (id.equals(prk.getDivisionId())) {
                listDatanew.add(prk);
            }
        }
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        for (int i = 0; i < listDivId.size(); i++) {
            for (CalTrxSectoral prk : listData) {
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
        BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
        List listDivId = (List) session.getAttribute("listDivisionAccess");
        List<AppMstDivision> listDivision = new ArrayList<>();
        Boolean isAllowed = Boolean.FALSE;
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
        if (params != null && params.containsKey("calSectoralId")) {
            BigDecimal id = new BigDecimal(params.get("calSectoralId").toString());
            CalTrxSectoral data = daoCalTrxSectoral.findById(id);
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxSectoral", data);
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
                        mvc.addObject("dataCalTrxSectoral", data);
                    } else {
                        return mvc;
                    }
                }
            }
        }

        dtg = new DateTimeGenerator();
        mvc.addObject("listTriwulan", dtg.getTriwulan());
        mvc.addObject("listYears", dtg.getYear());
        List<AppMstSector> listFinType = new ArrayList<>();
        listFinType = daoSec.findAllAppMstSectorEntity();
        mvc.addObject("listSectoralType", listFinType);

        mvc.setViewName("/cal/partialPages/modal_cal_sectoral");
        return mvc;
    }

    @RequestMapping(value = {"/modalview"}, method = RequestMethod.POST)
    public ModelAndView getModalView(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        if (params != null && params.containsKey("calSectoralId")) {
            BigDecimal id = new BigDecimal(params.get("calSectoralId").toString());
            CalTrxSectoral data = daoCalTrxSectoral.findById(id);
            Boolean isAllowed = Boolean.FALSE;
            BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
            List listDivId = (List) session.getAttribute("listDivisionAccess");
            List<AppMstDivision> listDivision = new ArrayList<>();
            if (data != null) {
                if (session.getAttribute("roleId").toString().equals("1")) {
                    mvc.addObject("dataCalTrxSectoral", data);
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
                        mvc.addObject("dataCalTrxSectoral", data);
                    } else {
                        return mvc;
                    }
                }
            }
            List<AppMstBank> listBank = new ArrayList<>();
            listBank = daoBank.findAppMstBankByDivisionId(data.getDivisionId());
            mvc.addObject("bankList", listBank);

            List<CalTrxSectoralDetail> listDetail = sectoralDetailDao.findCalTrxSectoralDetailBysectoralId(id);
            mvc.addObject("listDetail", listDetail);
        } else {
            return mvc;
        }
        dtg = new DateTimeGenerator();
        mvc.addObject("listTriwulan", dtg.getTriwulan());
        mvc.setViewName("/cal/partialPages/modal_cal_sectoral_view");
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
        CalTrxSectoral CalTrxSectoral = new CalTrxSectoral();
        Boolean status = Boolean.FALSE;
        String message = "";
        try {
            if (isSave == 1) {
                CalTrxSectoral = new CalTrxSectoral();
                CalTrxSectoral.setCalSectoralId(null);
                CalTrxSectoral.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                CalTrxSectoral.setYear(params.get("year").toString());
                CalTrxSectoral.setTriwulan(new BigDecimal(params.get("triwulan").toString()));
                CalTrxSectoral.setSectoralId(new BigDecimal(params.get("sectoralId").toString()));
                CalTrxSectoral.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                CalTrxSectoral.setUserCreated(new BigDecimal(session.getAttribute("userId").toString()));
                CalTrxSectoral.setDeletedStatus(BigDecimal.ZERO);
                CalTrxSectoral.setDateCreated(new Date());

                if (daoCalTrxSectoral.cekAvailableData(CalTrxSectoral.getDivisionId(), CalTrxSectoral.getYear(),
                        CalTrxSectoral.getTriwulan())) {
                    daoCalTrxSectoral.saveCalTrxSectoralEntity(CalTrxSectoral);
                    status = Boolean.TRUE;
                } else {
                    message = "Data Telah Digunakan";
                }
            } else if (isSave == 2) {
                BigDecimal id = new BigDecimal(params.get("sectoralId").toString());
                CalTrxSectoral = new CalTrxSectoral();
                CalTrxSectoral = daoCalTrxSectoral.findById(id);
                CalTrxSectoral.setDivisionId(new BigDecimal(params.get("divisionId").toString()));
                CalTrxSectoral.setYear(params.get("year").toString());
                CalTrxSectoral.setTriwulan(new BigDecimal(params.get("triwulan").toString()));
                CalTrxSectoral.setSectoralId(new BigDecimal(params.get("sectoralId").toString()));
                CalTrxSectoral.setIkuTarget(new BigDecimal(params.get("ikuTarget").toString()));
                CalTrxSectoral.setUserUpdated(new BigDecimal(session.getAttribute("userId").toString()));
                CalTrxSectoral.setDeletedStatus(BigDecimal.ZERO);
                CalTrxSectoral.setDateUpdated(new Date());

                if (daoCalTrxSectoral.cekAvailableDataEdit(CalTrxSectoral.getDivisionId(), CalTrxSectoral.getYear(),
                        CalTrxSectoral.getTriwulan(), CalTrxSectoral.getCalSectoralId())) {
                    daoCalTrxSectoral.saveCalTrxSectoralEntity(CalTrxSectoral);
                    status = Boolean.TRUE;
                } else {
                    message = "Data Telah Digunakan";
                }
            } else {
                CalTrxSectoral = new CalTrxSectoral();
                BigDecimal id = new BigDecimal(params.get("sectoralId").toString());
                CalTrxSectoral = daoCalTrxSectoral.findById(id);
                CalTrxSectoral.setDeletedStatus(BigDecimal.ONE);
                CalTrxSectoral.setDateDeleted(new Date());
                CalTrxSectoral.setUserDeleted(new BigDecimal(session.getAttribute("userId").toString()));
                daoCalTrxSectoral.saveCalTrxSectoralEntity(CalTrxSectoral);
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
        CalTrxSectoralDetail calTrxSectoralDetail = new CalTrxSectoralDetail();
        List<CalTrxSectoralDetail> listDetail = new ArrayList<>();
        try {
            BigDecimal sectoralId = new BigDecimal(params.get("calSectoralId").toString());
            List targets = (List) params.get("target");
            List realization = (List) params.get("realization");
            List bankId = (List) params.get("bankId");
            List detailId = new ArrayList();
            if (params.containsKey("detailId")) {
                detailId = (List) params.get("detailId");
            }
            for (int i = 0; i < bankId.size(); i++) {
                calTrxSectoralDetail = new CalTrxSectoralDetail();
                if (detailId.size() > 0 && i < detailId.size()) {
                    calTrxSectoralDetail.setDetailId(new BigDecimal(detailId.get(i).toString()));
                }
                calTrxSectoralDetail.setBankId(new BigDecimal(bankId.get(i).toString()));
                calTrxSectoralDetail.setSectoralId(sectoralId);
                calTrxSectoralDetail.setTarget(targets.get(i).toString());
                calTrxSectoralDetail.setRealization(realization.get(i).toString());
                listDetail.add(calTrxSectoralDetail);
            }
            sectoralDetailDao.saveListFinishingDetail(listDetail);
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/getbyid"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editAppMstCity(
            @RequestBody Map<String, Object> params, ModelMap model) {
        BigDecimal id = new BigDecimal(params.get("employeeId").toString());
        Map<String, Object> dataResult = new HashMap<>();
        CalTrxSectoral dataBud = new CalTrxSectoral();
        dataBud = daoCalTrxSectoral.findById(id);
        dataResult.put("dataBud", dataBud);
        return dataResult;
    }

    @RequestMapping(value = {"/delete/{CalTrxSectoralId}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBudget(HttpSession session, @PathVariable(value = "CalTrxSectoralId")
            BigDecimal CalTrxSectoralId) {
        Map<String, Object> dataResult = new HashMap<>();
//        Boolean isAllowed = Boolean.FALSE;
//        if (session.getAttribute("roleId").toString().equals("1")) {
//            isAllowed = Boolean.TRUE;
//        } else {
//            BigDecimal divId = new BigDecimal(session.getAttribute("divisionId").toString());
//            List listDivId = (List) session.getAttribute("listDivisionAccess");
//            CalTrxSectoral bud = daoCalTrxSectoral.findById(CalTrxSectoralId);
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
            daoCalTrxSectoral.deleteCalTrxSectoralEntity(CalTrxSectoralId,
                    new BigDecimal(session.getAttribute("empId").toString()));
            dataResult.put("status", Boolean.TRUE);
        } catch (Exception ex) {
            dataResult.put("status", Boolean.FALSE);
            dataResult.put("message", "Data Gagal Dihapus!");
        }


        return dataResult;
    }
}
