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
        <li>Program Kerja</li>
        <li>Program Kerja</li>
        </jsp:attribute>
    <jsp:attribute name="custom_css">
        <style type="text/css">
            .rpClass {

                text-align: right;
            }
        </style>
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
                    "rowId": "menuId",
                    "pagingType": "full_numbers",
                    "serverSide": true,
                    "ajax": {
                        url: APP_PATH + "/prk/proker/datasource",
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
                        {"data": "prokerYear", "searchable": true},
//                        {"data": "dirName", "searchable": true},
                        {"data": "divName", "searchable": true},
                        {"data": "ikuCode", "searchable": true},
                        {"data": "mainProkerName", "searchable": true},
                        {"data": "subMainProkerName", "searchable": true},
//                        {"data": "picUserName", "searchable": true},
                        {
                            "data": null,
                            "render": function (data, type, full, meta) {
                                return formatRupiah2(data.prokerBudget);
                            }
                        },
                        {
                            "data": null,
                            "render": function (data, type, full, meta) {
                                if (data.totalRealization == null || data.totalRealization === '') {
                                    return 0;
                                } else {
                                    return formatRupiah2(data.totalRealization);
                                }

                            }
                        },
                        {
                            "data": null,
                            "render": function (data, type, full, meta) {
                                return formatRupiah2(data.prokerBudget - data.totalRealization);
                            }
                        },
                        {
                            "data": null,
                            "render": function (data, type, full, meta) {
                                if (data.isIku == 0) {
                                    return "-";
                                } else {
                                    return data.achievement;
                                }
                            }
                        },
                        {
                            "data": null,
                            "sortable": false,
                            "searchable": false,
                            "render": function (data, type, full, meta) {
                                return '<center><div class="btn-group">' +
                                    '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">' +
                                    'Aksi <span class="caret"></span>' +
                                    '</button>' +
                                    '<ul class="dropdown-menu dropdown-menu-right">' +
                                    //                                    '<li><a href="#" onclick="viewProker(' + data.prokerId + ')"><i class="fa fa-search"></i>Lihat Detail</a></li>' +
                                    '<li><a href="#" onclick="editProker(' + data.prokerId + ')"><i class="fa fa-pencil"></i>Ubah</a></li>' +
                                    '<li><a href="#" onclick="deleteProker(' + data.prokerId + ')"><i class="fa fa-trash"></i>Hapus</a></li>' +
                                    '</ul></div></center>';
                            }
                        },
                        {"data": "dateCreated", "visible": false},
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
                    "order": [[11, 'desc']]
                });

                $("#datatable_fixed_column_wrapper thead th input[type=text]").on('keyup change', function () {
                    otable
                        .column($(this).parent().index() + ':visible')
                        .search(this.value)
                        .draw();
                    number = 0;
                });
                $("div.toolbar").html('<button class="btn btn-primary" onclick="addProker()"><i class="fa fa-plus"></i>Tambah</button>');
                $('#datatableDelete').click(function () {
                    var id = otable.row('.selected').id();
                    var ids = otable.rows('.selected').ids();
                    if (ids.length == 0) {
                        showBigWarning("Peringatan", "Tidak ada data yang dipilih", 2000);
                    } else {
                        $.each(ids, function (key, value) {
                            deleteJobGrade(value);
                        });
                    }
                });
                $('#datatableEdit').click(function () {
                    var id = otable.row('.selected').id();
                    if (id == null) {
                        showBigWarning("Peringatan", "Tidak ada data yang dipilih", 2000);
                    } else {
                        editJobGrade(id);
                    }
                });
                $('#datatable_fixed_column').on('draw.dt', function () {
                    $("[rel=tooltip]").tooltip();
                });
            });


            function addProker() {
                var json;
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/prk/proker/modal",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $(".loading").hide();
                        $('#modal').html(data);
                        $('#modalPrkProker').modal('toggle');
                    }

                });
            }

            function editProker(prokerId) {
                var data = {
                    "prokerId": prokerId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/prk/proker/modal",
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalPrkProker').modal('toggle');
                    }
                });

            }

            function deleteProker(prokerId) {
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/app/confirmation/modal",
                    data: null,
                    contentType: 'application/json',
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
                                'url': APP_PATH + '/prk/proker/delete/' + prokerId,
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
                    List Program Kerja
                </div>
                <div class="box-content">
                    <table id="datatable_fixed_column" class="table table-striped table-bordered table-hover"
                           width="100%">
                        <thead>
                        <tr>
                            <th style="border-right: none !important;">
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Tahun"/>
                            </th>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter Nama Direktorat"/>--%>
                                <%--</th>--%>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Nama Divisi"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Kode IKU"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Nama Proker"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Nama Sub Proker"/>
                            </th>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter PIC"/>--%>
                                <%--</th>--%>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter Anggaran"/>--%>
                                <%--</th>--%>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter Realisasi"/>--%>
                                <%--</th>--%>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter Saldo"/>--%>
                                <%--</th>--%>
                                <%--<th class="hasinput">--%>
                                <%--<input type="text" class="form-control" placeholder="Filter Pencapaian"/>--%>
                            </th>
                            <th>

                            </th>
                        </tr>
                        <tr>
                            <th width="2%">No.</th>
                            <th data-class="expand">Tahun</th>
                                <%--<th data-class="expand">Nama Direktorat</th>--%>
                            <th data-class="expand">Nama Divisi</th>
                            <th data-class="expand">Kode IKU</th>
                            <th data-class="expand">Nama Proker</th>
                            <th data-class="expand">Nama Sub Proker</th>
                                <%--<th data-class="expand">PIC</th>--%>
                            <th data-class="expand text-right">Anggaran</th>
                            <th data-class="expand text-right">Realisasi</th>
                            <th data-class="expand text-right">Saldo</th>
                            <th data-class="expand">Pencapaian</th>
                            <th width="8%">
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


