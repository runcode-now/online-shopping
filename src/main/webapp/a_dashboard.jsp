<%-- 
    Document   : a_dashboard
    Created on : Jan 10, 2024, 11:57:34 AM
    Author     : Admin
--%>

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

        </style>
    </head>

    <body class="ms-body ms-aside-left-open ms-primary-theme ms-has-quickbar">
        <!-- Sidebar Navigation Left -->
        <aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
            <!-- Logo -->
            <div class="logo-sn ms-d-block-lg">
                <img style="max-width: 255px;" src="https://scontent.fsgn2-7.fna.fbcdn.net/v/t1.15752-9/423472062_1513941472794031_1512193570186889795_n.png?_nc_cat=108&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=XRT4JijyDEAAX-eSnkR&_nc_oc=AQm7Ls7q3hNa9aYBBL0rALnbrxnhJTVTmyqQZmNbfKkE-l4E6aJMK9MC3cKzgIiFiPI&_nc_ht=scontent.fsgn2-7.fna&oh=03_AdTAZOY-GW5Nt09boLVoOBjnB7BNACIroAk--SEpLIMgYw&oe=65FA4C89" alt="logo">
            </div>
            <br>
            <!-- Navigation -->
            <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion">
                <!-- Dashboard -->
                <li class="menu-item">
                    <a href="ad_dashboard"> <span><i class="fas fa-columns"
                                                        style="width: 14px; height: 14px;"></i>Thống kê</span>
                    </a>
                </li>
                <!-- /Dashboard -->

                <!-- manage account -->
                <li class="menu-item">
                    <a href="ad_userlist"> <span><i class="fas fa-user-circle"
                                                    style="width: 14px; height: 14px;"></i>Quản lí tài khoản</span>
                    </a>
                </li>
                <!-- /manage account -->

                <!-- register account -->
                <li class="menu-item">
                    <a href="ad_register"> <span><i class="fas fa-user-plus"
                                                       style="width: 14px; height: 14px;"></i>Đăng kí tài khoản quản trị</span>
                    </a>
                </li>
                <!-- /register account -->

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
                    <div class="col-md-8 bg-white p-5 mr-2">
                        <form action="ad_dashboard" id="dashboard">
                            <div class="d-flex justify-content-lg-start align-content-center mb-4">
                                <p class="my-auto bold" style='text-align: end'>Xem theo</p>
                                <select class="w-25 form-control mx-2" name="view" onchange="view_change()">
                                    <option value="day" <c:if test="${view != 'month'}">selected</c:if>>7 ngày gần nhất</option>
                                    <option value="month" <c:if test="${view == 'month'}">selected</c:if>>Tháng</option>
                                    </select>
                                </div>
                            </form>
                            <canvas id="canvas"></canvas>
                            <c:if test="${view != 'month'}">
                                <p class="mt-4 text-center font-italic bold">Thống kê số lượng tài khoản mới đăng kí theo tuần</p>
                            </c:if>
                            <c:if test="${view == 'month'}">
                                <p class="mt-4 text-center font-italic bold">Thống kê số lượng tài khoản mới đăng kí theo tháng</p>
                            </c:if>

                        <div class="mb-1 mt-5 d-flex justify-content-between">
                            <p class="bold">TOP 5 người dùng mới</p>
                            <a href="ad_userlist" class="bold" style="color: #2f75ee">Xem thêm&nbsp;<i class="fas fa-angle-double-right" style="color: #2f75ee;"></i></a>
                        </div>
                        <table class="table-user" >
                            <tr class="table-user__header">
                                <th style="width: 5%;">STT</th>
                                <th style="width: 55%;">Tên</th>
                                <th style="width: 20%;">Ngày tạo</th>
                                <th style="width: 25%;">Vai trò</th>
                            </tr>

                            <c:forEach items="${accTop5}" var="acc5" varStatus="loop">
                                <tr class="table-user__body">
                                    <td>${loop.index + 1}</td>
                                    <td>
                                        <div class="d-flex align-items-center">
                                            <div style="width: 40px; height: 40px" class="d-flex align-items-center">
                                                <img class="m-0" style="width: 100%; height: 30px" src="${acc5.accAvarUrl}" alt="alt"/>
                                            </div>
                                            <p class="mb-0">${acc5.accName}</p>
                                        </div>
                                    </td>
                                    <td>${acc5.accCreatedDate}</td>
                                    <td>
                                        <c:if test="${acc5.roleId == 1}">Admin</c:if>
                                        <c:if test="${acc5.roleId == 2}">Salesman</c:if>
                                        <c:if test="${acc5.roleId == 3}">Marketer</c:if>
                                        <c:if test="${acc5.roleId == 4}">Khách hàng</c:if>
                                        </td>
                                    </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="col-md-4 bg-white p-0 text-center">
                        <div id="myChart"></div>
                        <p class="mx-4 text-center font-italic bold">Tỉ lệ số lượng tài khoản theo vai trò.</p>
                        <br/><br/><br/>
                        <div class="mt-5">
                            <c:forEach items="${statusList}" var="statusS">
                                <c:if test="${statusS.name == 'on'}">
                                    <div class="mb-5 mt-5">
                                        <p class="mb-0 text-success statistic__number" style="font-size: 60px;">${statusS.totalAccount}</p>
                                        <p style="font-size: 15px" class="text-success statistic__number"><i class="fas fa-circle fa-xs"></i>&nbsp;Đang hoạt động</p>
                                    </div>
                                </c:if>
                                <c:if test="${statusS.name == 'off'}">
                                    <div class="mb-5 mt-5">
                                        <p class="mb-0 text-danger statistic__number" style="font-size: 60px;">${statusS.totalAccount}</p>
                                        <p style="font-size: 15px" class="text-danger statistic__number"><i class="fas fa-circle fa-xs"></i>&nbsp;Dừng hoạt động</p>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </main>



        <!-- SCRIPTS -->
        <!-- Line Chart-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.1/chart.min.js" ></script>
        <script>
//            const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
                                    const labels = [
            <c:forEach items="${accList}" var="accS" varStatus="loop">
                                    '${accS.name}'<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                                    ];
                                    const data = {
                                    labels: labels,
                                            datasets: [
                                            {
                                            label: 'Số lượng tài khoản mới',
                                                    backgroundColor: 'green',
                                                    borderColor: 'green',
                                                    data: [
            <c:forEach items="${accList}" var="accS" varStatus="loop">
                                                    '${accS.totalAccount}'<c:if test="${!loop.last}">,</c:if>
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
                                                            max: 80,
                                                            ticks: {
                                                            stepSize: 20, // Điều chỉnh khoảng cách giữa các số trên trục tung
                                                                    maxRotation: 45
                                                            }
                                                    }
                                            }
                                            }
                                    }
                                    const canvas = document.getElementById('canvas');
                                    const chart = new Chart(canvas, config);
        </script>

        <!-- Pie Chart-->
        <script src="https://www.gstatic.com/charts/loader.js"></script>
        <script>
                                    google.charts.load('current', {'packages': ['corechart']});
                                    google.charts.setOnLoadCallback(drawChart);
                                    function getRoleName(roleId) {
                                    switch (roleId) {
                                    case 1:
                                            return 'Admin';
                                    case 2:
                                            return 'Salesman';
                                    case 3:
                                            return 'Marketer';
                                    case 4:
                                            return 'Khách hàng';
                                    default:
                                            return ''; // Hoặc giá trị mặc định khác nếu cần
                                    }
                                    }
                                    function drawChart() {
                                    // Set Data
                                    const data = google.visualization.arrayToDataTable([
                                    ['RoleId', 'Mhl'],
            <c:forEach items="${roleList}" var="roleS" varStatus="loop">
                                    [getRoleName(${roleS.name}), ${roleS.totalAccount}]<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                                    ]);
                                    // Draw
                                    const chart = new google.visualization.PieChart(document.getElementById('myChart'));
                                    chart.draw(data);
                                    }
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
