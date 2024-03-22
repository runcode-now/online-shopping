
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Thống kê</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <!-- Iconic Fonts -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="vendors/iconic-fonts/font-awesome/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="vendors/iconic-fonts/flat-icons/flaticon.css">
        <link rel="stylesheet" href="vendors/iconic-fonts/cryptocoins/cryptocoins.css">
        <link rel="stylesheet" href="vendors/iconic-fonts/cryptocoins/cryptocoins-colors.css">
        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- jQuery UI -->
        <link href="assets/css/jquery-ui.min.css" rel="stylesheet">
        <!-- Foodtech styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <style>
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
            .statistic__number:hover{
                cursor: pointer;
            }
            .excel{
                padding: 10px 20px;
            }
            .excel:hover{
                background-color: rgb(240, 240, 240);
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
                    <a href="#" class="has-chevron" data-toggle="collapse" data-target="#order" aria-expanded="false"
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

            <div class="ms-content-wrapper">
                <div class="row flex-nowrap mx-3">
                    <div class="w-100 bg-white p-5 mr-2">
                        <form action="sale_dashboard" id="dashboard">
                            <div class="d-flex justify-content-between align-content-center mb-4">
                                <div class="d-flex justify-content-start align-content-center w-50">
                                    <p class="my-auto bold" style='text-align: end'>Xem theo</p>
                                    <select class="form-control mx-3" name="view" style="width: 30%" onchange="view_change()">
                                        <option value="day" <c:if test="${view == 'day'}">selected</c:if>>7 ngày gần nhất</option>
                                        <option value="month" <c:if test="${view == 'month'}">selected</c:if>>Tháng</option>
                                        </select>
                                    </div>
                                    <div class="" style="width: 15%">
                                        <select class="form-control" name="select" onchange="view_change()">
                                            <option value="order"<c:if test="${select == 'order'}">selected</c:if>>Đơn hàng</option>
                                        <option value="price"<c:if test="${select == 'price'}">selected</c:if>>Doanh thu</option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                            <canvas id="canvas"></canvas>
                            <p class="mt-4 text-center font-italic bold">Thống kê 
                            <c:if test="${select == 'order'}">số lượng đơn hàng</c:if>
                            <c:if test="${select == 'price'}">doanh thu</c:if>
                                theo
                            <c:if test="${view == 'day'}">tuần</c:if>
                            <c:if test="${view == 'month'}">tháng</c:if>
                            </p>

                            <div class="mb-1 mt-5 d-flex justify-content-between">
                                <p class="bold">TOP sản phẩm bán chạy</p>
                                <div> 
                                    <a href="sale_exportexcel" class="bold excel" style="color: #247e4b">
                                        <i class="fas fa-file-excel"></i>
                                        &nbsp;Xuất ra Excel
                                    </a>
                                </div>
                            </div>
                            <table class="table-user" >
                                <tr class="table-user__header text-center">
                                    <th style="width: 5%;" class="text-left">STT</th>
                                    <th style="width: 45%;">Tên sản phẩm</th>
                                    <th style="width: 15%;">Ngày tạo</th>
                                    <th style="width: 15%;">Đã bán</th>
                                    <th style="width: 10%;">Đánh giá</th>
                                    <th style="width: 10%;">Giá bán</th>
                                </tr>

                            <jsp:useBean class="com.team3.onlineshopping.dal.ProductDAO" id="pro_dao" />
                            <c:forEach items="${top5Statistic}" var="acc5" varStatus="loop">
                                <c:set var="productId" value="${Integer.parseInt(acc5.name)}" />
                                <c:set value="${pro_dao.getById(productId)}" var="product" />
                                <tr class="table-user__body">
                                    <td class="text-center">${loop.index + 1}</td>
                                    <td>
                                        <div class="d-flex align-items-center">
                                            <div style="width: 40px; height: 40px" class="d-flex align-items-center">
                                                <img class="m-0" style="width: 100%" src="${product.proImgDefault}" alt="alt"/>
                                            </div>
                                            <p class="mb-0">${product.proName}</p>
                                        </div>
                                    </td>
                                    <td class="font-italic text-center">${product.proCreatedDate}</td>
                                    <td class="text-center">${acc5.totalAccount}</td>
                                    <td class="text-center">${product.proRating}</td>
<!--                                    <td>${product.proCost}</td>-->
                                    <td class="text-right mw-3">
                                        <fmt:formatNumber type="number" value="${product.proCost}" pattern="#,##0" />đ
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                </div>
            </div>
        </main>



        <!-- SCRIPTS -->
        <!-- Line Chart-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.1/chart.min.js" ></script>
        <script>
                                        const labels = [
            <c:forEach items="${deliveredList}" var="deli" varStatus="loop">
                                        '${deli.name}'<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                                        ];
                                        var num1 = '';
                                        var num2 = '';
                                        var num3 = '';
                                        var num4 = '';
            <c:if test="${select == 'order'}">
                                        num1 = 'Số lượng đơn hàng giao thành công';
                                        num2 = 'Số lượng đơn hàng đang vận chuyển';
                                        num3 = 'Số lượng đơn hàng đang chờ xác nhận';
                                        num4 = 'Số lượng đơn hàng đã hủy';
            </c:if>

            <c:if test="${select == 'price'}">
                                        num1 = 'Doanh thu đã nhận';
                                        num2 = 'Doanh thu đơn hàng đang vận chuyển';
                                        num3 = 'Doanh thu đơn hàng đang chờ xác nhận';
                                        num4 = 'Doanh thu đơn huỷ';
            </c:if>

                                        const data = {
                                        labels: labels,
                                                datasets: [
                                                {
                                                label: num1,
                                                        backgroundColor: 'green',
                                                        borderColor: 'green',
                                                        data: [
            <c:forEach items="${deliveredList}" var="delivered" varStatus="loop">
                                                        '${delivered.totalAccount}'<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                                                        ],
                                                        tension: 0
                                                },
                                                {
                                                label: num2,
                                                        backgroundColor: 'grey',
                                                        borderColor: 'grey',
                                                        data: [
            <c:forEach items="${deliveringList}" var="delivering" varStatus="loop">
                                                        '${delivering.totalAccount}'<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                                                        ],
                                                        tension: 0
                                                },
                                                {
                                                label: num3,
                                                        backgroundColor: 'orange',
                                                        borderColor: 'orange',
                                                        data: [
            <c:forEach items="${pendingList}" var="pen" varStatus="loop">
                                                        '${pen.totalAccount}'<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                                                        ],
                                                        tension: 0
                                                },
                                                {
                                                label: num4,
                                                        backgroundColor: 'red',
                                                        borderColor: 'red',
                                                        data: [
            <c:forEach items="${cancelledList}" var="can" varStatus="loop">
                                                        '${can.totalAccount}'<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                                                        ],
                                                        tension: 0
                                                },
                                                ],
                                        }
                                        const config = {
                                        type: 'line',
                                                data: data,
                                                options: {
                                                scales: {
                                                x: {
                                                grid: {
                                                display: true, // Ẩn đường lưới
                                                },
                                                },
                                                        y: {
                                                        grid: {
                                                        display: true, // Ẩn đường lưới
                                                        },
                                                                beginAtZero: true,
                                                                max: ${maxValue},
                                                                ticks: {
                                                                stepSize: ${step}, // Điều chỉnh khoảng cách giữa các số trên trục tung
                                                                        maxRotation: 45
                                                                }
                                                        }
                                                }
                                                }
                                        }

                                        const canvas = document.getElementById('canvas');
                                        const chart = new Chart(canvas, config);
        </script>


        <script>
            function view_change(){
            document.getElementById('dashboard').submit();
            }

        </script>
        <!-- Global Required Scripts Start -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

        <!-- Foodtech core JavaScript -->
        <script src="assets/js/framework.js"></script>
    </body>


</html>
