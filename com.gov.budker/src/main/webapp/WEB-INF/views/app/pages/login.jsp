<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="apple-touch-icon" sizes="57x57"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath}/resources/img/ojk-ico/favicon-16x16.png">
    <link rel="manifest" href="${pageContext.request.contextPath}/resources/img/ojk-ico/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">

    <title>Login</title>
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/font-awesome/css/font-awesome.min.css">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/AdminLTE.min.css">
    <!-- Custom style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/custom.css">
    <!-- Bootstrap Validator style -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/component/bootstrapvalidator/css/bootstrapValidator.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
    folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/component/skins/_all-skins.min.css">
    <![endif]-->
    <style type="text/css">
        html, body, .wrapper {
            height: 100% !important;
        }

        .hide {
            display: none;
            -webkit-transition: display .5s ease;
            -moz-transition: display .5s ease;
            -o-transition: display .5s ease;
        }

        .active {
            transition: display .5s ease;
            -webkit-transition: display .5s ease;
            -moz-transition: display .5s ease;
            -o-transition: display .5s ease;
        }
    </style>
</head>
<!-- ADD THE CLASS fixed TO GET A FIXED HEADER AND SIDEBAR LAYOUT -->
<!-- the fixed layout is not compatible with sidebar-mini -->
<body class="hold-transition skin-blue fixed sidebar-mini custom-body">
<!-- Site wrapper -->
<div class="wrapper" style="background-color: #ecf0f5">
    <div style="display: table;height: 100%;width: 100%">
        <div style="display: table-cell;height: 100%;width: 100%;vertical-align: middle;">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/LogoOJK.png"
                         width="200px" style="margin:0 auto;">
                    <div class="body-box" style="margin-top: 20px">
                        <div id="login-content" class="section-box clearfix">
                            <div class="box-title">
                                Login
                            </div>
                            <div class="box-content">
                                <div class="form-group">
                                    <label>User Id aaaaaaaa</label>
                                    <input placeholder="User Id" required="required" id="employeeUserName"
                                           class="form-control" type="text" name="employeeUserName" size="20" value=""
                                           autocomplete="off"
                                           maxlength="50">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input placeholder="Password" required="required" id="employeePassword"
                                           type="password" name="employeePassword" size="50"
                                           value="" class="form-control">
                                </div>
                                <%--<div class="text-left" style="display: inline-block;"><a href="javascript:forgot();">Forgot Password?</a></div>--%>
                                <div class="pull-right" style="display: inline-block;">
                                    <button id="btnSubmit" class="btn btn-primary" type="button">Login</button>
                                </div>
                            </div>
                        </div>
                        <%--<div id="forgot-content" class="section-box clearfix">--%>
                        <%--<div class="box-title">--%>
                        <%--Forgot Password--%>
                        <%--</div>--%>
                        <%--<div class="box-content">--%>
                        <%--<div class="form-group">--%>
                        <%--<label>Username</label>--%>
                        <%--<input type="text" class="form-control" name="">--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                        <%--<label>email</label>--%>
                        <%--<input type="email" class="form-control" name="">--%>
                        <%--</div>--%>
                        <%--<div class="text-left" style="display: inline-block;"><a href="javascript:login();">Log In</a></div>--%>
                        <%--<div class="pull-right" style="display: inline-block;">--%>
                        <%--<button class="btn btn-primary" type="button" >Send</button>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="preloader" style="display: none">
    <div class="loading">
    </div>
</div>
</body>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/resources/component/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/resources/component/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Bootstrap Validator -->
<script src="${pageContext.request.contextPath}/resources/component/bootstrapvalidator/js/bootstrapValidator.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/component/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/resources/component/demo.js"></script>
<!-- notify -->
<script src="${pageContext.request.contextPath}/resources/component/notify.js"></script>
<!-- Bootstrap Notify -->
<script src="${pageContext.request.contextPath}/resources/component/bootstrap-notify-3.1.3/bootstrap-notify.js"></script>

<script>
    $(document).ready(function () {
        $('#forgot-content').hide();
        $('#listform').bootstrapValidator({
            // feedbackIcons: {
            // 	valid: 'glyphicon glyphicon-ok',
            // 	invalid: 'glyphicon glyphicon-remove',
            // 	validating: 'glyphicon glyphicon-refresh'
            // },
            fields: {
                select2: {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required'
                        }
                    }
                },
                multiple: {
                    validators: {
                        notEmpty: {
                            message: 'The password is required'
                        }
                    }
                },
                startdate: {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required'
                        }
                    }
                },
                enddate: {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required'
                        }
                    }
                },
                text: {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required'
                        }
                    }
                },
                radio: {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required'
                        }
                    }
                },
                'checkbox[]': {
                    validators: {
                        notEmpty: {
                            message: 'The email address is required'
                        }
                    }
                },
            }
        });
        $('#btnSubmit').click(function () {
            Login();

        });

        $('input#employeeUserName').keyup(function (e) {
            if (e.keyCode === 13) {
                Login();
            }
        });

        $('input#employeePassword').keyup(function (e) {
            if (e.keyCode === 13) {
                Login();
            }
        });

//        $('input#company_id').keyup(function (e) {
//            if (e.keyCode === 13) {
//
//                Login();
//            }
//        });
    })

    //    function forgot(){
    //        $('#login-content').hide('slow');
    //        $('#forgot-content').show('slow');
    //    }
    //    function login(){
    //        $('#forgot-content').hide('slow');
    //        $('#login-content').show('slow');
    //    }

    function Login() {
        window.APP_PATH = "${pageContext.request.contextPath}";
        var username = $("input#employeeUserName").val();
        var password = $("input#employeePassword").val();

        if (username == "") {
            $("input#employeeUserName").focus();
            return;
        }
        if (password == "") {
            $("input#employeePassword").focus();
            return;
        }
        var data = {"employeeUserName": username, "employeePassword": password};
//        $("loginInfo").hide();
        $('#btnSubmit').attr('disabled', true);
        loadingShow();
//        $(".mini-loader").show();
        $.ajax({
            type: "POST",
            url: APP_PATH + '/login',
            data: JSON.stringify(data),
            contentType: 'application/json',
            cache: false,
            success: function (hasil) {
                if (hasil.status) {
                    window.location.href = APP_PATH + "/home/" + hasil.empId;
                    loadingHide();
                } else {
                    loadingHide();
                    danger("Mohon periksa username dan password");
                    $('#btnSubmit').removeAttr('disabled');
                }
            },
            error: function () {
                $.smallBox({
                    title: "Login Gagal",
                    content: "<i class='fa fa-clock-o'></i> Mohon Periksa Kembali Npp/Kata Sandi Anda",
                    color: "#C46A69",
                    iconSmall: "fa fa-times fa-2x fadeInRight animated",
                    timeout: 6000
                });
                $(".mini-loader").hide();
                $('#btnSubmit').removeAttr('disabled');
            },
            complete: function () {

            }
        });
    }
</script>
</body>
</html>