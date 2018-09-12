<%-- 
    Created on : mei 2018, 1:21:28 PM
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MODAL ADD -->

<style type="text/css">
    #checkMenu{
        position : relative !important;
    }
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<div data-backdrop="static" class="modal fade in" id="modalRole" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-lg-50">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h3 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${dataRole.roleId == null}">Tambah </c:if>
                    <c:if test="${dataRole.roleId != null}">Ubah </c:if>
                    Role
                    <c:if test="${dataRole.roleId == null}"> Baru</c:if>
                </h3>
            </div>
            <div class="modal-body">
                <input type="hidden" id="roleId" name="roleId" value="${dataRole.roleId}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Nama Role</b></label>
                            <input name="roleName" id="roleName" type="text" class="form-control" placeholder=""
                                   value="${dataRole.roleName}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="status"><b>Is Admin</b></label>
                            <select name="isAdmin" id="isAdmin" class="form-control">
                                <option value="1"
                                        <c:if test="${dataRole.isAdmin == 1}">selected</c:if>>Ya
                                </option>
                                <option value="0"
                                        <c:if test="${dataRole.isAdmin == 0}">selected</c:if>>Tidak
                                </option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Deskripsi Role</b></label>
                            <input name="roleDescription" id="roleDescription" type="text" class="form-control"
                                   placeholder=""
                                   value="${dataRole.roleDescription}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Pilih Menu Role</b></label>
                            <table id="tableMenu" class="tableRole table table-bordered table-hover dataTable"
                                   width="100%">
                                <thead>
                                <tr>
                                    <th style="text-align: center;width: 20%">
                                        <input onchange="checkAll()" id="checkAll" type="checkbox"/>
                                    </th>
                                    <th style="text-align: center;">Kode Menu</th>
                                    <th style="text-align: left;">Nama Menu</th>
                                </tr>
                                </thead>
                                <c:forEach items="${menuList}" var="listItem">
                                    <tr>
                                        <td style="text-align: center;">
                                            <input data-menuid="${listItem.menuId}" style="right : 0px !important;"
                                                   id="checkMenu" type="checkbox" value="${listItem.menuId}"
                                                    <c:forEach items="${menuRole}" var="data">
                                                        <c:if test="${listItem.menuId == data.menuId}">checked</c:if>
                                                    </c:forEach>
                                            />
                                        </td>
                                        <td style="text-align: center;">${listItem.menuCode}</td>
                                        <td>${listItem.menuName}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="status"><b>Status</b></label>
                            <select name="status" id="status" class="form-control">
                                <option value="1"
                                        <c:if test="${dataRole.status == 1}">selected</c:if>>Aktif
                                </option>
                                <option value="0"
                                        <c:if test="${dataRole.status == 0}">selected</c:if>>Tidak Aktif
                                </option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-border-blue draft" data-dismiss="modal">
                    Batal
                </button>
                <button onclick="validate()" type="button" class="btn btn-primary">
                    Submit
                </button>
            </div>
        </div> <!--/.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- END MODAL ADD -->
<script type="text/javascript">
    $(document).ready(function () {

        $('#modalRole')
            .bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    roleName: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    roleDescription: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    status: {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            })
            .off('success.form.bv')
            .on('success.form.bv', function (e) {
                e.preventDefault();
                submitDataAdd();
            });
    });

    function checkAll() {
        var checkboxes = document.getElementsByTagName('input'), val = null;
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].type == 'checkbox') {
                if (val === null)
                    val = checkboxes[i].checked;
                checkboxes[i].checked = val;
            }
        }
    }

    function validate() {
        $('#modalRole').bootstrapValidator('validate');
    }

    function submit() {
        var data = $("#formRole").serializeJSON();
        var dataString = JSON.stringify(data);
        var idData = $("#roleId").val();
        var is = null;
        if (idData === "") {
            is = 1;
        } else {
            is = 2;
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/app/role/saveOrUpdate/' + is,
            'data': dataString,
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalRole').modal('toggle');
                    otable.ajax.reload(null, false);
                    success("Data Brhasil Disimpan!");
                } else {
                    danger("Data Gagal Disimpan!");
                }
            }
        });
    }

    function submitDataAdd() {

        if ($('#tableMenu').find('input#checkMenu[type="checkbox"]:checked').length == 0) {
            danger("Pilih Menu Terlebih Dahulu!");
            return null;
        }

        var Roledata = {
            "roleId": $('input#roleId').val(),
            "roleName": $('input#roleName').val(),
            "roleDescription": $('input#roleDescription').val(),
            "status": $('select#status').val(),
            "isAdmin": $('select#isAdmin').val()
        };

        var reqData = {
            "dataRole": Roledata,
            "dataMenuRole": []
        };


        $('#tableMenu').find('input#checkMenu[type="checkbox"]:checked').each(function () {
            reqData.dataMenuRole.push(
                $(this).data('menuid')
            );
        });

        var idData = $("#roleId").val();
        var is = null;
        if (idData === "") {
            is = 1;
        } else {
            is = 2;
        }

        if (idData === "") {
            is = 1;
        } else {
            is = 2;
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': APP_PATH + '/app/role/saveOrUpdate/' + is,
            'data': JSON.stringify(reqData),
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalRole').modal('toggle');
                    otable.ajax.reload(null, false);
                    success("Data Berhasil Disimpan!");
                } else {
                    danger("Data Gagal Disimpan");
                }
            }
        });
    }
</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>