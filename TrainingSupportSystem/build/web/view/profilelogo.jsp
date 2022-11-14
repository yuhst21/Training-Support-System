<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.user != null}">
                              <li class="list-inline-item mb-0 ms-1">
                                <div class="dropdown dropdown-primary">
                                    <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="images/${sessionScope.user.avatar_url}" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                                    <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                                        <a class="dropdown-item d-flex align-items-center text-dark" href="user?action=profile">
                                            <img src="images/${sessionScope.user.avatar_url}" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                            <div class="flex-1 ms-2">
                                                <span class="d-block mb-1">${sessionScope.user.full_name}</span>
                                                <small class="text-muted"></small>
                                            </div>
                                        </a>
                                        <a class="dropdown-item text-dark" href="dashboard"><span class="mb-0 d-inline-block me-1"><i class="uil uil-dashboard align-middle h6"></i></span> Dashboard</a>
                                        <a class="dropdown-item text-dark" href="user?action=changepass"><span class="mb-0 d-inline-block me-1"><i class="uil uil-setting align-middle h6"></i></span> Change Password</a>
                                        <div class="dropdown-divider border-top"></div>
                                        <a class="dropdown-item text-dark" href="logout"><span class="mb-0 d-inline-block me-1"><i class="uil uil-sign-out-alt align-middle h6"></i></span> Logout</a>
                                    </div>
                                </div>
                            </li>                           
                      </c:if>
                              <c:if test="${sessionScope.user == null}">
                                <li class="list-inline-item mb-0 ms-1">  
                                    <a href="login">Login</a>
                                </li>
                                <li class="list-inline-item mb-0 ms-1">  
                                    <a href="url">Register</a>
                                </li>
                            </c:if>   
                            
                            