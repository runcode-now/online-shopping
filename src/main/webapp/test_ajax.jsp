<%-- 
    Document   : s_order
    Created on : Feb 4, 2024, 9:37:05 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpServletRequest" %>
<%@page import="jakarta.servlet.http.HttpServlet" %>
<%@page import="com.team3.onlineshopping.dal.AddressDAO"%>
<%@page import="com.team3.onlineshopping.dal.OrderDAO"%>
<%@page import="com.team3.onlineshopping.model.Address"%>
<%@page import="com.team3.onlineshopping.model.Order"%>
<!DOCTYPE html>
<html lang="en">


    <!-- Mirrored from slidesigma.com/themes/html/foodtech/pages/orders.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 31 Jan 2024 16:47:40 GMT -->
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Foodtech Dashboard</title>
        <!-- Iconic Fonts -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="vendors/iconic-fonts/font-awesome/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="vendors/iconic-fonts/flat-icons/flaticon.css">
        <link rel="stylesheet" href="vendors/iconic-fonts/cryptocoins/cryptocoins.css">
        <link rel="stylesheet" href="vendors/iconic-fonts/cryptocoins/cryptocoins-colors.css">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- Page Specific Css (Datatables.css) -->
        <link href="assets/css/datatables.min.css" rel="stylesheet">

        <!-- jQuery UI -->
        <link href="assets/css/jquery-ui.min.css" rel="stylesheet">
        <!-- Foodtech Core styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="favicon.ico">

    </head>

    <body class="ms-body ms-aside-left-open ms-primary-theme ms-has-quickbar relative">

        <!-- Sidebar Navigation Left -->
        <aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
            <!-- Logo -->
            <div class="logo-sn ms-d-block-lg">
                <a class="pl-0 ml-0 text-center" href="index.html">
                    <img src="assets/img/foodtech/foodtech-logo-216x62.png" alt="logo">
                </a>
            </div>
            <br>
            <!-- Navigation -->
            <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion">
                <!-- Dashboard -->
                <li class="menu-item">
                    <a href="pages/orders.html"> <span><i class="fas fa-columns" style="width: 14px; height: 14px;"></i>Dashboard</span>
                    </a>
                </li>
                <!-- /Dashboard -->

                <!-- order -->
                <li class="menu-item">
                    <a href="#" class="has-chevron" data-toggle="collapse" data-target="#order" aria-expanded="false"
                       aria-controls="order">  <span><i class="fas fa-clipboard-list fs-16"></i>Đơn hàng</span>
                    </a>
                    <ul id="order" class="collapse" aria-labelledby="order" data-parent="#side-nav-accordion">
                        <li> <a href="sale_order" class="active">Tất cả</a>
                        </li>
                        <li> <a href="#">Đơn hàng đã giao</a>
                        </li>
                        <li> <a href="#">Đơn hàng đã hủy</a>
                        </li>
                        <li> <a href="#">Đơn hàng đang chờ</a>
                        </li>
                    </ul>
                </li>
                <!-- order -->

                <!--customers-->
                <li class="menu-item">
                    <a href="#" class="has-chevron" data-toggle="collapse" data-target="#customer" aria-expanded="false"
                       aria-controls="customer"> <span><i class="fas fa-user-friends fs-16"></i>Khách hàng </span>
                    </a>
                    <ul id="customer" class="collapse" aria-labelledby="customer" data-parent="#side-nav-accordion">
                        <li> <a href="pages/customer/customersreview.html">Customers Review</a>
                        </li>
                        <li> <a href="pages/customer/customersreview.html">Danh sách khách hàng</a>
                        </li>
                        <li> <a href="pages/customer/social.html">Social Activity</a>
                        </li>
                    </ul>
                </li>
                <!--Customers  end--> 

                <!-- ========= PROFILE ============================= -->
                <p style="border-bottom: 1px solid rgb(177, 177, 177); width: 80%; margin: 20px 22px"></p>
                <!-- change profile -->
                <li class="menu-item">
                    <a href="#"> <span><i class="fas fa-file-invoice" style="width: 14px; height: 14px;"></i>Chỉnh sửa thông tin</span>
                    </a>
                </li>
                <!-- change profile  -->
                <!-- change password -->
                <li class="menu-item">
                    <a href="#"> <span><i class="fas fa-key" style="width: 14px; height: 14px;"></i>Đổi mật khẩu</span>
                    </a>
                </li>
                <!-- change password  -->
                <!-- sign out -->
                <li class="menu-item">
                    <a href="com_logout"> <span><i class="fas fa-sign-out-alt" style="width: 14px; height: 14px;"></i>Đăng xuất</span>
                    </a>
                </li>
                <!-- sign out  -->
            </ul>
        </aside>



        <!-- Main Content -->
        <main class="body-content">
            <!-- Navigation Bar -->
            <nav class="navbar ms-navbar">
                <div class="ms-aside-toggler ms-toggler pl-0" data-target="#ms-side-nav" data-toggle="slideLeft"> <span
                        class="ms-toggler-bar bg-primary"></span>
                    <span class="ms-toggler-bar bg-primary"></span>
                    <span class="ms-toggler-bar bg-primary"></span>
                </div>
                <div style="width: 12%; padding: 10px 0;">
                    <img style="width: 100%;"
                         src="https://scontent.fhan14-1.fna.fbcdn.net/v/t1.15752-9/415986971_1701360590387678_3307011220705221556_n.png?_nc_cat=107&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=tso1MuEDASYAX_AO5Iv&_nc_oc=AQkQFrorscwafEjAAlUbuJTSrmadsHMYl42q3djJWWUN-kzfDDkHp4EJsTQ3VBJRv3g&_nc_ht=scontent.fhan14-1.fna&oh=03_AdQx9-1PIA-_3gFwAiM_rPJiyS6SNrw7s79bvHsqHrA1yw&oe=65E4653C"
                         alt="">
                </div>
                <ul class="ms-nav-list ms-inline mb-0" id="ms-nav-options">
                    <li class="ms-nav-item ms-nav-user dropdown">
                        <a href="#" id="userDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img class="ms-user-img ms-img-round float-right" src="assets/img/foodtech/customer-6.jpg" alt="people">
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right user-dropdown" aria-labelledby="userDropdown">
                            <li class="dropdown-menu-header">
                                <h6 class="dropdown-header ms-inline m-0"><span class="text-disabled">Phạm Hùng Anh</span></h6>
                            </li>
                            <li class="dropdown-divider"></li>
                            <li class="ms-dropdown-list">
                                <a class="media fs-14 p-2" href="#"> <span><i class="flaticon-user mr-2"></i> Hồ sơ</span>
                                </a>
                                <a class="media fs-14 p-2" href="#"> <span><i class="flaticon-security mr-2"></i> Đổi mật khẩu</span>
                                </a>
                            </li>
                            <li class="dropdown-divider"></li>
                            <li class="dropdown-menu-footer">
                                <a class="media fs-14 p-2" href="#"> <span><i class="flaticon-security mr-2"></i> Lock</span>
                                </a>
                            </li>
                            <li class="dropdown-menu-footer">
                                <a class="media fs-14 p-2" href="#"> <span><i class="flaticon-shut-down mr-2"></i> Logout</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>


            <!-- Body Content Wrapper -->
            <div class="ms-content-wrapper">
                <div class="row">

                    <div class="col-md-12">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb pl-0">
                                <li class="breadcrumb-item"><a href="#"><i class="material-icons">home</i> Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Ðơn hàng</li>
                            </ol>
                        </nav>


                        <div class="col-12">
                            <div class="ms-panel">
                                <div class="ms-panel-header">
                                    <h6>Danh sách đơn hàng</h6>
                                </div>
                                <div class="ms-panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-hover thead-primary">
                                            <thead>
                                                <tr>
                                                    <th scope="col">STT</th>
                                                    <th scope="col">Tên đơn hàng</th>
                                                    <th scope="col">Tên khách hàng</th>
                                                    <th scope="col">Địa chỉ</th>
                                                    <th scope="col">Trạng thái</th>
                                                    <th scope="col">Ngày đặt hàng</th>
                                                    <th scope="col" class="text-right">Tổng giá</th>
                                                    <th scope="col" class="text-center">Hoạt động</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <form action="sale_order" method="post" id="statusStart">
                                                <div class="">
                                                    <jsp:useBean class="com.team3.onlineshopping.dal.AccountDAO" id="AccCus"/>
                                                    <fmt:formatNumber value="${or_list.orTotalPrice}" pattern="###,###" var="orderPrice"/>
                                                    <c:set value="${or_list.orStatus}" var="status"/>
                                                    <tr>
                                                        <th scope="row">${count.index + 1}</th>
                                                        <td>${or_list.orTitle}</td>
                                                        <td>${AccCus.getAccByCusId(or_list.cusId).accName}</td>
                                                        <td>
                                                            ${or_list.orId}
                                                        </td>
                                                        <td>
                                                            <select name="status_${or_list.orId}" id="status_order_${or_list.orId}" style="border: 1px solid #CCC" onchange="submitForm(${or_list.orId})">
                                                                <option value="delivered" ${status eq 'delivered' ? 'selected' : ''}>Đã giao hàng</option>
                                                                <option value="pending" ${status eq 'pending' ? 'selected' : ''}>Đang xác nhận</option>
                                                                <option value="cancelled" ${status eq 'cancelled' ? 'selected' : ''}>Đã Hủy</option>
                                                            </select>
                                                        </td>
                                                        <td>${or_list.orDate}</td>
                                                        <td class="text-right">${orderPrice}đ</td>
                                                        <td class="text-center">
                                                            <span><a href="sale_orderdetails?orderId=${or_list.orId}" class="open-modal"><i class="fas fa-eye" style="color: #b1a9a9;font-size: 14px;"></i></a></span>
                                                        </td>
                                                    </tr>
                                                </div>
                                            </form>
                                            </tbody>
                                        </table>
                                        <div id="paging" class="text-right">
                                            <c:if test="${pageNumber > 1}">
                                                <a class="btn btn-warning mb-1 px-2 py-1" style="min-width: 1.25rem" href="sale_order?index=${pageNumber-1}">Trước</a>
                                            </c:if>
                                            <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                                                <a class="btn btn-warning mb-1 px-2 py-1 ${pageNumber == i ? "active":""}" style="min-width: 1.25rem" href="sale_order?index=${i}">${i}</a>
                                                <!--<a class="btn btn-warning mb-1 px-2 py-1 ${pageNumber == i ? "active":""}" style="min-width: 1.25rem" href="#">${i}</a>-->
                                            </c:forEach>
                                            <c:if test="${pageNumber < endPage}">
                                                <a class="btn btn-warning mb-1 px-2 py-1" style="min-width: 1.25rem" href="sale_order?index=${pageNumber+1}">Sau</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>


                    </div>
                </div>
            </div>

        </main>


        <!-- SCRIPTS -->
        <!-- Global Required Scripts Start -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/perfect-scrollbar.js"></script>
        <script src="assets/js/jquery-ui.min.js"></script>
        <!-- Global Required Scripts End -->

        <!-- Foodtech core JavaScript -->
        <script src="assets/js/framework.js"></script>
        <script>
                                                                function submitForm(orderId) {
                                                                    let select = document.getElementById("status_order_" + orderId);
                                                                    let status = select.value;
                                                                    document.getElementById("statusStart").action = "sale_order?orderId=" + orderId + "&status=" + status;
                                                                    document.getElementById("statusStart").submit();
                                                                }
                                                                ;

                                                                $("#paging").on("click", "a", function () {
                                                                    let index = $(this).text();
                                                                    $.ajax({
                                                                        type: 'GET',
                                                                        url: "sale_order",
                                                                        data: {index: index},
                                                                        success: function (data) {
                                                                            let row = document.getElementById("#statusStart");
                                                                            row.innerHTML += data;
                                                                        }
                                                                    });
                                                                });

        </script>
    </body>

</html>

