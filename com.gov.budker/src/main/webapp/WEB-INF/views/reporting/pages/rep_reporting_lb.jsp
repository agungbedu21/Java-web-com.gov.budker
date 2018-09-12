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
        <li>Laporan LB</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <script type="text/javascript">
            var otable;
            var number = 0;
            $(document).ready(function () {
            });

            function getDataTabel() {
                var monthId = $('#listMonth').val();
                var year = $('#year').val();
                var tw = Math.ceil(parseFloat(monthId) / 3);
                if (year === '' || monthId === '') {
                    $('.reportContent').attr("hidden", true);
                    danger("Filter harus dipilih!");
                } else {
                    $('.reportContent').removeAttr("hidden");
                    var data = {
                        "month": $('#monthId').val(),
                        "year": $('#year').val()
                    };
                    $.ajax({
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            'type': 'POST',
                            'url': APP_PATH + '/rep/reporting/lb/getDataTableReport',
                            'data': JSON.stringify(data),
                            'dataType': 'json',
                            success: function (hasil) {
                                var budgeta = hasil.budget;
                                var budget = budgeta;
                                //budget
                                if (budget.length > 0) {
                                    var htmlBus = '';
                                    for (i = 0; i < tw; i++) {
                                        var costTotalA = 0;
                                        var costTotalB = 0;
                                        var j = i + 4;
                                        htmlBus += "<tr>";
                                        htmlBus += "<td>" + (i + 1) + "</td>";
                                        htmlBus += "<td>" + budget[i].fieldA + "</td>";
                                        htmlBus += "<td>" + budget[i].fieldB + "</td>";
                                        htmlBus += "<td>" + budget[j].fieldA + "</td>";
                                        htmlBus += "<td>" + budget[j].fieldB + "</td>";
                                        costTotalA = parseFloat(budget[i].fieldA);
                                        costTotalB = parseFloat(budget[j].fieldA);
                                        htmlBus += "<td>" + (parseFloat(costTotalB / costTotalA) * 100).toFixed(10) + "</td>";
                                        htmlBus += "</tr>";
                                    }

                                    $('#budgetContent').html(htmlBus);
                                    $('.importReport').removeAttr("hidden");
                                } else {
                                    $('.importReport').attr("hidden", true);
                                    $('#budgetContent').html("<tr><td colspan='8' style='background-color:#D2CCDE; text-align: center'>Tidak Ada Data!</td></tr>");
                                }
                                var proker = hasil.proker;
                                if (proker.length > 0) {
                                    var htmlBus = '';
                                    for (i = 0; i < proker.length; i++) {
                                        var costTotalA = 0;
                                        var costTotalB = 0;
                                        htmlBus += "<tr>";
                                        htmlBus += "<td>" + (i + 1) + "</td>";
                                        htmlBus += "<td>" + proker[i].ikuCode + "</td>";
                                        htmlBus += "<td>" + proker[i].prokerName + "</td>";
                                        htmlBus += "<td>" + proker[i].prokerBudget + "</td>";
                                        if (String(proker[i].realization) === 'null') {
                                            htmlBus += "<td>0</td>";
                                            costTotalA = parseFloat(0);
                                        } else {
                                            costTotalA = parseFloat(proker[i].realization);
                                            htmlBus += "<td>" + proker[i].realization + "</td>";
                                        }

                                        if (String(proker[i].realization) === 'null') {
                                            htmlBus += "<td>0</td>";
                                            costTotalB = parseFloat(1);
                                        } else {
                                            costTotalB = parseFloat(proker[i].prokerBudget);
                                            htmlBus += "<td>" + proker[i].subsequence + "</td>";
                                        }
                                        if (isNaN(parseFloat(costTotalA / costTotalB))) {
                                            htmlBus += "<td>-</td>";
                                        } else {
                                            htmlBus += "<td>" + parseFloat(costTotalA / costTotalB) + "</td>";
                                        }
                                        htmlBus += "</tr>";
                                    }
                                    $('#prokerContent').html(htmlBus);
                                    $('.importReport').removeAttr("hidden");
                                } else {
                                    $('.importReport').attr("hidden", true);
                                    $('#prokerContent').html("<tr><td colspan='8' style='background-color:#D2CCDE; text-align: center'>Tidak Ada Data!</td></tr>");
                                }


                                var prokerIku = hasil.prokerIku;
                                if (prokerIku.length > 0) {
                                    var htmlBus = '';
                                    var len = (parseFloat(prokerIku.length) / 4);
                                    len = Math.ceil(len);
                                    var i = 0;
                                    for (j = 0; j < len; j++) {
                                        var target = 0;
                                        var realization = 0;
                                        var achievement = 0;
                                        for (n = 0; n < tw; n++) {
                                            target = target + parseFloat(prokerIku[n + i].target);
                                            realization = realization + parseFloat(prokerIku[n + i].realization);
                                            achievement = achievement + parseFloat(prokerIku[n + i].achievement);
                                        }
                                        htmlBus += "<tr>";
                                        htmlBus += "<td>" + (j + 1) + "</td>";
                                        htmlBus += "<td>" + prokerIku[i].ikuCode + "</td>";
                                        htmlBus += "<td>" + prokerIku[i].prokerName + "</td>";

                                        if (prokerIku[i].picName === null || prokerIku[i].picName === "") {
                                            htmlBus += "<td>-</td>";
                                        } else {
                                            htmlBus += "<td>" + prokerIku[i].picName + "</td>";
                                        }
                                        if (isNaN(target)) {
                                            htmlBus += "<td>-</td>";
                                        } else {
                                            htmlBus += "<td>" + target + "</td>";
                                        }


                                        if (prokerIku[i].periode === null || prokerIku[i].periode === "") {
                                            htmlBus += "<td>-</td>";
                                        } else {
                                            htmlBus += "<td>" + prokerIku[i].periode + "</td>";
                                        }


                                        if (isNaN(realization)) {
                                            htmlBus += "<td>-</td>";
                                        } else {
                                            htmlBus += "<td>" + realization + "</td>";
                                        }

                                        if (isNaN(achievement)) {
                                            htmlBus += "<td>-</td>";
                                        } else {
                                            htmlBus += "<td>" + achievement + "</td>";
                                        }

                                        htmlBus += "<td>" + prokerIku[i].explanation + "</td>";
                                        htmlBus += "</tr>";
                                        i = i + 4;
                                    }
                                    $('#prokerIkuContent').html(htmlBus);
                                    $('.importReport').removeAttr("hidden");
                                } else {
                                    $('.importReport').attr("hidden", true);
                                    $('#prokerIkuContent').html("<tr><td colspan='9' style='background-color:#D2CCDE; text-align: center'>Tidak Ada Data!</td></tr>");
                                }

                                var prokerNonIku = hasil.prokerNonIku;
                                if (prokerNonIku.length > 0) {
                                    var htmlBus = '';
                                    var len = (parseFloat(prokerNonIku.length) / 12);
                                    len = Math.ceil(len);
                                    i = 0;
                                    for (j = 0; j < len; j++) {
                                        var progress = "-";
                                        var progressM = "-";
                                        var progressP = "-";
                                        for (n = 0; n < monthId; n++) {

                                            if (monthId == 1) {
                                                progressM = "-";
                                                if (prokerNonIku[n + i].progress) {
                                                    progress = prokerNonIku[(n) + i].progress;
                                                } else {
                                                    progress = "-";
                                                }
                                                if (i < 11) {
                                                    if (prokerNonIku[(n + i) + 1].progress) {
                                                        progressP = prokerNonIku[(n + 1) + i].progress;
                                                    } else {
                                                        progressP = "-";
                                                    }
                                                }

                                            } else if (monthId == 12) {
                                                if (i > 0) {
                                                    if (prokerNonIku[(n + i) - 1].progress) {
                                                        progressM = prokerNonIku[((monthId - 2) + i)].progress
                                                    } else {
                                                        progressM = "-";
                                                    }
                                                }
                                                if (prokerNonIku[n + i].progress) {
                                                    progress = prokerNonIku[(monthId - 1) + i].progress;
                                                } else {
                                                    progress = "-";
                                                }
                                                progressP = "-";
                                            } else {
                                                if (n > 0) {
                                                    if (prokerNonIku[(n + i) - 1].progress) {
                                                        progressM = prokerNonIku[(n + i) - 1].progress
                                                    } else {
                                                        progressM = "-";
                                                    }
                                                }

                                                if (prokerNonIku[n + i].progress) {
                                                    progress = prokerNonIku[(n) + i].progress;
                                                } else {
                                                    progress = "-";
                                                }

                                                if (i < 11) {
                                                    if (prokerNonIku[(n + i) + 1].progress) {
                                                        progressP = prokerNonIku[(n + i) + 1].progress;
                                                    } else {
                                                        progressP = "-";
                                                    }
                                                }

                                            }

                                        }
                                        htmlBus += "<tr>";
                                        htmlBus += "<td>" + (j + 1) + "</td>";
                                        htmlBus += "<td>" + prokerNonIku[i].prokerName + "</td>";

                                        if (prokerNonIku[i].picName === null || prokerNonIku[i].picName === "") {
                                            htmlBus += "<td>-</td>";
                                        } else {
                                            htmlBus += "<td>" + prokerNonIku[i].picName + "</td>";
                                        }
                                        htmlBus += "<td>" + prokerNonIku[i].dateTarget + "</td>";
                                        htmlBus += "<td>" + progressM + "</td>";
                                        htmlBus += "<td>" + progress + "</td>";
                                        htmlBus += "<td>" + progressP + "</td>";
                                        htmlBus += "</tr>";
                                        i = i + 12;
                                    }
                                    $('#prokerNonIkuContent').html(htmlBus);
                                    $('.importReport').removeAttr("hidden");
                                } else {
                                    $('.importReport').attr("hidden", true);
                                    $('#prokerNonIkuContent').html("<tr><td colspan='9' style='background-color:#D2CCDE; text-align: center'>Tidak Ada Data!</td></tr>");
                                }
                            }
                        }
                    )
                    ;
                }
            }

            function downloadExcel() {
                var month = $('#listMonth').val();
                var year = $('#year').val();
                window.location.href = APP_PATH + "/rep/reporting/lb/downloadExcel/" + year + "/" + month;
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
                    Report LB
                </div>
                <div class="box-content">
                    <div class="row">
                        <div class="col-sm-9 paddingR0">
                            <div class="form-group" style="margin-bottom: 5px">
                                <div class="row">
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
                                        <%--<div class="col-md-3">--%>
                                        <%--<label>Filter Berdasarkan Triwulan</label>--%>
                                        <%--<select class="form-control" id="triwulan">--%>
                                        <%--<option value="">Pilih Triwulan</option>--%>
                                        <%--<c:forEach items="${listTriwulan}" var="data">--%>
                                        <%--<option value="${data.twId}">${data.twName}</option>--%>
                                        <%--</c:forEach>--%>
                                        <%--</select>--%>
                                        <%--</div>--%>
                                    <div class="col-md-3">
                                        <label>Filter Berdasarkan Bulan</label>
                                        <select class="form-control" id="listMonth">
                                            <option value="">Pilih Bulan</option>
                                            <c:forEach items="${listMonth}" var="data">
                                                <option value="${data.monthId}">${data.monthName}</option>
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
                                        Total Anggaran
                                    </div>
                                </h4>
                            </th>

                        </tr>
                    </table>
                    <br/>
                    <table id="table-bank" class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr style="background-color: #E0E0E3">
                            <th class="no-sort1" style="width:5%">TW</th>
                            <th style="width: 20%">Nominal</th>
                            <th style="width: 10%">%</th>
                            <th style="width: 20%">Realisasi</th>
                            <th style="width: 15%">Subsequence</th>
                            <th style="width: 15%">%</th>
                        </tr>
                        </thead>
                        <tbody id="budgetContent">

                        </tbody>
                    </table>
                </div>
            </div>
            <hr style="border-width: thick" class="reportContent" hidden/>
                <%--proker--%>

            <div class="section-box clearfix reportContent" hidden>
                <div class="box-title hide">
                </div>
                <div class="box-content">

                    <table width="100%">
                        <tr>
                            <th colspan="6">
                                <h4>
                                    <div class="box-title">
                                        Anggaran Program Kerja
                                    </div>
                                </h4>
                            </th>

                        </tr>
                    </table>
                    <br/>
                    <table id="table-proker" class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr style="background-color: #E0E0E3">
                            <th class="no-sort1" style="width:5%" rowspan="2">No</th>
                            <th rowspan="2">Kode Iku</th>
                            <th rowspan="2">Nama Program Kerja</th>
                            <th colspan="4" style="text-align: center">Anggaran</th>
                        </tr>
                        <tr style="background-color: #E0E0E3">
                            <th>rencana</th>
                            <th>Realisasi</th>
                            <th>Subsequence</th>
                            <th>%</th>
                        </tr>
                        </thead>
                        <tbody id="prokerContent">

                        </tbody>
                    </table>
                </div>
            </div>
            <hr style="border-width: thick" class="reportContent" hidden/>
                <%--proker iku--%>

            <div class="section-box clearfix reportContent" hidden>
                <div class="box-title hide">
                </div>
                <div class="box-content">

                    <table width="100%">
                        <tr>
                            <th colspan="6">
                                <h4>
                                    <div class="box-title">
                                        Program Kerja IKU
                                    </div>
                                </h4>
                            </th>

                        </tr>
                    </table>
                    <br/>
                    <table id="table-noniku" class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr style="background-color: #E0E0E3">
                            <th class="no-sort1" style="width:5%">No</th>
                            <th style="width: 20%">Kode IKU</th>
                            <th style="width: 10%">Nama Program Kerja</th>
                            <th style="width: 20%">PIC</th>
                            <th style="width: 15%">Target</th>
                            <th style="width: 15%">Periode</th>
                            <th style="width: 20%">Realisasi</th>
                            <th style="width: 15%">Pencapaian</th>
                            <th style="width: 15%">Penjelasan</th>
                        </tr>
                        </thead>
                        <tbody id="prokerIkuContent">

                        </tbody>
                    </table>
                </div>
            </div>
            <hr style="border-width: thick" class="reportContent" hidden/>

                <%--proker non iku--%>

            <div class="section-box clearfix reportContent" hidden>
                <div class="box-title hide">
                </div>
                <div class="box-content">

                    <table width="100%">
                        <tr>
                            <th colspan="6">
                                <h4>
                                    <div class="box-title">
                                        Program Kerja Non IKU
                                    </div>
                                </h4>
                            </th>

                        </tr>
                    </table>
                    <br/>
                    <table id="table-a" class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr style="background-color: #E0E0E3">
                            <th class="no-sort1" style="width:5%">No</th>
                            <th style="width: 10%">Nama Program Kerja</th>
                            <th style="width: 20%">PIC</th>
                            <th style="width: 15%">Target Waktu Penyelesaian</th>
                            <th style="width: 15%">Progress M-1</th>
                            <th style="width: 20%">Progress</th>
                            <th style="width: 15%">Progress M+1</th>
                        </tr>
                        </thead>
                        <tbody id="prokerNonIkuContent">

                        </tbody>
                    </table>
                </div>
            </div>
            <hr style="border-width: thick" class="reportContent" hidden/>

                <%--proker non iku--%>


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


