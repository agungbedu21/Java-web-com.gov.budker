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
        <li>Realisasi</li>
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
                        url: APP_PATH + "/rlz/realization/datasource",
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
                                var result = data.prokerBudget - data.totalRealization;
                                return formatRupiah2(result);
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
                                    '<li><a href="#" onclick="editRealization(' + data.prokerId + ')"><i class="fa fa-pencil"></i>Update Realisasi</a></li>' +
                                    //                                    '<li><a href="#" onclick="deleteProker(' + data.prokerId + ')"><i class="fa fa-trash"></i>Hapus</a></li>' +
                                    '</ul></div><center>';
                            }
                        },{"data": "dateCreated", "visible": false},
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
                });
//                $("div.toolbar").html('<button class="btn btn-primary" onclick="editRealization()"><i class="fa fa-plus"></i>Update Realisasi</button>');
                $('#datatable_fixed_column').on('draw.dt', function () {
                    $("[rel=tooltip]").tooltip();
                });
            });


            function editRealization(prokerId) {
                var json = {
                    "prokerId": prokerId
                };
                loadingShow();
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/rlz/realization/modal",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    success: function (data) {
                        loadingHide();
                        $('#modal').html(data);
                        $('#modalRlzRealization').modal('toggle');
                    },
                    error: function () {
                        loadingHide();
                        danger("Tidak Diizinkan!");

                    },
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
                    List Realisasi
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
                            <%--</th>--%>
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
        <div class="modal modal-confirmation" id="modalConfirmation" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Konfirmasi</h4>
                    </div>
                    <div class="modal-body text-center">
                        <p>Apakah anda yakin ingin menghapus data?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="submitDelete">Ya
                        </button>
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Batal</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
    </jsp:attribute>
</t:genericpage>
<script src="${pageContext.request.contextPath}/resources/component/common.js"></script>


