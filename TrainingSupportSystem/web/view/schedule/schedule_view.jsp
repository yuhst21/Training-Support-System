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
        <link href="assets/css/modal.css" rel="stylesheet" type="text/css"/>



        <link href="assets/css/toggle_switch.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script src="assets/js/confirm_schedule.js" type="text/javascript"></script>

        <!-- JS -->
        <script>


            function f1() {
                var e = document.getElementById('test');
                var value = e.value;
                $.ajax({
                    url: 'test',
                    type: "POST",
                    data: {
                        classid: value
                    },
                    success: function (data) {
                        content.innerHTML += data;
                    }
                });

            }

            function add() {
                window.location.href = "milestone?action=add";
            }

            function modal_close2() {
                window.location.href = "#";
            }





        </script>

    </head>
    <body>
        <div id="demo-modal" class="modal">
            <div class="modal__content" id="model_content">
                <h2>Confirm</h2>

                <p>
                    Do you want to change subject status ? 
                </p>

                <div class="modal__footer">
                    <button type="button" onclick="modal_close()" class="button-10">Cancel</button>
                    <button type="button" onclick="modal_change()" class="button-10">Confirm</button>
                </div>

                <a href="#" class="modal__close">&times;</a>
            </div>
        </div>
        <div id="demo-modal2" class="modal">
            <div class="modal__content" id="model_content">
                <h2></h2>

                <p>
                    Update successfully!
                </p>

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
                <c:if test="${clist.size() > 0}">
                    <div class="container-fluid">
                        <div class="layout-specing">

                            <!-- SEARCH BAR -->
                            <div class="search-bar p-0 d-none d-lg-block ms-2">

                                <!-- SEARCH BAR -->


                                <div class="d-md-flex justify-content-between">


                                    <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                        <ul id="content" class="breadcrumb bg-transparent rounded mb-0 p-0">
                                            <li class="breadcrumb-item"><a href="index.html">TSS</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">Schedule</li>
                                        </ul>

                                    </nav>
                                </div>
                                <div id="search">
                                    <!-- FILTER BAR -->

                                    <!-- FILTER BAR --> 
                                    <form action="schedule"  method="GET"  class="searchform" >
                                        <input type="hidden" name="action" value="list">
                                        <div class="row">  
                                            <div class="col-md-2">
                                                <div class="mb-3">
                                                    </br>
                                                    <select onchange="f1()" id="test"  style="width: 120px;text-align: center" class="form-select"   style="margin-right: 80px;" class="s_filter" name="class_id" >                                                     
                                                        <c:forEach items="${clist}" var="c">
                                                            <option 
                                                                <c:if test="${class_id == c.class_id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${c.class_id}" ">${c.class_name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div><!--end col-->


                                            <div class="col-md-2">
                                                <div class="mb-3">
                                                    </br>
                                                    <select style="width: 120px;text-align: center" class="form-select" style="margin-right: 80px;" class="s_filter" name="status">                                       
                                                        <option value="-1">All status</option>
                                                        <option value="active"<c:if test="${status eq 'active'}">
                                                                selected="selected"
                                                            </c:if>>Active</option>
                                                        <option value="inactive" <c:if test="${status eq 'inactive'}">
                                                                selected="selected"
                                                            </c:if> >Inactive</option>
                                                    </select>
                                                </div>
                                            </div><!--end col-->



                                            <div class="col-md-2">
                                                <div class="mb-3">
                                                    </br>
                                                    <select onchange="f1()" id="test"  style="width: 120px;text-align: center" class="form-select"   style="margin-right: 80px;" class="s_filter" name="slot_id" > 
                                                        <option value="-1">All slot</option>                                                    
                                                        <c:forEach items="${tlist}" var="t">
                                                            <option 
                                                                <c:if test="${slot_id == t.slotid}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${t.slotid}" ">${t.slotname}</option>
                                                        </c:forEach>
                                                    </select>

                                                </div>
                                            </div><!--end col-->






                                            <div class="col-md-2">
                                                <div class="mb-3">
                                                    </br>
                                                    <button  type="submit" class="btn btn-primary">Search</button>
                                                </div>
                                            </div><!--end col-->
                                            <div class="col-md-2">
                                                <div class="mb-3">
                                                </div>


                                            </div><!--end col-->


                                            <div class="col-md-2">
                                                <div class="mb-3">
                                                    </br>
                                                    <button   onclick="add()" class="btn btn-primary"><a style="color:white" href="schedule?action=add">Add</a></button>
                                                </div>
                                            </div><!--end col-->







                                        </div>
                                    </form>


                                </div>

                                <div class="row">
                                    <div class="col-12 mt-4">
                                        <div class="table-responsive shadow rounded">
                                            <table class="table table-center bg-white mb-0 table-sortable">
                                                <thead>
                                                    <tr>
                                                        <th class="border-bottom p-3" style="min-width: 50px;">Id</th>
                                                        <th class="border-bottom p-3" style="min-width: 250px;">Topic</th>
                                                        <th class="border-bottom p-3" style="min-width: 100px;">Slot</th>
                                                        <th class="border-bottom p-3">From Time</th>
                                                        <th class="border-bottom p-3">To Time</th>
                                                        <th class="border-bottom p-3">Date</th>
                                                        <th class="border-bottom p-3">Room</th>
                                                        <th class="border-bottom p-3">Status</th>
                                                        <th class="border-bottom p-3" style="min-width: 100px;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${slist}" var="s">
                                                        <tr>
                                                            <td class="p-3 form-label">${s.getSchedule_id()}</td>
                                                            <td class="p-3 form-label">${s.topic}</td>
                                                            <td class="py-3 form-label"> ${s.slot.slotname}</td>                                                      
                                                            <td class="p-3 form-label">${s.from_time}</td>
                                                            <td class="p-3 form-label">${s.to_time}</td>
                                                            <td class="p-3 form-label">${s.training_date}</td>
                                                            <td class="p-3 form-label">${s.room.room_name}</td>
                                                    <form id="status_form_${s.getSchedule_id()}" action="schedule">
                                                        <input type="hidden" name="action" value="list">
                                                        <input name="page" type="hidden" value="${page_index}">
                                                        <input name="class_id" type="hidden" value="${class_id}">
                                                        <input name="slot_id" type="hidden" value="${slot_id}">
                                                        <input name="status" type="hidden" value="${status}">



                                                        <input type="hidden" name="change_status" value="${s.getSchedule_id()}_${s.status}"/>
                                                        <c:if test="${s.status eq true}">
                                                            <td class="p-3">
                                                                <span class="badge bg-soft-success">Active</span>
                                                                <label 

                                                                    class="switch-wrap">
                                                                    <input type="checkbox" class="open-model" id="status_check_${s.getSchedule_id()}" checked onChange="modal_open(${s.getSchedule_id()})" />

                                                                    <div class="switch"></div>
                                                                </label>    
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${s.status eq false}">
                                                            <td class="p-3">
                                                                <span style="color: red" class="badge bg-soft-danger">Inactive</span>
                                                                <label 

                                                                    class="switch-wrap">
                                                                    <input type="checkbox" class="open-model"  id="status_check_${s.getSchedule_id()}" onChange="modal_open(${s.getSchedule_id()})"/>
                                                                    <div class="switch"></div>
                                                                </label>
                                                            </td>
                                                        </c:if>
                                                        <td class="text-end p-3">
                                                            <a                                                            
                                                                href="schedule?action=edit&id=${s.getSchedule_id()}" class="btn btn-icon btn-pills btn-soft-success" ><i class="uil uil-pen"></i></a>
                                                            <a                                                            
                                                                href="attend?action=detail&schedule=${s.schedule_id}" class="btn btn-icon btn-pills btn-soft-success" ><i class="uil uil-file-medical-alt"></i></a>
                                                        </td>



                                                        </tr> 
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div><!--end row-->
                                <script src="assets/js/pagger_schedule.js" type="text/javascript"></script>



                                <div class="row text-center">
                                    <!-- PAGINATION START -->
                                    <div class="col-12 mt-4">
                                        <div class="d-md-flex align-items-center text-center justify-content-between">
                                            <span class="text-muted me-3">Showing ${(page_index-1) * page_size + 1} - ${page_index * page_size} out of ${count}</span>
                                            <ul id="only-pagger" class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                                <script>
                                                                        render("only-pagger",${page_index},${total_page}, 2,${class_id}, '${status}',${slot_id});

                                                </script>
                                            </ul>
                                        </div>
                                    </div><!--end col-->
                                    <!-- PAGINATION END -->
                                </div><!--end row-->

                            </div>
                        </div><!--end container-->

                        <!-- Footer Start -->
                        <footer class="bg-white shadow py-3">
                            <div class="container-fluid">
                                <div class="row align-items-center">
                                    <div class="col">
                                        <div class="text-sm-start text-center">
                                            <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear());</script> © Doctris. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
                                        </div>
                                    </div><!--end col-->
                                </div><!--end row-->
                            </div><!--end container-->
                        </footer><!--end footer-->
                        <!-- End -->
                    </div>
                </c:if>  
                <c:if test="${class_id eq -1}">
                    <div class="container-fluid">
                        <div class="layout-specing">

                            <!-- SEARCH BAR -->
                            <div class="search-bar p-0 d-none d-lg-block ms-auto">

                                <!-- SEARCH BAR -->


                                <div class="d-md-flex justify-content-between">


                                    <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                                        <ul id="content" class="breadcrumb bg-transparent rounded mb-0 p-0">
                                            <li class="breadcrumb-item"><a href="index.html">TSS</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">Schedule</li>
                                        </ul>

                                    </nav>
                                </div>
                                <div id="search">
                                    <!-- FILTER BAR -->
                                    <form action="schedule"  method="GET"  class="searchform" >
                                        <input type="hidden" name="action" value="list">
                                        <!-- FILTER BAR --> 
                                        <div class="row">  
                                            <div class="col-md-12">
                                                <div class="mb-3">
                                                    </br>
                                                    <h6>You dont have any class yet</h6>
                                                </div>
                                            </div><!--end col-->


                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    </br>
                                                    <input type="hidden" name="name">
                                                </div>
                                            </div><!--end col-->



                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    </br>
                                                    <input type="hidden" name="name">

                                                </div>
                                            </div><!--end col-->





                                            <div class="col-md-3">
                                                <div class="mb-3">
                                                    <input type="hidden" name="name">
                                                </div>
                                            </div><!--end col-->







                                        </div>
                                    </form>
                                </div>








                            </div>
                        </div><!--end container-->

                        <!-- Footer Start -->
                        <footer class="bg-white shadow py-3">
                            <div class="container-fluid">
                                <div class="row align-items-center">
                                    <div class="col">
                                        <div class="text-sm-start text-center">
                                            <p class="mb-0 text-muted"><script>document.write(new Date().getFullYear());</script> © Doctris. Design with <i class="mdi mdi-heart text-danger"></i> by <a href="index.html" target="_blank" class="text-reset">Shreethemes</a>.</p>
                                        </div>
                                    </div><!--end col-->
                                </div><!--end row-->
                            </div><!--end container-->
                        </footer><!--end footer-->
                        <!-- End -->
                    </div>

                </c:if>  

            </main>

            <!--End page-content" -->
        </div>
        <!-- page-wrapper -->

    </script>


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
