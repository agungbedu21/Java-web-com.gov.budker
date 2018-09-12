<%--
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MODAL ADD -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<div data-backdrop="static" class="modal fade in" id="modalChangePassword" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h4 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    Ubah Password
                </h4>
            </div>
            <div class="modal-body">
                <form id="formUser">
                    <input type="password" hidden id="employeeId" name="employeeId" value="${employeeData.employeeId}">
                    <input type="password" hidden id="employeePasswordOld" name="employeePasswordOld"
                           value="${employeeData.employeePassword}">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label><b>Nama User</b></label>
                                <input name="employeeName" id="employeeName" disabled type="text" class=" form-control"
                                       value="${employeeData.employeeName}">
                            </div>
                            <div class="form-group">
                                <label><b>Masukan Password Lama</b></label>
                                <input name="employeePassword" id="employeePassword" type="password"
                                       class=" form-control">
                            </div>
                            <div class="form-group">
                                <label><b>Masukan Password Baru</b></label>
                                <input name="newPassword" id="newPassword" type="password"
                                       class=" form-control">
                            </div>
                            <div class="form-group">
                                <label><b>Konfirmasi Masukan Password Baru</b></label>
                                <input name="newPasswordCfm" id="newPasswordCfm" type="password"
                                       class=" form-control">
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
        $('#modalChangePassword')
            .bootstrapValidator({
                fields: {
                    newPassword: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    employeePassword: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    newPasswordCfm: {
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
        $('#modalChangePassword').bootstrapValidator('validate');
    }

    function submit() {
        var oldPwd = $('#employeePasswordOld').val();
        var oldPwdInput = $('#employeePassword').val();
        var newPwd = $('#newPassword').val();
        var newPwdCfm = $('#newPasswordCfm').val();
        if (oldPwdInput === oldPwd) {
            if (newPwd === newPwdCfm) {
                var data = {
                    "employeeId": $('#employeeId').val(),
                    "employeePassword": newPwdCfm
                };
                var dataString = JSON.stringify(data);
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    'type': 'POST',
                    'url': APP_PATH + '/app/employee/savechange',
                    'data': dataString,
                    'dataType': 'json',
                    success: function (hasil) {
                        if (hasil.status) {
                            $('#modalChangePassword').modal('toggle');
                            success("Password Berhasil Diubah!");
                        } else {
                            danger("Password Gagal Diubah!");
                        }
                    }
                });
            } else {
                danger("Konfirmasi password salah!");
            }
        } else {
            danger("Password lama salah!");
        }

    }
</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>