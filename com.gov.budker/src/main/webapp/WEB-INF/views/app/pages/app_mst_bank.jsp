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
        <li>Daftar Bank</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
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
                    "rowId": "bankId",
                    "pagingType": "full_numbers",
                    "serverSide": true,
                    "ajax": {
                        url: APP_PATH + "/app/bank/datasource",
                        type: 'POST',
                        datatype: 'json',
                        contentType: 'application/json',
                        data: function (d) {
                            return JSON.stringify(d);
                        }
                    },
                    columns: [
                        {
                            "data": null,
                        },
                        {"data": "bankName", "searchable": true},
                        {
                            "data": null,
                            "sortable": false,
                            "searchable": false,
                            "render": function (data, type, full, meta) {
                                return getIsActiveHtmlCode(data.status);
                            }
                        },
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
                                    //                                    '<li><a href="#" onclick="editMenu()"><i class="fa fa-search"></i>Lihat Detail</a></li>' +
                                    '<li><a href="#" onclick="editbankId(' + data.bankId + ')"><i class="fa fa-pencil"></i>Ubah</a></li>' +
                                    '<li><a href="#" onclick="deletebankId(' + data.bankId + ')"><i class="fa fa-trash"></i>Hapus</a></li>' +
                                    '</ul></div>';
                            }
                        },
                        {"data": "bankId", "visible": false},
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
                $("div.toolbar").html('<button class="btn btn-primary" onclick="addbankId()"><i class="fa fa-plus"></i>Tambah</button>');
                $('#datatable_fixed_column').on('draw.dt', function () {
                    $("[rel=tooltip]").tooltip();
                });
            });

            function addbankId() {
                var json;
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/app/bank/modal",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalBank').modal('toggle');
                    },
                    error: function () {
                    },
                    complete: function () {
                    }
                });
            }

            function editbankId(bankId) {
                var data = {
                    "bankId": bankId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: APP_PATH + "/app/bank/modal",
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                    },
                    success: function (data) {
                        $('#modal').html(data);
                        $('#modalBank').modal('toggle');
                    }
                });

            }

            function deletebankId(bankIdId) {
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
                            $('#modalConfirmation').modal("toggle");
                            var data = {
                                "bankId": bankIdId
                            };
                            $.ajax({
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json'
                                },
                                'type': 'POST',
                                'url': APP_PATH + '/app/bank/saveOrUpdate/' + 3,
                                'data': JSON.stringify(data),
                                'dataType': 'json',
                                success: function (hasil) {
                                    if (hasil.status) {
                                        $(".loading").hide();
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

            //            function deletebankId(bankIdId) {
            //                $('#modalConfirmation').modal("toggle");
            //                $("#submitDelete").click(function () {
            //                    $('#modalConfirmation').modal("toggle");
            //                    var data = {
            //                        "bankId": bankIdId
            //                    };
            //                    $.ajax({
            //                        headers: {
            //                            'Accept': 'application/json',
            //                            'Content-Type': 'application/json'
            //                        },
            //                        'type': 'POST',
            //                        'url': APP_PATH + '/app/bank/saveOrUpdate/' + 3,
            //                        'data': JSON.stringify(data),
            //                        'dataType': 'json',
            //                        success: function (hasil) {
            //                            if (hasil.status) {
            //                                $(".loading").hide();
            //                                otable.ajax.reload(null, false);
            //                                success("Data Berhasil Dihapus!")
            //                            } else {
            //                                danger("Data Gagal Dihapus!");
            //                            }
            //                        }
            //                    });
            //
            //                });
            //            }

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
                    List Bank
                </div>
                <div class="box-content">
                    <table id="datatable_fixed_column"
                           class="table table-striped table-bordered table-hover" width="100%">
                        <thead>
                        <tr>
                            <th style="border-right: none !important;">
                            </th>
                            <th class="hasinput">
                                <input type="text" class="form-control" placeholder="Filter Nama Bank"/>
                            </th>
                            <th>
                            </th>
                            <th>
                            </th>
                        </tr>
                        <tr>
                            <th width="2%">No.</th>
                            <th data-class="expand">Nama Bank</th>
                            <th width="10%">Status</th>
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


