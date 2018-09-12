<%-- 
  agung.abdurohman and rillo pambudy
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
        <li>Home</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
        <script type="text/javascript">
            $(document).ready(function () {
                // pie chart
                pieChart1();

                //highChart
                highChart1();

                //kalkulasi
                getDataCalculation1();

                //proker
                getDataProker1();

                $('.select2').select2();
            });

            function pieChart() {
                loadingShow();
                pieChart1();
            }

            function pieChart1() {

                var data = {
                    "year": $('#yearPie').val(),
                    "triwulan": $('#triwulan').val()
                };
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    'type': 'POST',
                    'url': APP_PATH + '/prk/proker/getpiechart',
                    'data': JSON.stringify(data),
                    'dataType': 'json',
                    success: function (hasil) {
                        var bal = hasil.balance;
                        var rlz = hasil.realization;
                        Highcharts.chart('pie', {
                            chart: {
                                plotBackgroundColor: null,
                                plotBorderWidth: null,
                                plotShadow: false,
                                type: 'pie'
                            },
                            title: {
                                text: ' Persen Pencapaian Anggaran DPBS'
                            },
                            tooltip: {
                                pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    dataLabels: {
                                        enabled: true,
                                        format: '<b>{point.name}</b><br>{point.percentage:.2f} %',
                                        distance: -50,
                                        filter: {
                                            property: 'percentage',
                                            operator: '>',
                                            value: 10
                                        }
                                    },
                                    showInLegend: true
                                }
                            },
                            exporting: {
                                buttons: {
                                    contextButton: {
                                        menuItems: [{
                                            textKey: 'printChart',
                                            onclick: function () {
                                                this.print();
                                            }
                                        }, {
                                            separator: true
                                        }, {
                                            textKey: 'downloadPNG',
                                            onclick: function () {
                                                this.exportChart();
                                            }
                                        }, {
                                            textKey: 'downloadJPEG',
                                            onclick: function () {
                                                this.exportChart({
                                                    type: 'image/jpeg'
                                                });
                                            }
                                        }, {
                                            textKey: 'downloadPDF',
                                            onclick: function () {
                                                this.exportChart({
                                                    type: 'application/pdf'
                                                });
                                            }
                                        }, {
                                            textKey: 'downloadXLS',
                                            onclick: function () {
                                                this.downloadXLS();
                                            }
                                        }]
                                    }
                                }
                            },
                            series: [{
                                name: 'Anggaran',
                                colorByPoint: true,
                                data: [{
                                    name: 'Total Saldo Anggaran',
                                    y: parseInt(bal),
                                    sliced: false,
                                    selected: true
                                }, {
                                    name: 'Total Realisasi Anggaran',
                                    y: parseInt(rlz),
                                    sliced: false,
                                    selected: true
                                },]
                            }]
                        });
                    }
                });
                loadingHide();
            }

            function highChart() {
                loadingShow();
                highChart1();
            }

            function highChart1() {

                var data = {
                    "year": $('#year').val(),
                    "month": $('#month').val()
                };
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    'type': 'POST',
                    'url': APP_PATH + '/prk/proker/gethighchartdata',
                    'data': JSON.stringify(data),
                    'dataType': 'json',
                    success: function (hasil) {
                        var division = hasil.divisionList;
                        var budget = hasil.budgetList;
                        var rlz = hasil.realizationList;
                        var maxValue = hasil.max;
                        maxValue = maxValue;
                        var interval = maxValue / 10;
                        Highcharts.chart('bar', {
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: 'Anggaran dan Realisasi'
                            },
                            subtitle: {
                                text: '(dalam juta)'
                            },
                            xAxis: {
                                categories: division,
                                crosshair: true
                            },
                            yAxis: {
                                min: 0,
                                max: maxValue,
                                tickInterval: interval,
                                title: {
                                    text: ''
                                }
                            },
                            tooltip: {
                                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
                                footerFormat: '</table>',
                                shared: true,
                                useHTML: true
                            },
                            plotOptions: {
                                series: {
                                    borderWidth: 0,
                                    dataLabels: {
                                        enabled: true,
                                        format: '{point.y:.1f}'
                                    }
                                },
                                column: {
                                    pointPadding: 0.2,
                                    borderWidth: 0
                                }
                            }, exporting: {
                                buttons: {
                                    contextButton: {
                                        menuItems: [{
                                            textKey: 'printChart',
                                            onclick: function () {
                                                this.print();
                                            }
                                        }, {
                                            separator: true
                                        }, {
                                            textKey: 'downloadPNG',
                                            onclick: function () {
                                                this.exportChart();
                                            }
                                        }, {
                                            textKey: 'downloadJPEG',
                                            onclick: function () {
                                                this.exportChart({
                                                    type: 'image/jpeg'
                                                });
                                            }
                                        }, {
                                            textKey: 'downloadPDF',
                                            onclick: function () {
                                                this.exportChart({
                                                    type: 'application/pdf'
                                                });
                                            }
                                        }, {
                                            textKey: 'downloadXLS',
                                            onclick: function () {
                                                this.downloadXLS();
                                            }
                                        }]
                                    }
                                }
                            },
                            series: [{
                                name: 'Anggaran',
                                data: budget
                            }, {
                                name: 'Realisasi',
                                data: rlz
                            }]
                        })
                    }
                });
                loadingHide();
            }

            function getDataCalculation() {
                loadingShow();
                getDataCalculation1();
            }

            function getDataCalculation1() {

                var data = {
                    "year": $('#calYear').val()
//                    "divId": $('#division').val()
                };
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    'type': 'POST',
                    'url': APP_PATH + '/cal/growth/gettabelcalculation',
                    'data': JSON.stringify(data),
                    'dataType': 'json',
                    success: function (hasil) {
                        var res = hasil.data;
                        console.log(JSON.stringify(res))
                        var htmlResult = '';
                        for (i = 0; i < res.length; i++) {
                            htmlResult += '<tr>';
                            htmlResult += '<td>' + res[i].tahun + '</td>';
                            htmlResult += '<td>' + res[i].kalkulasi + '</td>';
                            htmlResult += '<td>' + res[i].ikuTarget + '</td>';
                            if (res[i].achTotal === null) {
                                htmlResult += '<td>-</td>';
                            } else {
                                htmlResult += '<td>' + res[i].achTotal.toFixed(2) + '</td>';
                            }
                            if (res[i].achIku === null) {
                                htmlResult += '<td>-</td>';
                            } else {
                                htmlResult += '<td>' + res[i].achIku.toFixed(2)  + '</td>';
                            }
                            htmlResult += '</tr>';
                        }
                        $('#dataResult').html(
                            htmlResult
                        );
                        loadingHide();
                    }
                });
                loadingHide();
            }

            function getDataProker() {
                loadingShow();
                getDataProker1();
            }

            function getDataProker1() {

                var data = {
                    "year": $('#prkYear').val()
                };
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    'type': 'POST',
                    'url': APP_PATH + '/prk/proker/getlistprokerdivision',
                    'data': JSON.stringify(data),
                    'dataType': 'json',
                    success: function (hasil) {
                        var res = hasil.data;
                        var htmlResult = '';
                        for (i = 0; i < res.length; i++) {
                            htmlResult += '<tr>';
                            htmlResult += '<td>' + res[i].divisionName + '</td>';
                            if (res[i].ikuCode === null) {
                                htmlResult += '<td>-</td>';
                            } else {
                                htmlResult += '<td>' + res[i].ikuCode + '</td>';
                            }

                            htmlResult += '<td>' + res[i].prokerName + '</td>';
                            if (res[i].achievement === null) {
                                htmlResult += '<td>-</td>';
                            } else {
                                htmlResult += '<td>' + res[i].achievement + '</td>';
                            }

                            htmlResult += '</tr>';
                        }
                        $('#dataResultProker').html(
                            htmlResult
                        );
                        loadingHide();
                    }
                });
                loadingHide();
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
        <div class="row">
            <div class="col-md-5">
                <div class="body-box">
                    <div class="section-box clearfix">
                        <div class="box-title">
                            Pencapaian Anggaran DPBS
                        </div>
                        <div class="box-content">
                            <div class="row">
                                <div class="col-sm-9 paddingR0">
                                    <div class="form-group">
                                        <label></label>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <select class="form-control select2" id="yearPie">
                                                    <c:forEach items="${listYear}" var="data">
                                                        <option value="${data.year}" <c:if
                                                                test="${data.year==2018}">selected</c:if>>${data.year}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-6">
                                                <select class="form-control select2" id="triwulan">
                                                    <c:forEach items="${listTriwulan}" var="data">
                                                        <option value="${data.twId}"> TW ${data.twName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <button class="btn btn-primary" onclick="pieChart()">Filter</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="pie"
                                 style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-7">
                <div class="body-box">
                    <div class="section-box clearfix">
                        <div class="box-title">
                            Pencapaian Anggaran per Satker
                        </div>
                        <div class="box-content">
                            <div class="row">
                                <div class="col-sm-9 paddingR0">
                                    <div class="form-group">
                                        <label></label>
                                        <div class="row">
                                            <div class="col-md-3">
                                                <select class="form-control select2" id="year">
                                                    <c:forEach items="${listYear}" var="data">
                                                        <option value="${data.year}" <c:if
                                                                test="${data.year==2018}">selected</c:if>>${data.year}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-6">
                                                <select class="form-control select2" id="month">
                                                    <c:forEach items="${listMonth}" var="data">
                                                        <option value="${data.monthId}">${data.monthName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <button class="btn btn-primary" onclick="highChart()">Filter
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="bar"
                                 style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
			<div class="row" style="margin-top: 25px;">
                <div class="col-md-12">
                    <div class="body-box">
                        <div class="section-box clearfix">
                            <div class="box-title">
                                Kalkulasi IKU Divisi Pengawasan
                            </div>
                            <div class="box-content">
                                <div class="row">
                                    <div class="col-sm-9 paddingR0">
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-md-2">
                                                    <label>Tahun</label>
                                                    <select class="form-control select2" id="calYear">
                                                        <c:forEach items="${listYear}" var="data">
                                                        <option value="${data.year}" <c:if
                                                                test="${data.year==2018}">selected</c:if>>${data.year}</option>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                                    <%--<div class="col-md-4">--%>
                                                    <%--<select class="form-control" id="division">--%>
                                                    <%--<c:forEach items="${listDiv}" var="data">--%>
                                                    <%--<option value="${data.divisionId}">${data.divisionName}</option>--%>
                                                    <%--</c:forEach>--%>
                                                    <%--</select>--%>
                                                    <%--</div>--%>
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <div style="margin-bottom: 5px">&nbsp</div>
                                                        <button class="btn btn-primary" onclick="getDataCalculation()">
                                                            Lihat
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <table id="table-calculation" class="table table-hover table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Tahun</th>
                                        <th>Kalkulasi</th>
                                        <th>Target IKU</th>
                                        <th>Total Capaian</th>
                                        <th>IKU (%)</th>
                                    </tr>
                                    </thead>
                                    <tbody id="dataResult">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div class="row" style="margin-top: 25px;">
            <div class="col-md-12">
                <div class="body-box">
                    <div class="section-box clearfix">
                        <div class="box-title">
                            Program Kerja IKU
                        </div>
                        <div class="box-content">
                            <div class="row">
                                <div class="col-sm-9 paddingR0">
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-md-2">
                                                <label>Tahun</label>
                                                <select class="form-control select2" id="prkYear">
                                                    <c:forEach items="${listYear}" var="data">
                                                        <option value="${data.year}"
                                                                <c:if test="${data.year== 2018}"> selected </c:if>
                                                        >${data.year}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <div style="margin-bottom: 5px">&nbsp</div>
                                                    <button class="btn btn-primary" onclick="getDataProker()">
                                                        Lihat
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <table id="table-proker" class="table table-hover table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th>Divisi</th>
                                    <th>Kode Iku</th>
                                    <th>Nama Proker</th>
                                    <th>Pencapaian</th>
                                </tr>
                                </thead>
                                <tbody id="dataResultProker">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="modal"></div>
    </jsp:attribute>
</t:genericpage>
<script src="${pageContext.request.contextPath}/resources/component/common.js"></script>


