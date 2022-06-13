<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Register</title>
    <%@ include file="/layout/header-p1.jsp" %>
    <!-- App favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico">

    <!-- Notification css (Toast) -->
    <link href="/assets/libs/toastr/css/iziToast.min.css" rel="stylesheet" type="text/css">
    <%--    <link href="/assets/libs/toastr/toastr.min.css" rel="stylesheet" type="text/css">--%>

    <!-- Plugins css -->
    <link href="/assets/libs/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">
    <link href="/assets/libs/switchery/switchery.min.css" rel="stylesheet" type="text/css">

    <link href="/assets/libs/select2/select2.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/bootstrap-touchspin/jquery.bootstrap-touchspin.min.css" rel="stylesheet">
    <link href="/assets/libs/bootstrap-timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
    <link href="/assets/libs/bootstrap-colorpicker/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="/assets/libs/bootstrap-datepicker/bootstrap-datepicker.css" rel="stylesheet">

    <script src="/assets/libs/toastr/js/iziToast.min.js"></script>

    <%@ include file="/layout/header-p2.jsp" %>
</head>

<body>

<!-- Begin page -->
<div id="wrapper">

    <!-- Topbar Start -->
    <%@ include file="/layout/topbar.jsp" %>
    <!-- end Topbar --> <!-- ========== Left Sidebar Start ========== -->

    <div class="left-side-menu">

        <div class="slimscroll-menu">

            <!--- Sidemenu -->
            <div id="sidebar-menu">
                <%@ include file="/layout/sidebar-left.jsp" %>
            </div>
            <!-- End Sidebar -->

            <div class="clearfix"></div>

        </div>
        <!-- Sidebar -left -->

    </div>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <h2 class="mb-4 text-info">Register Form</h2>

                                <form class="parsley-examples" method="post" autocomplete="off">

                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="fullName">Full Name <span class="text-danger">*</span></label>
                                            <input type="text" name="fullName" parsley-trigger="change" required="" placeholder="full name" class="form-control" id="fullName"
                                                   value="${user.getFullName()}">
                                            <span class="font-15 text-muted mt-3">Example: Nguyen Van Nam</span>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="gender">Gender <span class="text-danger">*</span></label>
                                            <select class="form-control" data-toggle="select2" id="gender" name="gender">
                                                <option disabled ${user == null ? "selected" : ""}>- Choose gender -</option>
                                                <c:forEach var="gender" items="${genders}">
                                                    <option value="${gender.getId()}" ${gender.getId() == user.getGender() ? "selected" : ""}>${gender.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="dateOfBirth">Date of Birth <span class="text-danger">*</span></label>
                                                <input class="form-control" id="dateOfBirth" type="date" name="dateOfBirth" required=""
                                                       value="${dateOfBirth}">
                                        </div>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="phoneNumber">Phone Number <span class="text-danger">*</span></label>
                                            <input type="text" name="phoneNumber" placeholder="phone number" required=""
                                                   value="${user.getPhoneNumber()}" class="form-control" id="phoneNumber">
                                            <span class="font-15 text-muted">Example: 0783465748</span>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="email">Email <span class="text-danger">*</span></label>
                                            <input type="text" name="email" placeholder="email address" required=""
                                                   value="${user.getEmail()}" class="form-control" id="email">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="address">Address</label>
                                            <input type="text" name="address" placeholder="address"
                                                   value="${user.getAddress()}" class="form-control" id="address">
                                        </div>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="form-group col-md-7">
                                            <label for="username" class="col-md-4 col-form-label">Username <span class="text-danger">*</span></label>
                                            <input type="text" required="" class="form-control" id="username" placeholder="username"
                                                       value="${user.getUsername()}" name="username">
                                            <span class="font-15 text-danger">Note: Username must start with A LETTER, other characters can be alphabets, numbers or an underscore and length constraint is from 8 to 20 characters</span>
                                        </div>
                                        <div class="form-group mt-3 col-md-7">
                                            <label for="hori-pass1" class="col-md-4 col-form-label">Password <span class="text-danger">*</span></label>
                                            <input id="hori-pass1" type="password" placeholder="Password" required="" class="form-control"
                                                       value="${user.getPassword()}">
                                            <span class="font-15 text-danger">Note: Minimum eight characters, at least one letter, one number and one special character @$!%*#?&</span>
                                        </div>
                                        <div class="form-group mt-3 col-md-7">
                                            <label for="hori-pass2" class="col-md-4 col-form-label">Confirm Password <span class="text-danger">*</span></label>
                                            <input data-parsley-equalto="#hori-pass1" type="password" required="" placeholder="Password" class="form-control" id="hori-pass2"
                                                       value="${user.getPassword()}" name="password">
                                        </div>
                                    </div>

                                    <div class="form-group text-right mb-0">
                                        <button class="btn btn-info waves-effect waves-light mr-1" type="submit">
                                            Submit
                                        </button>
                                        <button class="btn btn-secondary waves-effect waves-light" type="reset">
                                            Reset
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/layout/footer.jsp" %>

        <c:if test="${errors != null}">
            <c:forEach var="error" items="${errors}">
                <script>
                    iziToast.error({
                        title: 'Error',
                        message: "${error.getMessage()}",
                        timeout: false,
                        progressBar: false,
                        position: "topRight"
                    });
                </script>
            </c:forEach>
        </c:if>
        <c:if test="${invalidInput != null}">
            <c:forEach var="error" items="${invalidInput}">
                <script>
                    iziToast.error({
                        title: 'Error',
                        message: '${error}',
                        timeout: false,
                        progressBar: false,
                        position: "topRight"
                    });
                </script>
            </c:forEach>
        </c:if>
        <c:if test="${successfully != null}">
            <script>
                iziToast.success({
                    title: 'Done',
                    message: '${successfully}'
                });
            </script>
        </c:if>
        <c:if test="${failed != null}">
            <script>
                iziToast.error({
                    title: 'Hey',
                    message: '${failed}',
                    timeout: 10000,
                    progressBar: false,
                    position: "topRight"
                });
            </script>
        </c:if>
    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->

<!-- Vendor js -->
<script src="/assets/js/vendor.min.js"></script>

<!-- Toast js -->

<%--<script src="/assets/libs/toastr/toastr.min.js"></script>--%>

<%--<script src="/assets/js/pages/toastr.init.js"></script>--%>

<!-- Plugin js-->
<script src="/assets/libs/parsleyjs/parsley.min.js"></script>

<script src="/assets/libs/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
<script src="/assets/libs/switchery/switchery.min.js"></script>

<script src="/assets/libs/select2/select2.min.js"></script>
<script src="/assets/libs/bootstrap-touchspin/jquery.bootstrap-touchspin.min.js"></script>
<script src="/assets/libs/jquery-mask-plugin/jquery.mask.min.js"></script>
<script src="/assets/libs/moment/moment.min.js"></script>
<script src="/assets/libs/bootstrap-timepicker/bootstrap-timepicker.min.js"></script>
<script src="/assets/libs/bootstrap-colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="/assets/libs/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>

<!-- Validation init js-->
<script src="/assets/js/pages/form-validation.init.js"></script>

<!-- Init js-->
<script src="/assets/js/pages/form-advanced.init.js"></script>

<!-- App js -->
<script src="/assets/js/app.min.js"></script>
</body>
</html>