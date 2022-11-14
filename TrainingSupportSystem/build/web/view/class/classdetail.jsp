<%-- 
    Document   : settinglist
    Created on : Sep 25, 2022, 2:43:47 PM
    Author     : win
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>TSS - Trainng Support System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/images/logo.png">
        <!-- Bootstrap -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="assets/css/flatpickr.min.css">
        <!-- Icons -->
        <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <link href="assets/css/newcss.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/modal.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/custom_attend.css" rel="stylesheet" type="text/css"/>
        <script src="assets/js/confirm_class_update.js" type="text/javascript"></script>


    </head>

    <body>
        <div id="demo-modal" class="modal">
            <div class="modal__content" id="model_content">
                <h2>Confirm</h2>
                <p>
                    Do you want to update!
                </p>
                <div class="modal__footer">
                    <button type="button" onclick="modal_close()" class="button-10">Close</button>
                    <button type="button" onclick="modal_change()" class="button-10">Confirm</button>
                </div>
                <a href="#" class="modal__close">&times;</a>
            </div>
        </div>
        <div id="demo-modal2" class="modal">
            <div class="modal__content" id="model_content">
                <h2></h2>
                <p>Update successfully!</p>
                <div class="modal__footer">
                    <button type="button" onclick="modal_close2()" class="button-10">Close</button>
                </div>
                <a href="#" class="modal__close">&times;</a>
            </div>
        </div>
        <!-- Loader -->
        <div id="preloader">
            <div id="status">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
            </div>
        </div>
        <!-- Loader -->

        <div class="page-wrapper doctris-theme toggled">
            <!-- sidebar-wrapper  -->
            <jsp:include page="../navbar.jsp"/>
            <!-- sidebar-wrapper  -->

            <!-- Start Page Content -->
            <main class="page-content bg-light">
                <div class="top-header">
                    <div class="header-bar d-flex justify-content-between border-bottom">
                        <div class="d-flex align-items-center">
                            <a href="#" class="logo-icon">
                                <img src="assets/images/logo-icon.png" height="30" class="small" alt="">
                                <span class="big">
                                    <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                                    <img src="assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                                </span>
                            </a>
                            <a id="close-sidebar" class="btn btn-icon btn-pills btn-soft-primary ms-2" href="#">
                                <i class="uil uil-bars"></i>
                            </a>
                        </div>

                        <ul class="list-unstyled mb-0">
                            <jsp:include page="../profilelogo.jsp"/>
                        </ul>
                    </div>
                </div>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Edit Class</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="index.html">TSS</a></li>
                                    <li class="breadcrumb-item"><a href="classlist?action=view">Class</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Update</li>
                                </ul>
                            </nav>
                        </div>
                        <div class="row">
                            <div class="col-lg-2 mt-4"></div>
                            <div class="col-lg-8 mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <div class="row align-items-center">                                                         
                                        <div class="col-lg-5 col-md-12 text-lg-end text-center mt-4 mt-lg-0">

                                        </div><!--end col-->
                                    </div>
                                    <!--end row-->

                                    <form id="frm" action="classlist" method="post" class="mt-4">
                                        <input type="hidden" name="action" value="edit"/>
                                        <input type="hidden" name="class_id" value="${classset.class_id}" />
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Class Name</label>
                                                    <input name="class_name" required="" id="name" type="text" class="form-control" value="${classset.class_name}" >
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Subject Code</label>
                                                    <br/>
                                                    <select name="sub_code" class="form-select">
                                                        <c:forEach items="${sessionScope.list_sub}" var="s">
                                                            <option <c:if test="${s.subject_id == classset.subject.subject_id}">selected=""</c:if>
                                                                value="${s.subject_id}">${s.subject_code}</option>
                                                        </c:forEach>  
                                                    </select>

                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Supporter</label>
                                                    <br/>
                                                    <select name="support_get" class="form-select">
                                                        <c:forEach items="${sessionScope.supporter}" var="s">
                                                            <option <c:if test="${s.user_id == classset.supporter_id}">selected=""</c:if>
                                                                                                                       value="${s.user_id}">${s.full_name}</option>
                                                        </c:forEach>  
                                                    </select>
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Trainer</label>
                                                    <br/>
                                                    <select name="trainer_get" class="form-select">
                                                        <c:forEach items="${sessionScope.trainer}" var="s">
                                                            <option <c:if test="${s.user_id == classset.trainer_id}">selected=""</c:if>
                                                                                                                     value="${s.user_id}">${s.full_name}</option>
                                                        </c:forEach> 
                                                    </select>

                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Term</label>  
                                                    <br/>
                                                    <select name="term_get" class="form-select">
                                                        <c:forEach items="${requestScope.term_type}" var="s">
                                                            <option  <c:if test="${s.term_id == classset.term_id}">selected=""</c:if>
                                                                value="${s.term_id}">${s.term_name}</option>
                                                        </c:forEach>  
                                                    </select>
                                                </div>
                                            </div><!--end col-->                                
                                            <div class="col-md-8">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label> 
                                                    <input style="margin-right: 4px"
                                                           <c:if test="${classset.status eq true}">checked=""</c:if>
                                                               type="radio" name="status" value="1">Active 
                                                           <input style="margin-left: 10px; margin-right: 4px"
                                                           <c:if test="${classset.status eq false}">checked=""</c:if> 
                                                               type="radio" name="status" value="0">Inactive
                                                    </div>
                                                </div><!--end col-->

                                                <div class="col-md-12">
                                                    <div class="mb-12">
                                                        <label class="form-label">Description</label>
                                                        <br/>
                                                        <textarea id="textid" name="description" rows="5"  style="width: 400px" 
                                                                  onkeydown="limit(this);" onkeyup="limit(this);"
                                                                  maxlength="201" >${classset.description}</textarea>
                                                </div>
                                                <div class="mb-12" id="textvalidate">
                                                </div><!--end col-->
                                            </div><!--end row-->
                                        </div>
                                    </form>
                                    <button onclick="modal_open2()" style="width: 100px ;margin-left:  70%" type="submit" class="button-10">Update</button>
                                </div><!--end col-->

                            </div><!--end row-->
                        </div>
                    </div><!--end container-->

                    <!-- Footer Start -->
                    <footer class="bg-white shadow py-3">
                        <div class="container-fluid">
                            <div class="row align-items-center">
                                <div class="col">
                                    <div class="text-sm-start text-center">
                                        <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear());</script> © TSS. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
                                    </div>
                                </div><!--end col-->
                            </div><!--end row-->
                        </div><!--end container-->
                    </footer><!--end footer-->
                    <!-- End -->
            </main>
            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->

        <!-- Offcanvas Start -->

        <!-- Offcanvas End -->

        <!-- javascript -->

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- simplebar -->
        <script src="assets/js/simplebar.min.js"></script>
        <!-- Select2 -->
        <script src="assets/js/select2.min.js"></script>
        <script src="assets/js/select2.init.js"></script>
        <!-- Datepicker -->
        <script src="assets/js/flatpickr.min.js"></script>
        <script src="assets/js/flatpickr.init.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/app.js"></script>
    </body>

</html>
