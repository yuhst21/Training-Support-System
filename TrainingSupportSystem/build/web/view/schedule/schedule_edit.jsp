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
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
           <link href="assets/css/modal.css" rel="stylesheet" type="text/css"/>




        <script>



function modal_open(id) {

    var html = document.getElementById("model_content");
    html.innerHTML += "<input type=\"hidden\" id=\"modal_id\" value=" + id + ">";
    window.location.href = "#demo-modal";



}


function loz() {
    var from_time = document.getElementById('from_time').value;
    var to_time = document.getElementById('to_time').value;
    var topic = document.getElementById('topic').value;
    var min_chars = 0;
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    if (month < 10)
        month = "0" + month;
    if (day < 10)
        day = "0" + day;

    var today = year + "-" + month + "-" + day;
    var training_date = document.getElementById('date').value;
    if (from_time === "") {
        var content = document.getElementById('txt1');
        content.innerHTML = "<p id=\"buoi\" style=\"color: red\">From time can't be empty</p>";
    } else {
        var content1 = document.getElementById('txt1');
        content1.innerHTML = "";


    }

    if (to_time === "") {
        var content = document.getElementById('check');
        content.innerHTML = "<p id=\"cac\" style=\"color: red\">To time can't be empty</p>";
    } else if (from_time > to_time && to_time !== null && from_time !== null) {
        var content = document.getElementById('check');
        content.innerHTML = "<p id=\"cac\" style=\"color: red\">To time can't be samller than From time</p>";

    } else {
        var content1 = document.getElementById('check');
        content1.innerHTML = "";

    }


    if (!training_date) {
        var content = document.getElementById('checkdate');
        content.innerHTML = "<p id=\"cac\" style=\"color: red\">Date can't be empty</p>";
    } else {
       var content1 = document.getElementById('checkdate');
        content1.innerHTML = "";
    }
    if(topic.length === min_chars){
        var content = document.getElementById('topic1');
        content.innerHTML = "<p id=\"cac\" style=\"color: red\">Topic can't be empty</p>";
    } else {
         var content1 = document.getElementById('topic1');
        content1.innerHTML = "";
    }
     if (from_time < to_time && to_time !== null && from_time !== null && training_date && topic.length>min_chars ) {
        var html = document.getElementById("model_content");
       
        document.getElementById("dcmm").submit();
    }




}

function modal_close() {

    window.location.href = "#";
}


function modal_change() {
        window.location.href = "#demo-modal2";


}

function modal_close2() {
   document.getElementById("dcmm").submit();
}
            

            



        </script>

    </head>

   
      <body>
       
          <div id="demo-modal2" class="modal">
            <div class="modal__content" id="model_content">
                <h2></h2>

                <p>
                   Update successfully!
                </p>

                <div class="modal__footer">
                    <button type="button" onclick="modal_close()" class="button-10">Close</button>
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
                            <h5 class="mb-0">Update Schedule Information</h5>

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
                                                        <form id="dcmm" action="schedule?action=edit" method="Post">
                                                            <input type="hidden" name="id" value="${schedule.schedule_id}"/>
                                                            <div class="row">
                                                                <div class="col-lg-12">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Topic:*</label>
                                                                        <input id="topic"  type="text" class="form-control" placeholder="Topic:" required=""  name="topic" value="${schedule.topic}" >
                                                                        
                                                                    </div>
                                                                    <div id="topic1"></div>
                                                                </div><!--end col-->
                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Class:</label>
                                                                        <input  style="text-align: center" type="text" class="form-control" placeholder="New password" required="" value="${schedule.classs.class_name}" name="class_code" readonly="readonly">
                                                                        <input id="class_id" type="hidden" name="class_id" value="${schedule.classs.class_id}">
                                                                    </div>
                                                                </div><!--end col-->



                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Time slot
                                                                            :</label>
                                                                        <select onchange="f1()" id="slot_id"  style="text-align: center" class="form-control"   style="margin-right: 80px;" class="s_filter" name="slot_id" > 

                                                                            <c:forEach items="${tlist}" var="t">
                                                                                <option 
                                                                                    <c:if test="${schedule.slot.slotid == t.slotid}">
                                                                                        selected="selected"
                                                                                    </c:if>
                                                                                    value="${t.slotid}" ">${t.slotname}</option>
                                                                            </c:forEach>
                                                                        </select>

                                                                    </div>
                                                                </div><!--end col-->
                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">From Time</label>
                                                                        <input id="from_time"  style="text-align: center" type="time" class="form-control" placeholder="New password" required="" value="12:50" name="from_time" >
                                                                    </div>
                                                                    <div id="txt1"></div>
                                                                </div><!--end col-->
                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">To Time</label>
                                                                        <input id="to_time" style="text-align: center" type="time" class="form-control" placeholder="New password" required="" value="14:20" name="to_time"  >
                                                                    </div>
                                                                    <div id="check"></div>
                                                                </div><!--end col-->
                                                                


                                                               
                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Date:</label>
                                                                        <input id="date" type="date" class="form-control"  style="text-align: center"  required="" name="date" value="${schedule.training_date}">
                                                                        <div id="checkdate"></div>

                                                                    </div>
                                                                </div><!--end col-->

                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Room:</label>
                                                                        <select onchange="f1()" id="room_id"  style="text-align: center" class="form-control"   style="margin-right: 80px;" class="s_filter" name="room" > 

                                                                            <c:forEach items="${rlist}" var="r">
                                                                                <option 
                                                                                    <c:if test="${schedule.room.room_id == r.room_id}">
                                                                                        selected="selected"
                                                                                    </c:if>
                                                                                    value="${r.room_id}" >${r.room_name}</option>
                                                                            </c:forEach>
                                                                        </select>

                                                                    </div>
                                                                </div><!--end col-->

                                                                <div class="col-lg-6">
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Status:</label>
                                                                        </br>


                                                                        <input id="r1" style="margin-right: 4px"
                                                                               <c:if test="${schedule.status eq true}">checked=""</c:if>
                                                                                   type="radio" name="status" value="active">Active 
                                                                               <input id="r2" style="margin-left: 10px; margin-right: 4px"
                                                                               <c:if  test="${schedule.status  eq false}">checked=""</c:if> 
                                                                                   type="radio" name="status" value="deactive">Inactive

                                                                        </div>
                                                                    </div><!--end col-->
                                                                    <div class="col-lg-6">
                                                                        <div class="mb-3">
                                                                            <label class="form-label">Attend:</label>
                                                                            </br>


                                                                            <span class="badge bg-soft-success">Attended</span>

                                                                        </div>
                                                                    </div><!--end col-->
                                                                    <div class="col-md-12">
                                                                            <div class="mb-3">
                                                                                <label class="form-label">Note:</label>
                                                                                <textarea name="note" id="comments" rows="4" class="form-control" placeholder="Note :" name="note">${schedule.note}</textarea>
                                                                            </div>
                                                                        </div>
                                                                    <p  style="color:red">${mess1}</p>







                                                        </form>

                                                    </div><!--end row-->
                                                    <input style="float:right"  class="btn btn-primary"  type="submit" value="Update" onclick="loz()" />


                                                   </br>
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