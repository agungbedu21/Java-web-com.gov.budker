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
        <li>Laporan</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <script type="text/javascript">
            var otable;
            var number = 0;
            $(document).ready(function () {
            });

            function getDataTabel() {
                var divId = $('#division').val();
                var year = $('#year').val();
                var tw = $('#triwulan').val();
                if (year === '' || tw === '') {
                    $('.reportContent').attr("hidden", true);
                    danger("Tahun atau triwulan harus dipilih!");
                } else {
                    $('.reportContent').removeAttr("hidden");
                    var data = {
                        "division": $('#division').val(),
                        "year": $('#year').val(),
                        "triwulan": $('#triwulan').val()
                    };
                    $.ajax({
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        'type': 'POST',
                        'url': APP_PATH + '/rep/reporting/getDataTableReport',
                        'data': JSON.stringify(data),
                        'dataType': 'json',
                        success: function (hasil) {
                            var bprsList = hasil.bprs;
                            var busList = hasil.bus;
                            //bus
                            if (busList.length > 0) {
                                $('#busTarget').html(busList[0].ikuTarget + " %");
                                var htmlBus = '';
                                for (i = 0; i < busList.length; i++) {
                                    htmlBus += "<tr>";
                                    htmlBus += "<td>" + (i + 1) + "</td>";
                                    htmlBus += "<td>" + busList[i].divisionName + "</td>";
                                    htmlBus += "<td>" + busList[i].bankName + "</td>";
                                    htmlBus += "<td>" + busList[i].carBusValue + "</td>";
                                    htmlBus += "<td>" + busList[i].riskProfile + "</td>";
                                    htmlBus += "<td>" + busList[i].carMin + "</td>";
                                    htmlBus += "<td>" + busList[i].deltaCar + "</td>";
                                    htmlBus += "<td>" + busList[i].ach + "</td>";
                                    htmlBus += "</tr>";
                                }
                                htmlBus += "<tr  style='background-color:#D2CCDE' ><td colspan='7' style='text-align: center'>Capaian IKU</td><td><b>" + busList[0].ach + " %</b></td></tr>";
                                $('#busContent').html(htmlBus);
                            } else {
                                $('#busTarget').html("0%");
                                $('#busContent').html("<tr><td colspan='8' style='background-color:#D2CCDE; text-align: center'>Tidak Ada Data!</td></tr>");
                            }

                            //bprs
                            if (bprsList.length > 0) {
                                $('#bprsTarget').html(bprsList[0].ikuTarget + " %");
                                var htmlBprs = '';
                                $('#bprsContent').html(htmlBprs);
                                for (i = 0; i < bprsList.length; i++) {
                                    var sup = bprsList[i].supStatus;
                                    var supString = '';
                                    if (sup == 1) {
                                        supString = 'Normal';
                                    } else {
                                        supString = 'Intensif';
                                    }
                                    var isBal = bprsList[i].isBalance;
                                    var balString = '';
                                    if (isBal == 1) {
                                        balString = "Ya";
                                    } else {
                                        balString = "Tidak";
                                    }
                                    htmlBprs += "<tr>";
                                    htmlBprs += "<td>" + (i + 1) + "</td>"
                                    htmlBprs += "<td>" + bprsList[i].divisionName + "</td>"
                                    htmlBprs += "<td>" + supString + "</td>"
                                    htmlBprs += "<td>" + bprsList[i].car + " %</td>"
                                    htmlBprs += "<td>" + balString + "</td>"
                                    htmlBprs += "</tr>";
                                }
                                htmlBprs += "<tr  style='background-color:#D2CCDE' ><td colspan='4' style='text-align: center'>Capaian IKU</td><td><b>" + bprsList[0].ach + "%</b></td></tr>";
                                $('#bprsContent').html(htmlBprs);
                            } else {
                                $('#bprsTarget').html("0%");
                                $('#bprsContent').html("<tr><td colspan='5' style='background-color:#D2CCDE; text-align: center'>Tidak Ada Data!</td></tr>");
                            }
                            if (bprsList.length > 0 || busList.length > 0) {
                                $('.importReport').removeAttr('hidden');
                            } else {
                                $('.importReport').attr("hidden", true);
                            }
                        }
                    });
                }
            }

            function downloadExcel() {
                var divId = $('#division').val();
                var year = $('#year').val();
                var tw = $('#triwulan').val();
                window.location.href = APP_PATH + "/rep/reporting/downloadExcel/" + divId + "/" + year + "/" + tw;
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
                    Report Car Bus & BPRS
                </div>
                <div class="box-content">
                    <div class="row">
                        <div class="col-sm-9 paddingR0">
                            <div class="form-group" style="margin-bottom: 5px">
                                <div class="row">
                                    <div class="col-md-3">
                                        <label>Filter Berdasarkan Divisi</label>
                                        <select class="form-control" id="division">
                                            <option value="0">Pilih Divisi</option>
                                            <c:forEach items="${listDivision}" var="data">
                                                <option value="${data.divisionId}">
                                                        ${data.divisionName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
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
                                        CAR BUS
                                    </div>
                                </h4>
                            </th>
                            <th style="text-align: center"><h4><b>Target</b></h4></th>
                            <th style="text-align: center"><h4><b id="busTarget">0%</b></h4></th>

                        </tr>
                    </table>
                    <br/>
                    <table id="table-bank" class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr style="background-color: #E0E0E3">
                            <th class="no-sort1">No</th>
                            <th>Divisi</th>
                            <th>Nama Bank</th>
                            <th>Car Bus</th>
                            <th>Profile Resiko</th>
                            <th>Car Minimum</th>
                            <th>Delta Car</th>
                            <th>Pencapaian</th>
                        </tr>
                        </thead>
                        <tbody id="busContent">

                        </tbody>
                    </table>
                </div>
            </div>
            <hr style="border-width: thick" class="reportContent" hidden/>
            <div class="section-box clearfix reportContent" hidden>
                <div class="box-title hide">
                </div>
                <div class="box-content">
                    <table width="100%">
                        <tr>
                            <th colspan="3">
                                <h4>
                                    <div class="box-title">
                                        CAR BPRS
                                    </div>
                                </h4>
                            </th>
                            <th style="text-align: center"><h4><b>Target</b></h4></th>
                            <th style="text-align: center"><h4><b id="bprsTarget">0%</b></h4></th>

                        </tr>
                    </table>
                    <br/>
                    <table id="tablebprs" class="table table-hover table-striped table-bordered">
                        <thead>

                        <tr style="background-color: #E0E0E3">
                            <th class="no-sort1">No</th>
                            <th>Nama BPRS</th>
                            <th>Status Pengawasan</th>
                            <th>Car</th>
                            <th>Sesuai</th>
                        </tr>
                        </thead>
                        <tbody id="bprsContent">

                        </tbody>
                    </table>
                </div>
            </div>

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


