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
        <li>Laporan Permasalahan SP</li>
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
                        'url': APP_PATH + '/rep/reporting/finishingb/getDataTableReport',
                        'data': JSON.stringify(data),
                        'dataType': 'json',
                        success: function (hasil) {
                            var finishingList = hasil.finishing;
                            var costTotalC = 0;
                            var costTotalD = 0;

                            //bus
                            if (finishingList.length > 0) {
                                var htmlBus = '';
                                for (i = 0; i < finishingList.length; i++) {
                                    htmlBus += "<tr>";
                                    htmlBus += "<td>" + (i + 1) + "</td>";
                                    htmlBus += "<td>" + finishingList[i].bankName + "</td>";
                                    htmlBus += "<td>" + finishingList[i].problems + "</td>";
                                    htmlBus += "<td>" + finishingList[i].realization + "</td>";
                                    htmlBus += "<td>" + (finishingList[i].realization / finishingList[i].problems) * 100 + " %</td>";
                                    htmlBus += "<td>" + finishingList[i].explanation + "</td>";
                                    htmlBus += "</tr>";
                                    costTotalC = costTotalC + (parseFloat(finishingList[i].problems));
                                    costTotalD = costTotalD + (parseFloat(finishingList[i].realization));
                                }
                                htmlBus += "<tr  style='background-color:#D2CCDE' ><td colspan='2' style='text-align: center'>Total</td>" +
                                    "<td><b>" + costTotalC + " </b></td>" +
                                    "<td><b>" + costTotalD + " </b></td>" +
                                    "<td colspan='2'></td></tr>";
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
                window.location.href = APP_PATH + "/rep/reporting/finishingb/downloadExcel/0/" + year + "/" + tw;
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
                    Report Permasalahan SP
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
                                        Permasalahan SP
                                    </div>
                                </h4>
                            </th>

                        </tr>
                    </table>
                    <br/>
                    <table id="table-bank" class="table table-hover table-striped table-bordered">
                        <thead>
                        <tr style="background-color: #E0E0E3">
                            <th class="no-sort1" style="width:5%">No</th>
                            <th style="width: 20%">Nama Bank</th>
                            <th style="width: 15%">Jumlah Komitmen yang Harus Ditindaklanjuti Bank</th>
                            <th style="width: 15%">Jumlah Komitmen yang Sudah Ditindaklanjuti Bank</th>
                            <th style="width: 10%">Pencapaian</th>
                            <th>Keterangan</th>
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


