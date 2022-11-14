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
            function limit(element,code)
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
                            <h5 class="mb-0">Add Subject </h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="index.html">TSS</a></li>
                                    <li class="breadcrumb-item"><a href="subject?action=view">Subject</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Edit</li>
                                </ul>
                            </nav>
                        </div>
                        <div class="row">
                            <div class="col-lg-2 mt-4"></div>
                            <div class="col-lg-8 mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    <div class="row align-items-center">
                                        <div class="col-lg-2 col-md-4">
                                            <img src="images/${add_img_sub}" class="avatar avatar-md-md rounded-pill shadow mx-auto d-block" alt="">
                                        </div><!--end col-->

                                        <div class="col-lg-5 col-md-8 text-center text-md-start mt-4 mt-sm-0">
                                            <h5 class="">Upload subject picture</h5>
                                            <p class="text-muted mb-0">For best results, use an image at least 600px by 600px in either .jpg or .png format</p>
                                        </div><!--end col-->

                                        <div class="col-lg-5 col-md-12 text-lg-end text-center mt-4 mt-lg-0">
                                            <form action="subject" enctype="multipart/form-data" method="post">
                                                <input type="hidden" name="action" value="upload_add">
                                                <div 
                                                    <p style="color:red;">${mess}</p>
                                                    <label for="file-upload" class="btn btn-primary">
                                                        Upload
                                                    </label>
                                                    <input id="file-upload" type="file" name="image" style="display: none;">
                                                    <input type="submit" id="submit" value="Update" class="btn btn-soft-primary ms-2">
                                                </div>
                                            </form>
                                        </div><!--end col-->
                                    </div>
                                    <!--end row-->

                                    <form action="subject" method="post" class="mt-4">
                                        <input type="hidden" name="action" value="add"/>
                                        <input type="hidden" name="add_img_sub" value="${add_img_sub}" />
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Subject Name*</label>
                                                    <input name="add_name_sub" required="" id="name" type="text" maxlength="45" onkeydown="limit(this,'dit');" onkeyup="limit(this,'dit');" class="form-control" value="${add_name_sub}" placeholder="Subject Name :">
                                                </div>
                                                <div class="mb-3"  id="dit">
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Subject Code*</label>
                                                    <input
                                                        required="" name="add_code_sub" id="name2" type="text" maxlength="45" onkeydown="limit(this,'dit2');" onkeyup="limit(this,'dit2');" class="form-control" placeholder="Subject Code :" value="${add_code_sub}">
                                                    <p style="color:red;">${code_mess}</p>
                                                </div>
                                                <div class="mb-3"  id="dit2">
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Manager</label>
                                                    <select class="form-control department-name select2input" name="add_manager_sub">
                                                        <c:forEach items="${managers}" var="m">
                                                            <option 
                                                                <c:if test="${m.user_id == manager}">
                                                                    selected=""
                                                                </c:if>
                                                                value="${m.user_id}">${m.full_name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Expert</label>
                                                    <select class="form-control department-name select2input" name="add_expert_sub">
                                                        <c:forEach items="${experts}" var="e">
                                                            <option 
                                                                <c:if test="${e.user_id == expert}">
                                                                    selected=""
                                                                </c:if>
                                                                value="${e.user_id}">${e.full_name}</option>
                                                        </c:forEach>
                                                    </select>

                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Description</label>
                                                    <textarea 
                                                        name="add_body_sub" id="comments"  rows="4" class="form-control border rounded" placeholder="Description :">${add_body_sub}</textarea>
                                                </div>
                                            </div><!--end col-->
                                        </div><!--end row-->

                                        <button type="submit" class="btn btn-primary">Update</button>
                                        <p style="color:#4BB543;">${requestScope.success_mess}</p>
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
                                    <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear())</script> © TSS. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
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