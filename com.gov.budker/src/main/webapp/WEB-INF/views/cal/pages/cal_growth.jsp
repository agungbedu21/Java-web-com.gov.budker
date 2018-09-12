<%-- 
  agung.abdurohman@bni.co.id
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="pagetitle">
        SiMoKA - Kalkulasi - Pertumbuhan
    </jsp:attribute>
    <jsp:attribute name="breadcrumb">
        <%--BREAD CRUMB GOES HERE--%>
        <li>Kalkulasi</li>
        <li>Pertumbuhan</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
        <script type="text/javascript">
            var otable;
            var i = 0;
            $(document).ready(function () {
                var breakpointDefinition = {
                    tablet: 1024,
                    phone: 480
                };

                otable = $('#datatable_fixed_column').DataTable({
                    scrollY: "500px",
                    scrollX: true,
                    scrollCollapse: false,
                    'processing': true,
                    'paging': true,
                    'lengthChange': true,
                    'searching': true,
                    'ordering': true,
                    'info': true,
                    'autoWidth': false,
                    "rowId": "finishingId",
                    "pagingType": "full_numbers",
                    "serverSide": true,
                    "ajax": {
                        url: APP_PATH + "/cal/growth/datasource",
                        type: 'POST',
                        datatype: 'json',
                        contentType: 'application/json',
                        data: function (d) {
                            return JSON.stringify(d);
                        }
                    },
                    columns: [
                        {
                            "data": null
                        },
                        {"data": "divName", "searchable": true},
                        {"data": "year", "searchable": true},
                        {
                            "data": null,
                            "render": function (data, type, full, meta) {
                                return renderTriwulan(data.triwulan);
                            }
                        },
                        {"data": "totalYoy", "searchable": true},
                        {"data": "totalMonth", "searchable": true},
//                        {"data": "ikuTarget", "searchable": true},
                        {
                            "data": null,
                            "render": function (data, type, full, meta) {
                                var growthPercentage = (data.totalMonth - data.totalYoy) / data.totalYoy * 100;
                                if (isNaN(growthPercentage)) {
                                    return 0;
                                }
                                if (growthPercentage == null) {
                                    return 0;
                                } else {
                                    var res = parseFloat(growthPercentage).toFixed(2);
                                    return res;
                                }
                            }
                        },
//                        {
//                            "data": null,
//                            "render": function (data, type, full, meta) {
//                                var growthPercentage = data.totalYoy / data.totalMonth;
//                                var ach = growthPercentage / data.ikuTarget;
//                                if(isNaN(ach)){
//                                    return 0;
//                                }
//                                if (ach == null) {
//                                    return 0;
//                                } else if (ach > 110) {
//                                    return 110;
//                                } else {
//                                    var res = parseFloat(ach).toFixed(3);
//                                    return res;
//                                }
//                            }
//                        },
                        {
                            "data": null,
                            "sortable": false,
                            "searchable": false,
                            "render": function (data, type, full, meta) {
                                return '<div class="btn-group">' +
                                    '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">' +
                                    'Aksi <span class="caret"></span>' +
                                    '</button>' +
                                    '<ul class="dropdown-menu dropdown-menu-right">' +
                                    '<li><a href="#" onclick="viewGrowth(' + data.calGrowthId + ')"><i class="fa fa-search"></i>Lihat Detail</a></li>' +
                                    '<li><a href="#" onclick="editGrowth(' + data.calGrowthId + ')"><i class="fa fa-pencil"></i>Ubah</a></li>' +
                                    '<li><a href="#" onclick="deleteGrowth(' + data.calGrowthId + ')"><i class="fa fa-trash"></i>Hapus</a></li>' +
                                    '</ul></div>';
                            }
                        }
                    ],
                    "lengthMenu": [[10, 20, 30], [10, 20, 30]],
                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6 paddingRL0'<'toolbar'>><'col-sm-6 col-xs-12 hidden-xs paddingRL0 text-right'l>r>" +
                    "t" +
                    "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 paddingRL0'i><'col-xs-12 col-sm-6 paddingRL0'p>>",
                    "autoWidth": true,
                    "oLanguage": {
                        "sLengthMenu": "Menampilkan _MENU_ data per halaman",
                        "sInfo": "Menampilkan _START_ - _END_ dari _TOTAL_ data",
                        "sZeroRecords": "Tidak ada data yang ditampilkan",
                        "sInfoEmpty": "Tidak ada data",
                    },
                    language: {
                        searchPlaceholder: "Search records"
                    },
                    "drawCallback": function (oSettings) {
                        for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
                            $('td:eq(0)', oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(i + 1 + oSettings._iDisplayStart);
                        }
                    },
                    "columnDefs": [{
                        "searchable": false,
                        "orderable": false,
                        "targets": -0
                    }],
                    "order": [[1, 'asc']]
                });

                $("#datatable_fixed_column_wrapper thead th input[type=text]").on('keyup change', function () {
                    otable
                        .column($(this).parent().index() + ':visible')
                        .search(this.value)
                        .draw();
                });
                $("div.toolbar").html('<button class="btn btn-primary" onclick="addGrowth()"><i class="fa fa-plus"></i>Tambah</button>');
            });


            function addGrowth() {
                var json;
                loadingShow();
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/cal/growth/modal",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        loadingHide();
                        $('#modalCalGrowth').modal('toggle');
                    },
                    error: function () {
                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });
            }

            function viewGrowth(calGrowthId) {

                var json = {
                    "calGrowthId": calGrowthId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/cal/growth/modalview",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        loadingShow();
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        loadingHide();
                        $('#modalCalGrowthView').modal('toggle');
                    },
                    error: function () {
                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });
            }

            function editGrowth(calGrowthId) {
                var data = {
                    "calGrowthId": calGrowthId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/cal/growth/modal",
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalCalGrowth').modal('toggle');
                    },
                    error: function () {
                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });

            }

            function deleteGrowth(calGrowthId) {
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/app/confirmation/modal",
                    data: null,
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalConfirmation').modal("toggle");
                        $("#submitDelete").click(function () {
                            $.ajax({
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json'
                                },
                                'type': 'POST',
                                'url': APP_PATH + '/cal/growth/delete/' + calGrowthId,
                                'data': null,
                                'dataType': 'json',
                                success: function (hasil) {
                                    if (hasil.status) {
                                        otable.ajax.reload(null, false);
                                        success("Data Berhasil Dihapus!");
                                    } else {
                                        danger("Data Gagal Dihapus!");
                                    }
                                }
                            });
                        });
                    }
                });
            }

            function renderTriwulan(tw) {
                var stringTriwulan = "";
                switch (tw) {
                    case 1 :
                        stringTriwulan = "1 (Satu)";
                        break;
                    case 2 :
                        stringTriwulan = "2 (Dua)";
                        break;
                    case 3 :
                        stringTriwulan = "3 (Tiga)";
                        break;
                    case 4 :
                        stringTriwulan = "4 (Empat)";
                        break;
                    default :
                        stringTriwulan = "";
                        break;
                }
                return stringTriwulan;
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
                    List Pertumbuhan
                </div>
                <div class="box-content">
                    <table id="datatable_fixed_column" class="table table-striped table-bordered table-hover"
                           width="100%">
                        <thead>
                        <tr>
                            <th style="border-right: none !important;">
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Nama Divisi"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Tahun"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Triwulan"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Total Pembiayaan Yoy"/>
                            </th>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter Target IKU (%)"/>--%>
                                <%--</th>--%>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Persentase Pertumbuhan"/>
                            </th>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter Capaian IKU(%) "/>--%>
                                <%--</th>--%>
                            <th>

                            </th>
                        </tr>
                        <tr>
                            <th width="2%">No.</th>
                            <th data-class="expand">Nama Divisi</th>
                            <th data-class="expand">Tahun</th>
                            <th data-class="expand">Triwulan</th>
                            <th data-class="expand">Total Pembiayaan Yoy</th>
                            <th data-class="expand">Total Pembiayaan Bulan Berjalan</th>
                                <%--<th data-class="expand">Target IKU (%)</th>--%>
                            <th data-class="expand">Persentase Pertumbuhan</th>
                                <%--<th data-class="expand">Capaian IKU</th>--%>
                            <th width="12%">
                                <center>Aksi</center>
                            </th>
                        </tr>
                        </thead>
                    </table>

                </div>
            </div>
        </div>
        <div id="modal">

        </div>
    </jsp:attribute>
</t:genericpage>
<script src="${pageContext.request.contextPath}/resources/component/common.js"></script>


