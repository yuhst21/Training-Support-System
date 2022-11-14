<%-- 
    Document   : subject_list
    Created on : Sep 21, 2022, 10:53:52 PM
    Author     : HAICAO
--%>
<a href="nbproject/project.xml"></a>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
                            <h5 class="mb-0">Update Assignment Information</h5>

                           
                        </div>
                        <div class="row">
                            <div class="col-lg-2 mt-4"></div>
                            <div class="col-lg-8 mt-4">
                                <div class="card border-0 p-4 rounded shadow">
                                    
                                    <!--end row-->

                                    <form  action="edit_ass" method="post" class="mt-4">
                                        <input type="hidden" name="id" value="${id}"/>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Subject code:</label>
                                                    <br>
                                                    <input
                                                        required="" readonly="" name="subject" id="name2" type="text" class="form-control"  value="${subject}">
                                                    
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Title</label>
                                                    <input
                                                        required="" name="title" maxlength="25" id="name2" type="text" class="form-control" placeholder="title :" value="${title}">
                                                    
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Assignment body</label>
                                                    <textarea style="height: 100px" maxlength="40" name="ass_body" required="" id="name" type="text" class="form-control"  placeholder="Assignment Name :">${assbody}</textarea>
                                                    
                                                </div>
                                            </div><!--end col-->

                                            
                                            

                                            

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Evaluation weight</label>
                                                    <input 
                                                        name="evalweight"  type="number" min="0" max="100" class="form-control" placeholder="evalweight :" value="${evalweight}"/>
                                                </div>
                                            </div><!--end col-->
                                             <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Team work</label>
                                                    <br>
                                                        
                                                    <input type="radio" name = "teamwork" <c:if test="${teamwork eq true}">
                                                                        checked=""
                                                                    </c:if> value="true" /> Yes
                                                            <input type="radio" name = "teamwork" <c:if test="${teamwork eq false}">
                                                                        checked=""
                                                                    </c:if>  value="false" /> No
                                                        
                                                    
                                                </div>
                                            </div><!--end col-->
                                             <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">On-going</label>
                                                    <br>
                                                        
                                                            <input type="radio" name = "ongoing" <c:if test="${ongoing eq true}">
                                                                        checked=""
                                                                    </c:if> value="true" /> Yes
                                                            <input type="radio" name = "ongoing" <c:if test="${ongoing eq false}">
                                                                        checked=""
                                                                    </c:if>  value="false" /> No
                                                        
                                                    
                                                </div>
                                            </div><!--end col-->
                                             <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>
                                                    <br>
                                                        
                                                            <input type="radio" name = "status" <c:if test="${status == true}">
                                                                        checked=""
                                                                    </c:if> value="true" > Active</option>
                                                            <input type="radio" name = "status" <c:if test="${status == false}">
                                                                        checked=""
                                                                    </c:if>  value="false" > Inactive</option>
                                                        
                                                    
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