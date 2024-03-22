<%-- 
    Document   : s_order
    Created on : Feb 4, 2024, 9:37:05 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.team3.onlineshopping.dal.AddressDAO"%>
<%@page import="com.team3.onlineshopping.dal.OrderDAO"%>
<%@page import="com.team3.onlineshopping.model.Address"%>
<%@page import="com.team3.onlineshopping.model.Order"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Ðơn hàng đã giao</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
        <style>
            /* =============== FILTER ACCOUNT ====================*/
            .filter {
                position: relative;
            }

            .filter__title {
                font-size: 100px;
                position: absolute;
                top: 10px;
                left: 100px;
                background-color: white;
                padding: 0 10px;
                color: rgb(26, 185, 26);
                font-weight: bold;
            }

            .filter__body {
                margin: 40px;
                border: 1px solid rgb(231, 231, 231);
                padding: 20px 35px;
                border-radius: 5px;
                margin-top: 5px;
            }

            .filter__btn {
                position: absolute;
                left: 76%;
                top: 94%;
                padding: 3px 35px;
                border: 1px solid rgb(26, 185, 26);
                background-color: #fff;
                border-radius: 3px;
                color: rgb(11, 152, 11);
                font-weight: bold;
            }

            .filter__btn:hover {
                cursor: pointer;
                background-color: rgb(235, 235, 235);
            }

            /* =========== SHOW ====================== */
            .ms-panel-body .show {
                display: flex;
                align-items: center;
                width: 20%;
                margin-bottom: 20px;
                gap: 10px;
            }

            .show .show__title {
                flex: 1;
                margin: 0px;
                margin-left: 1rem;
            }

            .show .show__select {
                flex: 2;
            }

            /* =============== TABLE DATA  =====================*/

            .table-user {
                width: 100%;
                border-collapse: collapse;
            }

            .table-user__header {
                color: #fff;
                background-color: #FF8306;
                border-top: 1px solid rgb(194, 194, 194);
                border-bottom: 1px solid rgb(194, 194, 194);
            }

            .table-user__header th {
                padding: 10px 0;
                padding-left: 10px;
            }

            .table-user__body {
            }

            .table-user__body:hover {
                background-color: rgb(250, 250, 250);
            }

            .table-user__body td {
                border-top: 1px solid rgb(228, 228, 228);
                border-bottom: 1px solid rgb(228, 228, 228);
                padding: 15px 0;
                padding-left: 10px;
                padding-right: 20px;
            }


            .table-user__body--select {
                /* max-height: 40px; */
                width: 100%;
                padding: 5px;
            }

            .select__option {
                font-size: 14px;
                padding: 10px 0;
                transform: background-color .3s;
            }

            .select__option:hover {
                background-color: black;
            }

            .select__option--active {
                color: rgb(13, 182, 13);
            }

            .select__option--disactive {
                color: rgb(236, 17, 17);
            }

            /*==============Pagination========*/
            .btn.btn-page{
                border: 1px solid #dee2e6;
                color:  rgb(102, 102, 102);
            }

            .btn.btn-page:hover{
                background-color: #E9ECEF;
            }

            .btn-page.active{
                background-color: #ff8306;
                color: #FFF;
            }
            .btn-page.active:hover{
                background-color: #ff8306;
                color: #FFF;
            }

            /*===============pop-up================*/
            #toast {
                position: fixed;
                top: 100px;
                right: 32px;
            }

            #toast .toast {
                max-width: 300px;
                max-height: 120px;
                display: flex;
                align-items: center;
                gap: 20px;
                background-color: #fff;
                padding: 10px 10px;
                border-radius: 2px;
                border-left: 4px solid #47d864;
                box-shadow: 0 5px 8px rgba(0, 0, 0, 0.08);
                animation: slideInLeft ease 1s, fadeOut linear 2s 3s forwards;
            }

            @keyframes slideInLeft {
                from {
                    opacity: 0;
                    transform: translateX(calc(100% + 32px));
                }

                to {
                    opacity: 1;
                    transform: translateX(0);
                }
            }

            @keyframes fadeOut {
                to {
                    opacity: 0;
                }
            }


            .toast__icon {
                font-size: 22px;
                color: #47d864;
            }

            .toast__body--msg {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 17px;
                color: rgb(77, 75, 75);
                margin-bottom: 0px;
            }
            .hide {
                display: none;
            }

            /*=============================*/
            .table tbody{
                border-bottom: 1px solid rgb(204, 204, 204);
            }

            .ms-panel-header {
                border-bottom: none;
            }

            .unactive{
                color: rgba(0,0,0,0.87) !important;
            }

            .unactive:before{
                background-color: transparent !important;
            }

            .unactive:hover{
                color: #ff8306 !important;
            }
        </style>
    </head>

    <body class="ms-body ms-aside-left-open ms-primary-theme ms-has-quickbar relative">

        <!-- Sidebar Navigation Left -->
        <aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
            <!-- Logo -->
            <div class="logo-sn ms-d-block-lg">
                <img style="max-width: 255px;" src="https://scontent.fhan18-1.fna.fbcdn.net/v/t1.15752-9/423422529_931280068324876_5402123020227114441_n.png?_nc_cat=108&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=GJIOVC0k1PsAX_l36k0&_nc_ht=scontent.fhan18-1.fna&oh=03_AdT2vGnLNVUhiU4H96X6QdvfqioLWRYCd9NIi8nXo46hXg&oe=65FA97B9" alt="logo">
            </div>
            <br>
            <!-- Navigation -->
            <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion">
                <!-- Dashboard -->
                <li class="menu-item">
                    <a href="sale_dashboard"> <span><i class="fas fa-columns" style="width: 14px;
                                                       height: 14px;"></i>Thống kê</span>
                    </a>
                </li>
                <!-- /Dashboard -->

                <!-- order -->
                <li class="menu-item">
                    <a href="#" class="has-chevron active" data-toggle="collapse" data-target="#order" aria-expanded="false"
                       aria-controls="order">  <span><i class="fas fa-clipboard-list fs-16"></i>Đơn hàng</span>
                    </a>
                    <ul id="order" class="collapse" aria-labelledby="order" data-parent="#side-nav-accordion">
                        <li> <a href="sale_order" class="unactive">Tất cả</a>
                        </li>
                        <li> <a href="sale_order?type=delivered" class="active">Đơn hàng đã giao</a>
                        </li>
                        <li> <a href="sale_order?type=cancelled" class="unactive">Đơn hàng đã hủy</a>
                        </li>
                        <li> <a href="sale_order?type=pending" class="unactive">Đơn hàng đang chờ xử lý</a>
                        </li>
                        <li> <a href="sale_order?type=delivering" class="unactive">Đơn hàng đang giao</a>
                        </li>
                    </ul>
                </li>
                <!-- order -->


                <!-- ========= PROFILE ============================= -->
                <p style="border-bottom: 1px solid rgb(177, 177, 177); width: 80%; margin: 20px 22px"></p>
                <li class="menu-item">
                    <a href="com_upprofile"> <span><i class="fas fa-file-invoice" style="width: 14px; height: 14px;"></i>Chỉnh
                            sửa thông tin</span>
                    </a>
                </li>
                <!-- change password  -->
                <!-- change password -->
                <li class="menu-item">
                    <a href="com_changepass"> <span><i class="fas fa-key" style="width: 14px; height: 14px;"></i>Đổi mật
                            khẩu</span>
                    </a>
                </li>
                <!-- change password  -->
                <!-- change password -->
                <li class="menu-item">
                    <a href="com_logout"> <span><i class="fas fa-sign-out-alt" style="width: 14px; height: 14px;"></i>Đăng
                            xuất</span>
                    </a>
                </li>
                <!-- change password  -->
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
                <div style="width: 25%; padding-top: 10px;">
                    <img style="width: 100%;"
                         src="https://scontent.fhan3-1.fna.fbcdn.net/v/t1.15752-9/422913501_374119282031008_2624180467215836246_n.png?_nc_cat=105&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=Qv6VonjNR50AX8G2nc_&_nc_ht=scontent.fhan3-1.fna&oh=03_AdRJCufJs3uUgbXd6mUFtWjrq5-GLQdKVLFt5ehtX0aDqg&oe=65E9FB2A"
                         alt="">
                </div>
                <ul class="ms-nav-list ms-inline mb-0" id="ms-nav-options">
                    <li class="ms-nav-item ms-nav-user dropdown">
                        <a href="#" id="userDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img class="ms-user-img ms-img-round float-right" style="width: 40px;height: 40px; object-fit: cover;border-radius: 50%;"
                                 src="${sessionScope.account.accAvarUrl}" alt="people">
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right user-dropdown" aria-labelledby="userDropdown">
                            <li class="dropdown-menu-header">
                                <h6 class="dropdown-header ms-inline m-0"><span class="text-disabled">${sessionScope.account.accName}</span>
                                </h6>
                            </li>
                            <li class="dropdown-divider"></li>
                            <li class="ms-dropdown-list">
                                <a class="media fs-14 p-2" href="com_upprofile"> <span><i class="flaticon-user mr-2"></i> Hồ sơ</span>
                                </a>
                                <a class="media fs-14 p-2" href="com_changepass"> <span><i class="flaticon-security mr-2"></i> Đổi mật
                                        khẩu</span>
                                </a>
                            </li>
                            <li class="dropdown-divider"></li>
                            <li class="dropdown-menu-footer">
                                <a class="media fs-14 p-2" href="com_logout"> <span><i class="flaticon-shut-down mr-2"></i>
                                        Đăng xuất</span>
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
                                <li class="breadcrumb-item"><a href="#"><i class="material-icons">home</i> Thống kê</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Ðơn hàng</li>
                            </ol>
                        </nav>

                        <div class="col-12">
                            <div class="ms-panel">
                                <div class="ms-panel-header">
                                    <h6 style="color: #ff8306;">Danh sách đơn hàng</h6>
                                </div>
                                <!--=============filter begin ========================-->
                                <div class="ms-panel-header filter" style="border-bottom: 0;padding-bottom: 0;">
                                    <p class="filter__title" style="font-size: 16px;">Bộ lọc</p>
                                    <form action="sale_order" method="GET">
                                        <input type="hidden" name="type" value="delivered"/>
                                        <div class="filter__body">
                                            <div class="row">
                                                <!--=========filter by name=========-->
                                                <div class="common mb-4 col-md-6">
                                                    <p class="common__title">Tên đơn hàng</p>
                                                    <input type="text" class="form-control" name="tilteOrder" value="${tilteOrder}" placeholder="Tên đơn hàng/ Tên khách hàng/ Địa chỉ"/>
                                                </div>

                                                <!--==============filter by time========-->
                                                <div class="common mb-4 col-md-3">
                                                    <p class="common__title">Từ ngày</p>
                                                    <input name="beginDate" type="date" class="form-control" value="${beginDate}" />
                                                </div>
                                                <div class="common mb-4 col-md-3">
                                                    <p class="common__title">Đến ngày</p>
                                                    <input name="endDate" type="date" class="form-control" value="${endDate}" />
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="common mb-4 col-md-6"></div>

                                                <!--=======filter by price==========-->
                                                <div class="common mb-4 col-md-3">
                                                    <p class="common__title">Từ Giá</p>
                                                    <input name="beginPrice" type="number" class="form-control" value="${beginPrice}" />
                                                </div>
                                                <div class="common mb-4 col-md-3">
                                                    <p class="common__title">Đến Giá</p>
                                                    <input name="endPrice" type="number" class="form-control" value="${endPrice}" />
                                                </div>
                                            </div>
                                        </div>
                                        <input type="submit" class="filter__btn" value="Tìm kiếm">
                                    </form>
                                </div>
                                <!--=============filter end ==========================-->

                                <!--==============list begin================-->
                                <div class="ms-panel-body">
                                    <div class="table-responsive" style="overflow: unset">
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
                                                <c:forEach items="${requestScope.orderList}" var="or_list" varStatus="count">
                                                    <jsp:useBean class="com.team3.onlineshopping.dal.AccountDAO" id="AccCus"/>
                                                    <jsp:useBean class="com.team3.onlineshopping.dal.AddressDAO" id="add_dao"/>
                                                    <fmt:formatNumber value="${or_list.orTotalPrice}" pattern="###,###" var="orderPrice"/>
                                                    <c:set value="${or_list.orStatus}" var="status"/>
                                                    <tr>
                                                        <th scope="row">${requestScope.startIndex + count.index + 1}</th>
                                                        <td>${or_list.orTitle}</td>
                                                        <td>${AccCus.getAccByCusId(or_list.cusId).accName}</td>
                                                        <td>
                                                            <c:set var="orderId" value="${or_list.orId}" />
                                                            <fmt:parseNumber var="orderIdParsed" type="number" value="${orderId}" />
                                                            <c:set var="address" value="${add_dao.getAddByOrderId(orderIdParsed).getAddName()}" />
                                                            <c:set var="addressArray" value="${fn:split(address, ',')}" />
                                                            ${addressArray[fn:length(addressArray) - 1]}
                                                        </td>
                                                        <td>
                                                            <c:if test="${status eq 'delivered'}">
                                                                <span class="badge badge-success">Đã giao hàng</span>
                                                            </c:if>
                                                        </td>
                                                        <td>${or_list.orDate}</td>
                                                        <td class="text-right">${orderPrice}đ</td>
                                                        <td class="text-center">
                                                            <span>
                                                                <a href="sale_orderdetails?orderId=${or_list.orId}">
                                                                    <i class="fas fa-eye" style="color: #b1a9a9; font-size: 14px;"></i>
                                                                </a>
                                                                <a href="javascript:void(0)" id="deleteButton" onclick="confirmDelete(${or_list.orId})">
                                                                    <i class="fa-regular fa-trash-can" style="color: #999;"></i>
                                                                </a>
                                                            </span>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </form>
                                            </tbody>
                                        </table>
                                        <c:if test="${empty requestScope.orderList}">
                                            <div class="d-flex justify-content-center"><p style="font-size: 24px;">Không có đơn hàng nào!</p></div>
                                        </c:if>
                                        <!--Begin Pagination-->
                                        <div class="d-flex justify-content-between">
                                            <div class="d-flex align-items-center text-uppercase h6 mb-0">
                                                Hiển thị ${pageNumber} trên ${endPage} trang
                                            </div>
                                            <div class="text-right mr-1">
                                                <c:if test="${pageNumber > 1}">
                                                    <a class="btn btn-page mb-1 px-2 py-1" style="min-width: 1.25rem" 
                                                       href="sale_order?type=delivered&index=${pageNumber-1}&tilteOrder=${tilteOrder}&beginDate=${beginDate}&endDate=${endDate}&statusOrder=${statusOrder}&beginPrice=${beginPrice}&endPrice=${endPrice}">Trước</a>
                                                </c:if>

                                                <c:choose>
                                                    <c:when test="${requestScope.endPage > 5}">
                                                        <c:set var="startPage" value="${pageNumber - 2}" />
                                                        <c:if test="${startPage < 1}">
                                                            <c:set var="startPage" value="1" />
                                                        </c:if>

                                                        <c:set var="endPage" value="${startPage + 4}" />
                                                        <c:if test="${endPage > requestScope.endPage}">
                                                            <c:set var="endPage" value="${requestScope.endPage}" />
                                                            <c:set var="startPage" value="${endPage - 4}" />
                                                        </c:if>

                                                        <c:forEach begin="${startPage}" end="${endPage}" var="i">
                                                            <a class="btn btn-page mb-1 px-2 py-1 ${pageNumber == i ? 'active' : ''}" style="min-width: 1.25rem" 
                                                               href="sale_order?type=delivered&index=${i}&tilteOrder=${tilteOrder}&beginDate=${beginDate}&endDate=${endDate}&statusOrder=${statusOrder}&beginPrice=${beginPrice}&endPrice=${endPrice}">${i}</a>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                                                            <a class="btn btn-page mb-1 px-2 py-1 ${pageNumber == i ? 'active' : ''}" style="min-width: 1.25rem" 
                                                               href="sale_order?type=delivered&index=${i}&tilteOrder=${tilteOrder}&beginDate=${beginDate}&endDate=${endDate}&statusOrder=${statusOrder}&beginPrice=${beginPrice}&endPrice=${endPrice}">${i}</a>

                                                        </c:forEach>
                                                    </c:otherwise>
                                                </c:choose>

                                                <c:if test="${pageNumber < endPage}">
                                                    <a class="btn btn-page mb-1 px-2 py-1" style="min-width: 1.25rem" 
                                                       href="sale_order?type=delivered&index=${pageNumber+1}&tilteOrder=${tilteOrder}&beginDate=${beginDate}&endDate=${endDate}&statusOrder=${statusOrder}&beginPrice=${beginPrice}&endPrice=${endPrice}">Sau</a>
                                                </c:if>
                                            </div>
                                        </div>
                                        <!--End Pagination-->

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <!--pop-up notification-->
        <div id="toast" class="hide">
            <div class="toast">
                <div class="toast__icon">
                    <div class="fas fa-check-circle"></div>
                </div>
                <div class="toast__body">
                    <p class="toast__body--msg">Chuyển trạng thái thành công</p>
                </div>
            </div>
        </div>
        <!--pop-up notification-->                              

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
                                                                        let select = $("#status_order_" + orderId);
                                                                        let status = select.val();

                                                                        $.ajax({
                                                                            url: 'sale_order',
                                                                            type: 'POST',
                                                                            data: {
                                                                                orderId: orderId,
                                                                                status: status
                                                                            },
                                                                            success: function (response) {
                                                                                document.getElementById('toast').classList.remove('hide');
                                                                                setTimeout(function () {
                                                                                    document.getElementById('toast').classList.add('hide');
                                                                                }, 1500);
                                                                            }
                                                                        });
                                                                    }
                                                                    
                                                                    function confirmDelete(orderId) {
                var type = '${requestScope.type}';
                var confirmation = confirm("Bạn có chắc muốn xóa không?");
                if (confirmation) {
                    if (type !== null) {
                        window.location.href = "sale_deleteorder?orderId=" + orderId + "&type=" + type;
                    } else {
                        window.location.href = "sale_deleteorder?orderId=" + orderId;
                    }
                }
            }
        </script>
    </body>

</html>

