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
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <link href="assets/css/newcss.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/modal.css" rel="stylesheet" type="text/css"/>


        <script>
            function limit(element)
            {
                var max_chars = 20;
                var min_chars = 0;
                var content = document.getElementById('dit');

                if (element.value.length > max_chars) {
                    var content = document.getElementById('dit');
                    content.innerHTML = "<p id=\"noti\" style=\"color: red\">Title can't exceed 20 characters</p>";
                }
                if (element.value.length < max_chars && element.value.length > min_chars) {
                    var content = document.getElementById('noti');
                    content.innerHTML = "";
                }
                if (element.value.length === min_chars) {
                    var content = document.getElementById('dit');
                    content.innerHTML = "<p id=\"noti\" style=\"color: red\">Title can't be empty</p>";
                }
            }

            function change() {
                var todate = document.getElementById('date1').value;
                var enddate = document.getElementById('date2').value;
                if(!todate){
                    var content = document.getElementById('loz');
                    content.innerHTML = "<p id=\"check2\" style=\"color: red\">Please select From date!</p>";
                } else{
                    var content = document.getElementById('loz');
                    content.innerHTML = "";
                }
                if(!enddate){
                    var content = document.getElementById('check');
                    content.innerHTML = "<p id=\"check2\" style=\"color: red\">Please select To date!</p>";
                } else{
                    var content = document.getElementById('check');
                    content.innerHTML = "";
                }
                
                
                


            }

            function f3() {
                var todate = document.getElementById('date1').value;
                var enddate = document.getElementById('date2').value;
                var date = document.getElementById('name2');
                var max_chars = 20;
                var min_chars = 0;
                if (date.value.length === min_chars) {
                    var content = document.getElementById('dit');
                    content.innerHTML = "<p id=\"noti\" style=\"color: red\">Title can't be empty</p>";
                }
                if(!todate){
                    var content = document.getElementById('lol');
                    content.innerHTML = "<p id=\"check2\" style=\"color: red\">Please select From date!</p>";
                } else{
                    var content = document.getElementById('lol');
                    content.innerHTML = "";
                }
                if(!enddate){
                    var content = document.getElementById('check');
                    content.innerHTML = "<p id=\"check2\" style=\"color: red\">Please select To date!</p>";
                } else{
                    var content = document.getElementById('check');
                    content.innerHTML = "";
                }
                 if (enddate < todate && todate && enddate) {
                    var content = document.getElementById('check');
                    content.innerHTML = "<p id=\"check2\" style=\"color: red\">end date can't be smaller than start date</p>";
                }
                if (date.value.length < max_chars && enddate >= todate && date.value.length > min_chars && enddate && todate ) {
                   document.getElementById("form2").submit();

                }
            }



            function f1() {

                var e = document.getElementById('class_id');
                var value = e.value;
                $.ajax({
                    url: 'milestone?action=load',
                    type: "GET",
                    data: {
                        classid: value
                    },
                    success: function (data) {
                        var content = document.getElementById('content');
                        content.innerHTML = data;

                    }
                });

            }
            
            function modal_close() {

                window.location.href = "#";
            }

            function modal_change() {
                window.location.href = "#demo-modal2";


            }

            function modal_close2() {
                document.getElementById("form2").submit();
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
                            <h5 class="mb-0">Add New Milestone</h5>

                            <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                    <li class="breadcrumb-item"><a href="homepage">TSS</a></li>
                                    <li class="breadcrumb-item active" aria-current="page"><a href="schedule?action=list">Schedule</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Edit</li>
                                </ul>
                            </nav>
                        </div>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="card border-0 rounded-0 p-4">


                                    <div class="tab-content mt-2" id="pills-tabContent">
                                        <div class="row">
                                            <div class="col-lg-3"></div>
                                            <div class="col-lg-6">


                                                <div class="rounded shadow mt-4">
                                                    <div class="p-4 border-bottom">
                                                        <h6 class="mb-0">Activity detail:</h6>
                                                    </div>

                                                    <div class="p-4">
                                                        <form id="form2" action="milestone" method="Post">
                                                            <input type="hidden" name="action" value="add"/>
                                                            <input type="hidden" name="id" value="${milestone.milestone_id}"/>
                                                            <div class="row">
                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Tittle</label>
                                                                        <input 

                                                                            required="" name="tittle" id="name2" type="text" class="form-control" placeholder="Tittle:"  maxlength="21"  onkeydown="limit(this);" onkeyup="limit(this);" >


                                                                    </div>
                                                                    <div class="mb-3"  id="dit">




                                                                    </div>   
                                                                </div><!--end col-->

                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Class:</label>

                                                                        <select id="class_id" style="text-align: center" class="form-control"  onchange="f1()" style="margin-right: 80px;" class="s_filter" name="class_id"> 

                                                                            <c:forEach items="${clist}" var="c">
                                                                                <option 
                                                                                    <c:if test="${class_id == c.class_id}">
                                                                                        selected="selected"
                                                                                    </c:if>
                                                                                    value="${c.class_id}">${c.class_name}</option>
                                                                            </c:forEach>
                                                                        </select>

                                                                    </div>
                                                                </div><!--end col-->


                                                                <div class="col-lg-6">
                                                                    <div class="mb-3" id="content">
                                                                        <label class="form-label">Assignment:</label>

                                                                        <select style="width: 200px;text-align: center" class="form-control"  style="margin-right: 80px;" class="s_filter" name="ass_id"> 

                                                                            <c:forEach items="${alist}" var="a">
                                                                                <option 
                                                                                    <c:if test="${a.ass_id == ass_id}">
                                                                                        selected="selected"
                                                                                    </c:if>


                                                                                    value="${a.ass_id}">${a.title}</option>
                                                                            </c:forEach>
                                                                        </select>

                                                                    </div>
                                                                </div><!--end col-->








                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">From Date</label>
                                                                        <input 
                                                                            required="" name="fromdate" id="date1"  type="date" class="form-control" placeholder="Subject Code :" value="${milestone.from_date}" onchange="change()">

                                                                    </div>
                                                                     <div id="lol"></div>
                                                                </div><!--end col-->

                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">To Date</label>

                                                                        <input 

                                                                            required="" name="todate" id="date2"  type="date" class="form-control" placeholder="Subject Code :" value="${milestone.to_date}" onchange="change()">

                                                                    </div>
                                                                    <div id="check"></div>

                                                                </div>
                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Status:</label>
                                                                        </br>
                                                                        <input style="margin-right: 4px"
                                                                               checked=""
                                                                               type="radio" name="status" value="active">Active 
                                                                        <input style="margin-left: 10px; margin-right: 4px"

                                                                               type="radio" name="status" value="deactive">Inactive
                                                                    </div>
                                                                </div><!--end col-->

                                                            </div><!--end col-->

                                                            <p style="color:red">${mess1}</p>
                                                        </form>





                                                        <button onclick="f3()" style="float: right"  class="btn btn-primary">Add</button>                       

                                                    </div><!--end row-->

                                                    </br>



                                                </div>
                                            </div>
                                        </div><!--end col-->
                                        <div class="col-lg-3"></div>

                                    </div><!--end teb pane-->








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