<%-- 
  agung.abdurohman@bni.co.id
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="pagetitle">
        SiMoKA
    </jsp:attribute>
    <jsp:attribute name="breadcrumb">
        <%--BREAD CRUMB GOES HERE--%>
        <li>Kalkulasi</li>
        <li>Penyelesaian Komitmen</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
        <script type="text/javascript">
            var otable;
            var number = 0;
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
                        url: APP_PATH + "/cal/finishing/datasource",
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
                        {"data": "finishingTypeName", "searchable": true},
                        {"data": "year", "searchable": true},
                        {
                            "data": null,
                            "render": function (data, type, full, meta) {
                                return renderMonth(data.month);
                            }
                        },
//                        {"data": "ikuTarget", "searchable": true},
                        {
                            "data": null,
                            "render": function (data, type, full, meta) {
                                var growthPercentage = parseFloat(data.achTotal).toFixed(2);
                                if (isNaN(growthPercentage)) {
                                    return 0;
                                }
                                if (growthPercentage == null) {
                                    return 0;
                                } else {
                                    return growthPercentage;
                                }
                            }
                        },
//                        {
//                            "data": null,
//                            "render": function (data, type, full, meta) {
//                                return parseFloat((data.achTotal / data.ikuTarget) * 100).toFixed(3);
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
                                    '<li><a href="#" onclick="viewFinishing(' + data.finishingId + ')"><i class="fa fa-search"></i>Lihat Detail</a></li>' +
                                    '<li><a href="#" onclick="editFinishing(' + data.finishingId + ')"><i class="fa fa-pencil"></i>Ubah</a></li>' +
                                    '<li><a href="#" onclick="deleteBudget(' + data.finishingId + ')"><i class="fa fa-trash"></i>Hapus</a></li>' +
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
                $("div.toolbar").html('<button class="btn btn-primary" onclick="addFinishing()"><i class="fa fa-plus"></i>Tambah</button>');
            });


            function addFinishing() {
                var json;
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/cal/finishing/modal",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalCalFinsihing').modal('toggle');
                    },
                    error: function () {
                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });
            }

            function viewFinishing(finishingId) {
                var json = {
                    "finishingId": finishingId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/cal/finishing/modalview",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalCalFinsihingView').modal('toggle');
                    },
                    error: function () {
                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });
            }

            function editFinishing(finishingId) {
                var data = {
                    "finishingId": finishingId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/cal/finishing/modal",
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalCalFinsihing').modal('toggle');
                    },
                    error: function () {
                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });

            }

            function deleteBudget(finishingId) {
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
                                'url': APP_PATH + '/cal/finishing/delete/' + finishingId,
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

            function renderMonth(month) {
                var stringMonth = "";
                switch (month) {
                    case 1 :
                        stringMonth = "Januari";
                        break;
                    case 2 :
                        stringMonth = "Februari";
                        break;
                    case 3 :
                        stringMonth = "Maret";
                        break;
                    case 4 :
                        stringMonth = "April";
                        break;
                    case 5 :
                        stringMonth = "Mei";
                        break;
                    case 6 :
                        stringMonth = "Juni";
                        break;
                    case 7 :
                        stringMonth = "Juli";
                        break;
                    case 8 :
                        stringMonth = "Agustus";
                        break;
                    case 9 :
                        stringMonth = "September";
                        break;
                    case 10 :
                        stringMonth = "Oktober";
                        break;
                    case 11 :
                        stringMonth = "November";
                        break;
                    case 12 :
                        stringMonth = "Desember";
                        break;
                    default :
                        stringMonth = "";
                        break;
                }
                return stringMonth;
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
                    List Penyelesaian Komitmen
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
                                <input type="text" class="form-control" placeholder="Filter Jenis"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Tahun"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Bulan"/>
                            </th>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter Target IKU (%)"/>--%>
                                <%--</th>--%>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Total Capaian (%) "/>
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
                            <th data-class="expand">Jenis</th>
                            <th data-class="expand">Tahun</th>
                            <th data-class="expand">Bulan</th>
                                <%--<th data-class="expand">Target IKU</th>--%>
                            <th data-class="expand">Total Capaian</th>
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


