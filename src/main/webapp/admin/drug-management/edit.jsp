<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Edit Drug</title>
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
                                <h2 class= "mb-4 text-secondary">UPDATE DRUG INFORMATION</h2>

                                <form class="parsley-examples" method="post" autocomplete="off">
                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="drugName">Drug Name<span class="text-danger">*</span></label>
                                            <input type="text" name="drugName" parsley-trigger="change" required="" placeholder="Drug Name" class="form-control" id="drugName"
                                                   value="${drug.getDrugName()}">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="drugContent">Drug Content (mg)<span class="text-danger">*</span></label>
                                            <input type="number" name="drugContent" parsley-trigger="change" required="" value="${drug.getDrugContent()}" class="form-control" id="drugContent">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="quantity">Quantity<span class="text-danger">*</span></label>
                                            <input id="quantity" name="quantity" type="number" value="${drug.getQuantity()}" required="" class="form-control">
                                        </div>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="price">Price (VND)<span class="text-danger">*</span></label>
                                            <input type="number" name="price" placeholder="" required=""
                                                   value="${drug.getPricePerPill()}" class="form-control" id="price">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="dosageForm">Dosage Form</label>
                                            <select class="form-control" data-toggle="select2" id="dosageForm" name="dosageForm">
                                                <option disabled ${drug == null ? "selected" : ""}>- Choose drug dosage form -</option>
                                                <c:forEach var="dosageForm" items="${dosageFormList}">
                                                    <option value="${dosageForm.getId()}" ${dosageForm.getId() == drug.getDosageForm() ? "selected" : ""}>${dosageForm.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="usage">Usage</label>
                                            <input type="text" name="usage" placeholder="Usage" class="form-control" id="usage" value="${drug.getUsage()}">
                                        </div>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label>Production Date<span class="text-danger">*</span></label>
                                            <div>
                                                <div class="input-group">
                                                    <input required="" type="text" name="productionDate" class="form-control"
                                                           placeholder="mm/dd/yyyy" data-provide="datepicker" data-date-autoclose="true"
                                                           value="${productionDate}">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text"><i class="mdi mdi-calendar"></i></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label>Expiration Date<span class="text-danger">*</span></label>
                                            <div>
                                                <div class="input-group">
                                                    <input required="" type="text" name="expirationDate" class="form-control"
                                                           placeholder="mm/dd/yyyy" data-provide="datepicker" data-date-autoclose="true"
                                                           value="${expirationDate}">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text"><i class="mdi mdi-calendar"></i></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="note">Note</label>
                                            <input type="text" name="note" placeholder="Note" class="form-control" id="note" value="${drug.getNote()}">
                                        </div>
                                    </div>

                                    <div class="form-group text-right mb-0">
                                        <button class="btn btn-secondary waves-effect waves-light mr-1" type="submit">
                                            Update
                                        </button>
                                        <a href="/drugs" class="btn btn-dark waves-effect waves-light">
                                            <span>Cancel</span>
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/layout/footer.jsp" %>

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
                    timeout: 7000,
                    progressBar: false,
                    position: "topRight"
                });
            </script>
        </c:if>

    </div>

</div>
<!-- END wrapper -->


<!-- Right Sidebar -->
<%--<%@ include file="/layout/sidebar-right.jsp" %>--%>
<!-- /Right-bar -->

<!-- Right bar overlay-->
<%--<div class="rightbar-overlay"></div>--%>

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