<%-- 
    Document   : subject_list
    Created on : Sep 21, 2022, 10:53:52 PM
    Author     : HAICAO
--%>
<a href="nbproject/project.xml"></a>
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
        <script>
            function limit(element, code)
            {
                var max_chars = 45;
                var content = document.getElementById(code);

                if (element.value.length >= max_chars) {
                    var content = document.getElementById(code);
                    content.innerHTML = "<p id=\"noti\" style=\"color: red\">Input can't exceed 45 characters</p>";
                }
                if (element.value.length < max_chars) {
                    var content = document.getElementById(code);
                    content.innerHTML = "";
                }
            }
        </script>
    </head>

    <body>
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
                            <h5 class="mb-0">Update Subject Setting Information</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="homepage">TSS</a></li>
                                    <li class="breadcrumb-item"><a href="subject?action=view">Subject</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Edit</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-lg-2 mt-4"></div>
                            <div class="col-lg-8 mt-4">
                                <div class="card border-0 p-4 rounded shadow">

                                    <form action="subject_setting" method="post" class="mt-4">
                                        <input type="hidden" name="action" value="edit"/>
                                        <div class="row">
                                            <input type="hidden" name="id" value="${set.id}">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Subject Name</label>
                                                    <input readonly="" id="name" type="text" class="form-control" value="${set.subject.subject_name}">
                                                    <input type="hidden" name="subject" value="${set.subject.subject_id}"/>
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Setting Type</label>
                                                    <input required="" readonly="" id="name2" type="text" maxlength="45" onkeydown="limit(this, 'lvalue');" onkeyup="limit(this, 'lvalue');" class="form-control" placeholder="Value :" value="${set.type.setting_tiltle}">
                                                </div>
                                                <div class="mb-3"  id="lvalue">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Setting Title*</label>
                                                    <input name="title" required="" id="name" type="text" maxlength="45" onkeydown="limit(this, 'ltitle');" onkeyup="limit(this, 'ltitle');" class="form-control" value="${set.setting_title}">
                                                </div>
                                                <div class="mb-3"  id="ltitle">
                                                </div>
                                            </div><!--end col--> 
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Value*</label>
                                                    <input required="" name="value" id="name2" type="text" maxlength="45" onkeydown="limit(this, 'lvalue');" onkeyup="limit(this, 'lvalue');" class="form-control" placeholder="Value :" value="${set.setting_value}">
                                                </div>
                                                <div class="mb-3"  id="lvalue">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Display Order*</label>
                                                    <input required="" name="order" id="name2" type="text" maxlength="45" onkeydown="limit(this, 'lorder');" onkeyup="limit(this, 'lorder');" class="form-control" placeholder="Value :" value="${set.display_order}">
                                                    <p style="color:red">${sessionScope.num_mess}</p>
                                                </div>
                                                <div class="mb-3"  id="lorder">
                                                </div>
                                            </div>    
                                            <div class="col-md-7">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label> <br/>
                                                    <input style="margin-right: 4px"
                                                           <c:if test="${set.status eq true}">checked=""</c:if>
                                                               type="radio" name="status" value="active">Active 
                                                           <input style="margin-left: 10px; margin-right: 4px"
                                                           <c:if test="${set.status eq false}">checked=""</c:if> 
                                                               type="radio" name="status" value="deactive">Inactive
                                                    </div>
                                                </div>

                                                <div class="col-md-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Description</label>
                                                        <textarea name="body" id="comments"  rows="4" class="form-control border rounded" placeholder="Description :">${set.description}</textarea>
                                                </div>
                                            </div><!--end col-->
                                        </div><!--end row-->

                                        <button type="submit" class="btn btn-primary">Update</button>
                                        <p style="color:#4BB543;">${success_mess}</p>
                                        <p style="color:red;">${fail_mess}</p>
                                    </form>
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
                                    <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear())</script> © Doctris. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
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