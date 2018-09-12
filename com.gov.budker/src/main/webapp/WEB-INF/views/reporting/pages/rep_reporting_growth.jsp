<%--
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<t:genericpage>
    <jsp:attribute name="pagetitle">
        SiMoKA
    </jsp:attribute>
    <jsp:attribute name="breadcrumb">
        <%--BREAD CRUMB GOES HERE--%>
        <li>Laporan Pertumbuhan</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <script type="text/javascript">
            var otable;
            var number = 0;
            $(document).ready(function () {
            });

            function getDataTabel() {
//                var divId = $('#division').val();
                var year = $('#year').val();
                var tw = $('#triwulan').val();
                if (year === '' || tw === '') {
                    $('.reportContent').attr("hidden", true);
                    danger("Filter harus dipilih!");
                } else {
                    $('.reportContent').removeAttr("hidden");
                    var data = {
//                        "division": $('#division').val(),
                        "year": $('#year').val(),
                        "triwulan": $('#triwulan').val()
                    };
                    $.ajax({
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        'type': 'POST',
                        'url': APP_PATH + '/rep/reporting/growth/getDataTableReport',
                        'data': JSON.stringify(data),
                        'dataType': 'json',
                        success: function (hasil) {
                            var growthList = hasil.growth;
                            var costTotalC = 0;
                            var costTotalD = 0;
                            var costTotalE = 0;

                            //bus
                            if (growthList.length > 0) {
                                var htmlBus = '';
                                for (i = 0; i < growthList.length; i++) {
                                    htmlBus += "<tr>";
                                    htmlBus += "<td>" + (i + 1) + "</td>";
                                    htmlBus += "<td>" + growthList[i].bankName + "</td>";
                                    htmlBus += "<td>" + growthList[i].rbbGrowth + "</td>";
                                    htmlBus += "<td>";
                                    if (growthList[i].growthTarget == 1) {
                                        htmlBus += "Ya";
                                    } else {
                                        htmlBus += "Tidak";
                                    }
                                    htmlBus += "</td>";
                                    htmlBus += "<td>" + growthList[i].lastYearValue + "</td>";
                                    htmlBus += "<td>" + growthList[i].yoyCost + "</td>";
                                    htmlBus += "<td>" + growthList[i].monthCost + "</td>";
                                    htmlBus += "<td>" + growthList[i].growthValue + "</td>";
                                    htmlBus += "</tr>";
                                    //================================
                                    if (growthList[i].growthTarget == 1) {
                                        if (String(growthList[i].lastYearValue) !== 'null') {
                                            costTotalC = (costTotalC + parseFloat(growthList[i].lastYearValue));
                                        }
                                        costTotalD = (costTotalD + parseFloat(growthList[i].yoyCost));
                                        costTotalE = (costTotalE + parseFloat(growthList[i].monthCost));
                                    }
                                }

                                htmlBus += "<tr  style='background-color:#D2CCDE' ><td colspan='4' style='text-align: center'>Total Pembiayaan (Target tumbuh = Ya)</td>" +
                                    "<td><b>" + costTotalC + " </b></td>" +
                                    "<td><b>" + costTotalD + " </b></td>" +
                                    "<td><b>" + costTotalE + " </b></td>" +
                                    "<td></td></tr>";
                                $('#busContent').html(htmlBus);
                                $('.importReport').removeAttr("hidden");
                            } else {
                                $('.importReport').attr("hidden", true);
                                $('#busContent').html("<tr><td colspan='8' style='background-color:#D2CCDE; text-align: center'>Tidak Ada Data!</td></tr>");
                            }
                        }
                    });
                }
            }

            function downloadExcel() {
//                var divId = $('#division').val();
                var year = $('#year').val();
                var tw = $('#triwulan').val();
                window.location.href = APP_PATH + "/rep/reporting/growth/downloadExcel/0/" + year + "/" + tw;
            }

        </script>
        <style>
            #addButton {
                margin-top: -150px;
                margin-left: 40px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="bodycontent">
        <%--BODY CONTENT GOES HERE, WILL BE PRINTED BETWEEN DIV ID="CONTENT TAG--%>
        <div class="body-box">
            <div class="section-box clearfix">
                <div class="box-title">
                    Report Pertumbuhan
                </div>
                <div class="box-content">
                    <div class="row">
                        <div class="col-sm-9 paddingR0">
                            <div class="form-group" style="margin-bottom: 5px">
                                <div class="row">
                                    <%--<div class="col-md-3">--%>
                                        <%--<label>Filter Berdasarkan Divisi</label>--%>
                                        <%--<select class="form-control" id="division">--%>
                                            <%--<option value="0">Pilih Divisi</option>--%>
                                            <%--<c:forEach items="${listDivision}" var="data">--%>
                                                <%--<option value="${data.divisionId}">--%>
                                                        <%--${data.divisionName}--%>
                                                <%--</option>--%>
                                            <%--</c:forEach>--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                    <div class="col-md-3">
                                        <label>Filter Berdasarkan Tahun</label>
                                        <select class="form-control" id="year">
                                            <option value="">Pilih Tahun</option>
                                            <c:forEach items="${listYear}" var="data">
                                                        <option value="${data.year}" <c:if
                                                                test="${data.year==2018}">selected</c:if>>${data.year}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label>Filter Berdasarkan Triwulan</label>
                                        <select class="form-control" id="triwulan">
                                            <option value="">Pilih Triwulan</option>
                                            <c:forEach items="${listTriwulan}" var="data">
                                                        <option value="${data.twId}">${data.twName}</option>
                                                    </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <div style="margin-bottom: 5px">&nbsp</div>
                                            <button class="btn btn-primary" onclick="getDataTabel()">
                                                Tampilkan
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section-box clearfix reportContent" hidden>
                <div class="box-title hide">
                </div>
                <div class="box-content">

                    <table width="100%">
                        <tr>
                            <th colspan="6">
                                <h4>
                                    <div class="box-title">
                                        Pertumbuhan
                                    </div>
                                </h4>
                            </th>
                            <th style="text-align: center"></th>

                        </tr>
                    </table>
                    <br/>
                    <table id="table-bank" class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr style="background-color: #E0E0E3">
                            <th class="no-sort1">No</th>
                            <th>Nama Bank</th>
                            <th>Pertumbuhan RBB</th>
                            <th>Target Tumbuh</th>
                            <th>Pembiayaan Desember Y-1</th>
                            <th>Pembiayaan Bulan Y-1</th>
                            <th>Pembiayaan Bulan Y</th>
                            <th>Pertumbuhan Yoy</th>
                        </tr>
                        </thead>
                        <tbody id="busContent">

                        </tbody>
                    </table>
                </div>
            </div>
            <hr style="border-width: thick" class="reportContent" hidden/>

            <div hidden class="importReport"
                 style="padding: 20px 30px; background: rgb(0, 166, 90); font-size: 16px; font-weight: 600;color: rgb(249, 249, 249);">
                <span style="margin-right: 10px;">Silakan tekan tombol untuk mendownload laporan dalam bentuk file excel!</span>
                <button class="btn btn-default btn-sm" onclick="downloadExcel()"
                        style="margin-top: -5px; border: 0px; box-shadow: none; color: rgb(0, 166, 90); font-weight: 600; background: rgb(255, 255, 255);">
                    Download
                </button>
            </div>
        </div>
    </jsp:attribute>
</t:genericpage>
<script src="${pageContext.request.contextPath}/resources/component/common.js"></script>


