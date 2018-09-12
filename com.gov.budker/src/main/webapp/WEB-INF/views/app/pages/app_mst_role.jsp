<%--
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="pagetitle">
        SiMoKA
    </jsp:attribute>
    <jsp:attribute name="breadcrumb">
        <%--BREAD CRUMB GOES HERE--%>
        <li>Pengaturan</li>
        <li>Manajemen role</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <script type="text/javascript">
            var otable;
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
                    "rowId": "roleId",
                    "pagingType": "full_numbers",
                    "serverSide": true,
                    "ajax": {
                        url: APP_PATH + "/app/role/datasource",
                        type: 'POST',
                        datatype: 'json',
                        contentType: 'application/json',
                        data: function (d) {
                            return JSON.stringify(d);
                        }
                    },
                    columns: [
                        {"data": null},
                        {"data": "roleName", "searchable": true},
                        {"data": "roleDescription", "searchable": true},
                        {
                            "data": "isAdmin",
                            "sortable": false,
                            "searchable": false,
                            "render": function (data, type, full, meta) {
                                return getIsActiveHtmlCode(data);
                            }
                        },
                        {
                            "data": "status",
                            "sortable": false,
                            "searchable": false,
                            "render": function (data, type, full, meta) {
                                return getIsActiveHtmlCode(data);
                            }
                        }, {
                            "data": null,
                            "sortable": false,
                            "searchable": false,
                            "render": function (data, type, full, meta) {
                                return '<div class="btn-group">' +
                                    '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">' +
                                    'Aksi <span class="caret"></span>' +
                                    '</button>' +
                                    '<ul class="dropdown-menu dropdown-menu-right">' +
                                    //                                    '<li><a href="#" onclick="editMenu()"><i class="fa fa-search"></i>Lihat Detail</a></li>' +
                                    '<li><a href="#" onclick="editrole(' + data.roleId + ')"><i class="fa fa-pencil"></i>Ubah</a></li>' +
                                    '<li><a href="#" onclick="deleterole(' + data.roleId + ')"><i class="fa fa-trash"></i>Hapus</a></li>' +
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
                $("div.toolbar").html('<button class="btn btn-primary" onclick="addrole()"><i class="fa fa-plus"></i>Tambah</button>');
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

            function addrole() {
                var json;
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/app/role/modal",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalRole').modal('toggle');
                    }
                });
            }

            function editrole(roleId) {
                var data = {
                    "roleId": roleId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/app/role/modal",
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $(".loading").hide();
                        $('#modal').html(data);
                        $('#modalRole').modal('toggle');
                    }
                });

            }

            function deleterole(roleId) {
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
                        $('#modalConfirmation').modal('toggle');
                        $("#submitDelete").click(function () {

                            var data = {
                                "roleId": roleId
                            };
                            $.ajax({
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json'
                                },
                                'type': 'POST',
                                'url': APP_PATH + '/app/role/saveOrUpdate/' + 3,
                                'data': JSON.stringify(data),
                                'dataType': 'json',
                                success: function (hasil) {
                                    if (hasil.status) {
                                        otable.ajax.reload(null, false);
                                        success("Data Berhasil Dihapus!")
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
                    List Role
                </div>
                <div class="box-content">
                    <table id="datatable_fixed_column"
                           class="table table-striped table-bordered table-hover" width="100%">
                        <thead>
                        <tr>
                            <th style="border-right: none !important;">
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Nama role"/>
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control"
                                       placeholder="Filter Deskripsi role"/>
                            </th>
                            <th>

                            </th>
                            <th>

                            </th>
                            <th>

                            </th>
                        </tr>
                        <tr>
                            <th width="2%">No.</th>
                            <th data-class="expand">Nama role</th>
                            <th data-class="expand">Deskripsi role</th>
                            <th width="10%">
                                <center>Is Admin</center>
                            </th>
                            <th width="5%">
                                <center>Status</center>
                            </th>
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
        <div class="modal  modal-confirmation" id="modalConfirmation" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Konfirmasi</h4>
                    </div>
                    <div class="modal-body text-center">
                        <p>Apakah anda yakin ingin menghapus data?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="submitDelete">Ya</button>
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


