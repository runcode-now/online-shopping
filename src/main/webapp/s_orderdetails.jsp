<%-- 
    Document   : s_orderdetails
    Created on : Feb 6, 2024, 3:25:53 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">


    <!-- Mirrored from slidesigma.com/themes/html/foodtech/pages/prebuilt-pages/invoice.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 31 Jan 2024 16:51:19 GMT -->
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Chi tiết đơn hàng</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <!-- Iconic Fonts -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="vendors/iconic-fonts/flat-icons/flaticon.css">
        <link href="vendors/iconic-fonts/font-awesome/css/all.min.css" rel="stylesheet">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery UI -->
        <link href="assets/css/jquery-ui.min.css" rel="stylesheet">
        <!-- Foodtech styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="favicon.ico">
        <style>
            /*================All===================*/
            .invoice-address input[name = "orderTitle"]:focus{
                border-color: #000;
            }

            /*==============pop-up====================*/
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
        </style>
    </head>

    <body class="ms-body ms-aside-left-open ms-primary-theme ms-has-quickbar">

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
                        <li> <a href="sale_order?type=delivered" class="unactive">Đơn hàng đã giao</a>
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
                                <li class="breadcrumb-item"><a href="sale_order">Đơn hàng</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Chi tiết đơn hàng</li>
                            </ol>
                        </nav>

                        <div class="ms-panel">
                            <div class="ms-panel-header header-mini">
                                <div class="d-flex justify-content-between">
                                    <fmt:formatNumber value="${requestScope.order.orId}" pattern="DH000000" var="orderId"/>
                                    <h6>Đơn hàng</h6>
                                    <h6>#${orderId}</h6>
                                </div>
                            </div>
                            <div class="ms-panel-body">
                                <!-- Order Details -->
                                <div class="row align-items-center">
                                    <div class="col-md-6">
                                        <div class="invoice-address">
                                            <h3>${requestScope.order.orTitle}</h3>
                                            <h5>Tên khách hàng: ${requestScope.customer.accName}</h5>
                                            <p class="mb-0" >Địa chỉ: ${requestScope.address.addName}</p>
                                        </div>
                                    </div>
                                    <div class="col-md-6 text-md-right">
                                        <ul class="invoice-date">
                                            <li>Ngày đặt hàng: <span>${requestScope.order.orDate}</span></li>
                                            <li>Phương thức thanh toán: ${requestScope.payMethod}</li>
                                            <li>Trạng thái đơn hàng: 
                                                <span class="badge
                                                      <c:choose>
                                                          <c:when test="${requestScope.status eq 'Đã giao hàng'}">badge-success</c:when>
                                                          <c:when test="${requestScope.status eq 'Đã hủy'}">badge-danger</c:when>
                                                          <c:when test="${requestScope.status eq 'Chờ xử lý'}">badge-light</c:when>
                                                          <c:otherwise>badge-primary</c:otherwise>
                                                      </c:choose> ">
                                                    ${requestScope.status}</span>
                                            </li>

                                        </ul>
                                    </div>
                                </div>

                                <!--editing status & title-->
                                <c:if test="${requestScope.status ne 'Đã giao hàng' && requestScope.status ne 'Đã hủy'}" >
                                    <form id="editFrom" action="sale_orderdetails" class="row align-items-center" method="POST" style="display: none;width: 100%;">
                                        <div class="col-md-6">
                                            <div class="invoice-address">
                                                <input type="hidden" name="orderId" value="${requestScope.order.orId}"/>
                                                <h3>${requestScope.order.orTitle}</h3>
                                                <h5>Tên khách hàng: ${requestScope.customer.accName}</h5>
                                                <p class="mb-0" >Địa chỉ: ${requestScope.address.addName}</p>
                                            </div>
                                        </div>
                                        <div class="col-md-6 text-md-right">
                                            <ul class="invoice-date">
                                                <li>Ngày đặt hàng: <span>${requestScope.order.orDate}</span></li>
                                                <li>Phương thức thanh toán: ${requestScope.payMethod}</li>
                                                <li>Trạng thái đơn hàng: 
                                                    <select name="status" style="border: 1px solid #CCC">
                                                        <option value="delivered">Đã giao hàng</option>
                                                        <option value="pending" <c:if test="${status eq 'pending'}">selected</c:if>>Ðang giao hàng</option>
                                                        <option value="delivering" <c:if test="${status eq 'delivering'}">selected</c:if>>Chờ xử lý</option>
                                                            <option value="cancelled" >Đã Hủy</option>
                                                        </select>
                                                    </li>
                                                    <li><button class="btn btn-pill btn-success" type="submit"">Thay đổi</button></li>
                                                </ul>
                                            </div>
                                        </form>
                                </c:if>

                                <!--Product-->
                                <div class="ms-invoice-table table-responsive mt-5">
                                    <table class="table table-hover text-right thead-light">
                                        <thead>
                                            <tr class="text-capitalize">
                                                <th class="text-center w-5">STT</th>
                                                <th class="text-left">Sản phẩm</th>
                                                <th class="text-left">Phân loại</th>
                                                <th>Đơn giá</th>
                                                <th>Số lượng</th>
                                                <th>Chi phí</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.orderDetails}" var="or_details" varStatus="count">
                                                <jsp:useBean class="com.team3.onlineshopping.dal.ProductDAO" id="product"/>
                                                <jsp:useBean class="com.team3.onlineshopping.dal.CategoryProductDetailsDAO" id="Category"/>
                                                <fmt:formatNumber value="${product.getById(or_details.proId).proPrice}" pattern="###,###" var="orderPrice"/>
                                                <fmt:formatNumber value="${or_details.orDeQuantity * product.getById(or_details.proId).proPrice}" pattern="###,###" var="totalPrice"/>
                                                <tr>
                                                    <td class="text-center">${count.index + 1}</td>
                                                    <td class="text-left ms-table-f-w">
                                                        <img style="width: 35px; height: 30px;" src="${product.getById(or_details.proId).proImgDefault}" alt="people">&nbsp; ${product.getById(or_details.proId).proName}</td>
                                                    <td class="text-left">${Category.getCateDeByProId(product.getById(or_details.proId).proId).cateProDeName}</td>
                                                    <td>${orderPrice}đ</td>
                                                    <td>${or_details.orDeQuantity}</td>
                                                    <td>${totalPrice}đ</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <fmt:formatNumber value="${requestScope.order.orTotalPrice}" pattern="###,###" var="orderTotalPrice"/>
                                                <td colspan="5">Tổng chi phí: </td>
                                                <td>${orderTotalPrice}đ</td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                    <div class="invoice-buttons d-flex justify-content-between">
                                        <c:if test="${requestScope.type == null}">
                                            <a href="sale_order" class="btn btn-primary ml-2 mb-2">Quay lại</a>
                                        </c:if>
                                        <c:if test="${requestScope.type != null}">
                                            <a href="sale_order?type=${type}" class="btn btn-primary ml-2 mb-2">Quay lại</a>
                                        </c:if>
                                        <div class="d-flex justify-content-between">
                                            <c:if test="${requestScope.status ne 'Đã giao hàng' && requestScope.status ne 'Đã hủy'}">
                                                <a href="javascript:void(0)" class="btn btn-primary mr-2 mb-2" onclick="showFormEdit()">Chỉnh sửa</a>
                                            </c:if>
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
                    <p class="toast__body--msg"></p>
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
                                                    function showFormEdit() {
                                                        let editForm = document.getElementById("editFrom");
                                                        let rowAlignItems = document.querySelector(".row.align-items-center");
                                                        let requestStatus = "${requestScope.status}";

                                                        if (requestStatus === 'Đã giao hàng' || requestStatus === 'Đã hủy') {
                                                            document.getElementById('toast').classList.remove('hide');
                                                            setTimeout(function () {
                                                                document.getElementById('toast').classList.add('hide');
                                                            }, 2000);
                                                        } else {
                                                            if (editForm.classList.contains("d-flex")) {
                                                                editForm.classList.remove("d-flex");
                                                            } else {
                                                                editForm.classList.add("d-flex");
                                                            }

                                                            if (rowAlignItems.classList.contains("ms-d-block-sm")) {
                                                                rowAlignItems.classList.remove("ms-d-block-sm");
                                                            } else {
                                                                rowAlignItems.classList.add("ms-d-block-sm");
                                                            }
                                                        }
                                                    }




        </script>
    </body>


</html>

