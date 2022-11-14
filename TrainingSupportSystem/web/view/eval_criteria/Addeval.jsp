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
         <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
         <script>
            
            function f1() {

                var e = document.getElementById('ass_id');
                var value = e.value;
                $.ajax({
                    url: 'load_list',
                    type: "GET",
                    data: {
                        subject_id: value
                    },
                    success: function (data) {
                        var content = document.getElementById('milestone');
                        content.innerHTML = data;

                    }
                });

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
                            <h5 class="mb-0">Add New eval</h5>

                            
                        </div>
                        <div class="row">
                            <div class="col-lg-2 mt-4"></div>
                            <div class="col-lg-8 mt-4">
                                <div class="card border-0 p-4 rounded shadow">

                                    <!--end row-->

                                    <form  action="add_eval" method="post" class="mt-4">

                                        <div class="row">
                                           
                                            
                                             
                                            <select id="ass_id" onchange="f1()" style="width:150px;height: 40px;margin-right: 100px"  name = "assignment" >

                                                <c:forEach items="${requestScope.listAssignment}" var="c">

                                                    <option  value="${c.ass_id}" > ${c.title}</option>
                                                </c:forEach>
                                            </select>
                                            <select id="milestone" style="width:150px;height: 40px;margin-right: 180px;margin-bottom: 40px" name = "milestone" >

                                                
                                            </select>
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Eval name</label>
                                                    <input
                                                       maxlength="30" required="" name="eval_name" id="name2" type="text" class="form-control" placeholder="title :" >

                                                </div>
                                            </div><!--end col-->






                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Evaluation weight</label>
                                                    <input 
                                                        name="eval_weight"  type="number" min="0" max="100" class="form-control" placeholder="evalweight :" />
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Max loc</label>
                                                    <input 
                                                        name="max_loc"  type="number" class="form-control" placeholder="max_loc :" />
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Description</label>
                                                    <input 
                                                        maxlength="30" required="" name="description"  type="text" class="form-control" placeholder="description :" />
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Team work</label>
                                                    
                                                        <br>
                                                            <input type="radio" name = "teamwork" value="1" checked=""> Active
                                                            <input type="radio" name = "teamwork" value="0" >Inactive
                                                        
                                                    
                                                </div>
                                            </div><!--end col-->

                                            <div class="col-md-6">
                                                <div class="mb-3">
                                                    <label class="form-label">Status</label>
                                                    
                                                        <br>
                                                            <input type="radio" name = "status" value="1" checked=""> Active
                                                            <input type="radio" name = "status" value="0" >Inactive
                                                        
                                                    
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