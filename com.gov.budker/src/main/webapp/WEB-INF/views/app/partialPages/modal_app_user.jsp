<%--
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MODAL ADD -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<div data-backdrop="static" class="modal fade in" id="modalAppUser" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h3 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${userList.employeeId == null}">Tambah </c:if>
                    <c:if test="${userList.employeeId != null}">Ubah </c:if>
                    User
                    <c:if test="${userList.employeeId == null}"> Baru</c:if>
                </h3>
            </div>
            <div class="modal-body">
                <form id="formUser">
                    <input type="hidden" id="employeeId" name="employeeId" value="${userList.employeeId}">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label><b>Nama User</b></label>
                                <input name="employeeName" id="employeeName" type="text" class=" form-control"
                                       value="${userList.employeeName}">
                            </div>
                            <div class="form-group">
                                <label><b>Email User</b></label>
                                <input name="employeeUserName" id="employeeUserName" type="text" class=" form-control"
                                       value="${userList.employeeUserName}">
                            </div>
                            <div class="form-group">
                                <label><b>Password</b></label>
                                <input name="employeePassword" id="employeePassword" type="password"
                                       class=" form-control" placeholder="" value="${userList.employeePassword}">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>User Role</b></label>
                                <select name="employeeRoleId" id="employeeRoleId" class="form-control select2">
                                    <option value="">-- Pilih Role --</option>
                                    <c:forEach items="${listRole}" var="data">
                                        <option value="${data.roleId}"
                                                <c:if test="${userList.roleId == data.roleId}">selected</c:if>>
                                                ${data.roleName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>Divisi</b></label>
                                <select name="employeeDivision" id="employeeDivision" class="form-control select2">
                                    <option value="">-- Pilih Divisi --</option>
                                    <c:forEach items="${listDiv}" var="data">
                                        <option value="${data.divisionId}"
                                                <c:if test="${userList.employeeDivision == data.divisionId}">selected</c:if>>
                                                ${data.divisionName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
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
<script>
    $(document).ready(function () {
        $('#modalAppUser')
            .bootstrapValidator({
                fields: {
                    employeeName: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    employeePassword: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    employeeRoleId: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    employeeDivision: {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            })
            .off('success.form.bv')
            .on('success.form.bv', function (e) {
                e.preventDefault();
                submit();
            });
        $('.select2').select2();
    });

    function validate() {
        $('#modalAppUser').bootstrapValidator('validate');
    }

    function submit() {
        var data = {
            "employeeId": $('#employeeId').val(),
            "employeeName": $('#employeeName').val(),
            "employeeUserName": $('#employeeUserName').val(),
            "employeePassword": $('#employeePassword').val(),
            "employeeDivision": $('#employeeDivision').val(),
            "employeeRoleId": $('#employeeRoleId').val()
        };
        var dataString = JSON.stringify(data);
        var idData = $("#employeeId").val();
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
            'url': APP_PATH + '/app/employee/saveOrUpdate/' + is,
            'data': dataString,
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $('#modalAppUser').modal('toggle');
                    otable.ajax.reload(null, false);
                    success("Data Berhasil Disimpan!");
                } else {
                    danger("Data Gagal Disimpan!");
                }
            }
        });
    }
</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>