
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="sidebar" class="sidebar-wrapper">
    <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
        <div class="sidebar-brand">
            <a href="dashboard">
                <img src="assets/images/favicon.ico2.png" height="24" class="logo-light-mode" alt="">
                <img src="assets/images/favicon.ico2.png" height="24" class="logo-dark-mode" alt="">
            </a>
        </div>

        <ul class="sidebar-menu pt-3">
            <li><a href="dashboard"><i class="uil uil-dashboard me-2 d-inline-block"></i>Dashboard</a></li>
                <c:if test="${sessionScope.role == 14}">
                <li><a href="attend?action=tracking"><i class="uil uil-dashboard me-2 d-inline-block"></i>Attendance Tracking</a></li>
                <li><a href="attend?action=attend"><i class="uil uil-dashboard me-2 d-inline-block"></i>Take Attendance</a></li>
                </c:if>
                <c:if test="${sessionScope.role == 15}">
                <li><a href="attend?action=schedule"><i class="uil uil-dashboard me-2 d-inline-block"></i>Schedule Attendance</a></li>
                </c:if>
                <c:if test="${sessionScope.role == 11}">
                <li><a href="system_permission"><i class="uil uil-dashboard me-2 d-inline-block"></i>System Permission</a></li>
                <li class="sidebar-dropdown">
                    <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>User</a>
                    <div class="sidebar-submenu">
                        <ul>
                            <li><a href="users?action=view">User List</a></li>
                        </ul>
                    </div>
                </li>
                <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Setting</a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="settinglist?action=view">Setting List</a></li>
                        <li><a href="settinglist?action=add">Add Setting</a></li>
                    </ul>
                </div>
            </li>
            </c:if>
            <c:if test="${sessionScope.role == 13 || sessionScope.role == 14}">
                <li class="sidebar-dropdown">
                    <a href="javascript:void(0)"><i class="uil uil-calender me-2 d-inline-block"></i>Schedule</a>
                    <div class="sidebar-submenu">
                        <ul>
                            <li><a href="schedule?action=list">Schedule List</a></li>
                            <li><a href="schedule?action=add">Add New Schedule</a></li>
                        </ul>
                    </div>
                </li>
            </c:if>

            <c:if test="${sessionScope.role == 14}">
                <li class="sidebar-dropdown">
                    <a href="javascript:void(0)"><i class="uil uil-clock-nine me-2 d-inline-block"></i>MileStone</a>
                    <div class="sidebar-submenu">
                        <ul>
                            <li><a href="milestone?action=list">Milestone List</a></li>
                            <li><a href="milestone?action=add">Add New Milestone</a></li>
                        </ul>
                    </div>
                </li>
            </c:if>

            <c:if test="${sessionScope.role == 14 || sessionScope.role == 12 || sessionScope.role == 11}">
                <li class="sidebar-dropdown">
                    <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Subject</a>
                    <div class="sidebar-submenu">
                        <ul>
                            <li><a href="subject?action=view">Subject List</a></li>
                            <c:if test="${sessionScope.role == 11}">
                            <li><a href="subject?action=add">Add Subject</a></li>
                            </c:if>
                            
                                <c:if test="${sessionScope.role == 12}">
                                <li><a href="subject_setting?action=view">Subject Setting List</a></li>
                                <li><a href="subject_setting?action=add">Add Subject Setting</a></li>
                                </c:if>
                        </ul>
                    </div>
                </li>
            </c:if>
            <c:if test="${sessionScope.role == 12}">
                <li class="sidebar-dropdown">
                    <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Assignment</a>
                    <div class="sidebar-submenu">
                        <ul>
                            <li><a href="list_ass">Assignment List</a></li>
                            <li><a href="add_ass">Add Assignment</a></li>
                        </ul>
                    </div>
                </li>
            </c:if>   
            <c:if test="${sessionScope.role == 14 || sessionScope.role == 13 || sessionScope.role == 15}">
                <li class="sidebar-dropdown">
                    <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Team </a>
                    <div class="sidebar-submenu">
                        <ul>
                            <li><a href="list_of_team">Team List </a></li>
                                <c:if test="${sessionScope.role == 14|| sessionScope.role == 13}">
                                <li><a href="add_team">Add Team</a></li>
                                <li ><a  href="list_team">New Team </a></li>
                                </c:if>
                        </ul>
                    </div>
                </li>
            </c:if>
            <c:if test="${sessionScope.role == 12}">
                <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Eval Criteria </a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="listeval">Eval Criteria List </a></li>
                        <li><a href="add_eval">Add Eval </a></li>

                    </ul>
                </div>
            </li>
            
            <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Class </a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="classlist?action=view">Class List</a></li>
                        <li><a href="classlist?action=add">Add Class</a></li>
                    </ul>
                </div>
            </li>
            </c:if>    
            
            <c:if test="${sessionScope.role == 13}">
                <li><a href="weblist?action=view"><i class="uil uil-stethoscope me-2 d-inline-block"></i>Web Contact</a></li>
            </c:if>
            

            
            <c:if test="${sessionScope.role == 14 || sessionScope.role == 13}">
                <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Class Setting</a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="classsetting?action=view">Class Setting List</a></li>
                        <li><a href="classsetting?action=add">Add Class Setting</a></li>
                    </ul>
                </div>
            </li>
            </c:if> 
            <c:if test="${sessionScope.role == 14 || sessionScope.role == 13}">
                <li class="sidebar-dropdown">
                <a href="javascript:void(0)"><i class="uil uil-sign-in-alt me-2 d-inline-block"></i>Trainee</a>
                <div class="sidebar-submenu">
                    <ul>
                        <li><a href="traineelist?action=view">Trainee List</a></li>
                    </ul>
                </div>
            </li>
            </c:if>
            

        </ul>
        <!-- sidebar-menu  -->
    </div>
    <!-- sidebar-content  -->
    <ul class="sidebar-footer list-unstyled mb-0">
        <li class="list-inline-item mb-0 ms-1">
            <a href="#" class="btn btn-icon btn-pills btn-soft-primary">
                <i class="uil uil-comment icons"></i>
            </a>
        </li>
    </ul>
</nav>