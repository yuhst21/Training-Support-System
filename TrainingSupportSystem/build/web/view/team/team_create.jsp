<%-- 
    Document   : subject_list
    Created on : Sep 21, 2022, 10:53:52 PM
    Author     : HAICAO
--%>
<a href="nbproject/project.xml"></a>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>TSS - Training Support System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Premium Bootstrap 4 Landing Page Template" />
        <meta name="keywords" content="Appointment, Booking, System, Dashboard, Health" />
        <meta name="author" content="Shreethemes" />
        <meta name="email" content="support@shreethemes.in" />
        <meta name="website" content="../../index.html" />
        <meta name="Version" content="v1.2.0" />
        <!-- favicon -->
        <link rel="shortcut icon" href="assets/images/logo.png">
        <!-- Bootstrap -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <link href="assets/css/newcss.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/sortcolumn.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/toggle_switch.css" rel="stylesheet" type="text/css"/>

        <!-- JS -->


        <script src="assets/js/confirm_subject.js" type="text/javascript"></script>
        <script src="assets/js/pagger_subject.js" type="text/javascript"></script>
        <style>
            .filter123{
                margin-right: 50px
            }

            .pagination{
                color: black;
                font-size: 50px;
                float: left;
                padding: 20px 30px;
                margin: 20px;
                text-decoration: none;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }
            .pagination a:hover:not(.active){
                background-color: chocolate;
            }
            .topnav{
                overflow: hidden;
                background-color: #e9e9e9;
            }
            .topnav input[type = text]{
                margin-left: 100px;
                font-size: 20px;
            }

        </style>
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

                        <div class="container-fluid" style="width:1000px">
                    <div class="layout-specing">
                                                                                    
                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive shadow rounded">                                   
                                    <div class="card border-0 rounded-0 p-4">
                                        <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded shadow overflow-hidden bg-light" id="pills-tab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link rounded-0 active" id="overview-tab" data-bs-toggle="pill" href="#pills-overview" role="tab" aria-controls="pills-overview" aria-selected="false">
                                                    <div class="text-center pt-1 pb-1">
                                                        <h4 class="title fw-normal mb-0">Random</h4>
                                                    </div>
                                                </a><!--end nav link-->
                                            </li><!--end nav item-->
                                            
                                            <li class="nav-item">
                                                <a class="nav-link rounded-0" id="experience-tab" data-bs-toggle="pill" href="#pills-experience" role="tab" aria-controls="pills-experience" aria-selected="false">
                                                    <div class="text-center pt-1 pb-1">
                                                        <h4 class="title fw-normal mb-0">Upload Team</h4>
                                                    </div>
                                                </a><!--end nav link-->
                                            </li><!--end nav item-->
                                            
                                            <li class="nav-item">
                                                <a class="nav-link rounded-0" id="review-tab" data-bs-toggle="pill" href="#pills-review" role="tab" aria-controls="pills-review" aria-selected="false">
                                                    <div class="text-center pt-1 pb-1">
                                                        <h4 class="title fw-normal mb-0">Reuse group</h4>
                                                    </div>
                                                </a><!--end nav link-->
                                            </li><!--end nav item-->
                                            
                                           
                                        </ul><!--end nav pills-->
            
                                        <div class="tab-content mt-2" id="pills-tabContent" style="text-align:center">
                                            <div class="tab-pane fade show active" id="pills-overview" role="tabpanel" aria-labelledby="overview-tab">
                                                <div style="margin-bottom:20px">How many group you want to create: </div>
                                                <form action="create_team" method="get">  
                                                    <input type = hidden name="class" value="${requestScope.class_id}">
                                                    <input type = hidden name="milestone" value="${requestScope.milestone}">
                                                    <input type="text" style="width:200px;margin-left: 350px;border-color: black;margin-bottom:20px"  class="form-control"  name="group" required="" />
                                                    <button  class="btn btn-primary" type="submit">Random</button>
                                                </form>
                                                
            
                                                
            
                                                
            
                                                
                                            </div><!--end teb pane-->
                                            
                                            <div class="tab-pane fade" id="pills-experience" role="tabpanel" aria-labelledby="experience-tab">
                                                <div><a href="view/team/report.jsp">Generate report</a></div>
                                                <form method="post" action="list_team" enctype="multipart/form-data">                                 
                                                    <br/>
                                                    <input type = hidden name="milestone" value="${requestScope.milestone}">
                                                    <input type = hidden name="class" value="${requestScope.class_id}">
                                                    <input type="file" name="file" size="60" /><br /><br /> 
                                                    <input class="btn btn-primary" type="submit" value="Upload" />
                                                </form>
                                                
                                            </div><!--end teb pane-->
                
                                            <div class="tab-pane fade" id="pills-review" role="tabpanel" aria-labelledby="review-tab">
                                                <div >Reuse group<div>
                                                        <br/>
                                                        
                                                <form action="reuse" method="get">
                                                    <input type = hidden name="class" value="${requestScope.class_id}">
                                                    <input type = hidden name="milestone" value="${requestScope.milestone}">
                                                <select  style="height:35px;width: 150px;text-align: center;border-radius: 10px;margin-bottom: 20px"   name="newmilestone">

                                                    <c:forEach items="${requestScope.listMilestone}" var="c">

                                                        <option value="${c.milestone_id}"> ${c.tittle}</option>
                                                    </c:forEach>
                                                </select>
                                                    
                                                    <br/>
                                                    <button class="btn btn-primary" type="submit">OK</button>
                                                </form>
                                                
                                            </div><!--end teb pane-->
                
                                            
                
                                            
                                        </div><!--end tab content-->
                                    </div>
                                </div><!--end col-->
                            </div>










                                </div>
                            </div>
                        </div>
                    </div><!--end row-->



                    <div class="row text-center">
                        <!-- PAGINATION START -->
                        <div class="col-12 mt-4">
                            <div class="d-md-flex align-items-center text-center justify-content-between">
                                <ul id="only-pagger" class="pagination justify-content-center mb-0 mt-3 mt-sm-0">

                                </ul>
                            </div>
                        </div><!--end col-->
                        <!-- PAGINATION END -->
                    </div><!--end row-->

                </div>
        </div><!--end container-->


    </main>
    <!--End page-content" -->
</div>
<!-- page-wrapper -->


<!-- javascript -->
<script src="assets/js/bootstrap.bundle.min.js"></script>
<!-- simplebar -->
<script src="assets/js/simplebar.min.js"></script>
<!-- Icons -->
<script src="assets/js/feather.min.js"></script>
<!-- Main Js -->
<script src="assets/js/app.js"></script>
<script src="assets/js/sortcolumn.js" type="text/javascript"></script>

</body>




</html>
