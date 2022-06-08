<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Add New Drug</title>
    <%@ include file="/layout/header-p1.jsp" %>
    <!-- App favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico">

    <!-- Plugins css -->
    <link href="assets\libs\bootstrap-tagsinput\bootstrap-tagsinput.css" rel="stylesheet">
    <link href="assets\libs\switchery\switchery.min.css" rel="stylesheet" type="text/css">

    <link href="assets\libs\select2\select2.min.css" rel="stylesheet" type="text/css">
    <link href="assets\libs\bootstrap-touchspin\jquery.bootstrap-touchspin.min.css" rel="stylesheet">
    <link href="assets\libs\bootstrap-timepicker\bootstrap-timepicker.min.css" rel="stylesheet">
    <link href="assets\libs\bootstrap-colorpicker\bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="assets\libs\bootstrap-datepicker\bootstrap-datepicker.css" rel="stylesheet">
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
                                <h2 class= "mb-4">DRUG INFORMATION</h2>

                                <form class="parsley-examples" method="post" autocomplete="off">
                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="drugName">Drug Name<span class="text-danger">*</span></label>
                                            <input type="text" name="drugName" parsley-trigger="change" required="" placeholder="Drug Name" class="form-control" id="drugName">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="drugContent">Drug Content (mg)<span class="text-danger">*</span></label>
                                            <input type="number" name="drugContent" parsley-trigger="change" required="" value="0" class="form-control" id="drugContent">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="quantity">Quantity<span class="text-danger">*</span></label>
                                            <input id="quantity" name="quantity" type="number" value="0" required="" class="form-control">
                                        </div>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
<%--                                            <label for="price">Price<span class="text-danger">*</span></label>--%>
<%--                                            <input type="number" name="price" required="" value="0" class="form-control" id="price">--%>

                                            <label for="price">Price<span class="text-danger">*</span></label>
                                            <input type="text" name="price" placeholder="" required="" data-mask="999,999,999.99"
                                                   value="0" class="form-control" id="price">
                                        </div>

                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
<%--                                            <input type="password" name="dosageForm" required="" class="form-control" id="dosageForm">--%>
                                            <label for="dosageForm">Dosage Form</label>
                                            <select class="form-control" data-toggle="select2" id="dosageForm" name="dosageForm">
                                                <c:forEach var="dosageForm" items="${dosageFormList}">
                                                    <option value="${dosageForm.getId()}" ${dosageForm.getId() == 1 ? "selected" : ""}>${dosageForm.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="usage">Usage</label>
                                            <input type="text" name="usage" placeholder="Usage" class="form-control" id="usage">
                                            <span class="font-14 text-muted mt-5">Example: Headache, Stomach ache</span>
                                        </div>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label>Production Date</label>
                                            <div>
                                                <div class="input-group">
                                                    <input type="text" name="productionDate" class="form-control" placeholder="yyyy-mm-dd" data-provide="datepicker" data-date-autoclose="true">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text"><i class="mdi mdi-calendar"></i></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label>Expiration Date</label>
                                            <div>
                                                <div class="input-group">
                                                    <input type="text" name="expirationDate" class="form-control" placeholder="yyyy-mm-dd" data-provide="datepicker" data-date-autoclose="true">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text"><i class="mdi mdi-calendar"></i></span>
                                                    </div>
                                                </div>
                                            </div></div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="note">Note</label>
                                            <input type="text" name="note" placeholder="Note" class="form-control" id="note">
                                        </div>
                                    </div>

                                    <div class="form-group text-right mb-0">
                                        <button class="btn btn-primary waves-effect waves-light mr-1" type="submit">
                                            Add
                                        </button>
                                        <button type="reset" class="btn btn-secondary waves-effect waves-light">
                                            Cancel
                                        </button>
                                    </div>

                                </form>
                            </div>
                        </div>
                        <!-- end card -->
                    </div>
                    <!-- end col -->

                    <!-- end col -->
                </div>

            </div>
            <!-- end container-fluid -->
        </div>
        <!-- end content -->

        <!-- Footer Start -->
        <%@ include file="/layout/footer.jsp" %>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->


<!-- Right Sidebar -->
<%@ include file="/layout/sidebar-right.jsp" %>
<!-- /Right-bar -->

<!-- Right bar overlay-->
<div class="rightbar-overlay"></div>

<!-- Vendor js -->
<script src="/assets/js/vendor.min.js"></script>

<!-- Plugin js-->
<script src="/assets/libs/parsleyjs/parsley.min.js"></script>
<script src="assets\libs\bootstrap-tagsinput\bootstrap-tagsinput.min.js"></script>
<script src="assets\libs\switchery\switchery.min.js"></script>
<script src="assets\libs\select2\select2.min.js"></script>
<script src="assets\libs\bootstrap-touchspin\jquery.bootstrap-touchspin.min.js"></script>
<script src="assets\libs\jquery-mask-plugin\jquery.mask.min.js"></script>
<script src="assets\libs\moment\moment.min.js"></script>
<script src="assets\libs\bootstrap-timepicker\bootstrap-timepicker.min.js"></script>
<script src="assets\libs\bootstrap-colorpicker\bootstrap-colorpicker.min.js"></script>
<script src="assets\libs\bootstrap-datepicker\bootstrap-datepicker.min.js"></script>

<!-- Validation init js-->
<script src="/assets/js/pages/form-validation.init.js"></script>

<!-- Init js-->
<script src="assets\js\pages\form-advanced.init.js"></script>

<!-- App js -->
<script src="/assets/js/app.min.js"></script>
</body>
</html>