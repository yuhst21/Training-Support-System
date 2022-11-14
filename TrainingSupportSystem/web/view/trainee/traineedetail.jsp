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
        <script src="assets/js/confirm_trainee_update.js" type="text/javascript"></script>

        <script>

        </script>

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
                            <!-- OLD SEARCH BAR -->

                            <!-- OLD SEARCH BAR -->
                        </div>

                        <!-- USER PROFILE -->
                        <ul class="list-unstyled mb-0">
                            <jsp:include page="../profilelogo.jsp"/>
                        </ul>
                        <!-- USER PROFILE -->
                    </div>
                </div>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="d-md-flex justify-content-between">
                            <h5 class="mb-0">Edit Trainee</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="index.html">TSS</a></li>
                                    <li class="breadcrumb-item"><a href="subject?action=view">Trainee</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Edit</li>
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

                                    <form id="frm" action="traineelist" method="post" class="mt-4">
                                        <input type="hidden" name="action" value="edit"/>
                                        <input type="hidden" name="settingid" value="" />
                                        <input type="hidden" name="users_id" value="${user_class.user.user_id}">
                                        <input type="hidden" name="classes_id" value="${user_class.class_id.class_id}">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Trainee Name</label>
                                                    <input style="background-color: white" type="text" class="form-control" value="${user_class.user.full_name}" disabled="" >
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Email</label>
                                                    <input style="background-color: white" type="text" class="form-control" value="${user_class.user.email}" disabled="" >
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Class</label>                                                  
                                                    <input style="background-color: white" type="text" class="form-control" value="${user_class.class_id.class_name}" disabled="" >
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Ongoing Evaluation</label>                                                  
                                                    <input style="background-color: white" type="text" class="form-control" value="" disabled="" >
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Final Evaluation</label>                                                  
                                                    <input style="background-color: white" type="text" class="form-control" value="" disabled="" >
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Topic Evaluation</label>                                                  
                                                    <input style="background-color: white" type="text" class="form-control" value="" disabled="" >
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-8">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label> 
                                                    <input style="margin-right: 4px"
                                                           <c:if test="${user_class.status eq true}">checked="checked"</c:if>
                                                               type="radio" name="status" value="true" onclick="statusCheck()" id="studying">Studying 
                                                           <input style="margin-left: 10px; margin-right: 4px"
                                                           <c:if test="${user_class.status eq false}">checked="checked"</c:if> 
                                                               type="radio" name="status" value="false" onclick="statusCheck()" id="droppedout">Dropped Out
                                                           <div id="droppedout_check" style="visibility:hidden">
                                                               Dropped Out Date: <input id="date" type="date" name="droppedout_date" class="form-control"  value="${user_class.dropout_date}">                                                            
                                                    </div>
                                                    <div id="checkdate"></div>
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-12">
                                                <div class="mb-12">
                                                    <label class="form-label">Note</label>
                                                    <br/>
                                                    <textarea id="textid" name="note" rows="5"  style="width: 400px"  onkeydown="limit(this);" onkeyup="limit(this);"
                                                              maxlength="201"     placeholder="Note Here">${user_class.note}</textarea>

                                                </div>
                                                <div class="mb-12" id="textvalidate">

                                                </div>
                                            </div><!--end col-->
                                        </div><!--end row-->


                                    </form>
                                    <button onclick="modal_open2()" style="width: 100px ;margin-left:  70%" type="submit" class="button-10">Update</button>

                                </div>
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
