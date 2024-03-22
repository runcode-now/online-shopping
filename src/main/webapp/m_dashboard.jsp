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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
        <!-- Sidebar Navigation Left Begin -->
        <aside id="ms-side-nav" class="side-nav fixed ms-aside-scrollable ms-aside-left">
            <!-- Logo -->
            <div class="logo-sn ms-d-block-lg">
                <img style="max-width: 255px;" src="https://scontent.fsgn2-4.fna.fbcdn.net/v/t1.15752-9/423568413_220611387798284_6265991015599051178_n.png?_nc_cat=101&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=6xm1kK9w87wAX9TEvv_&_nc_ht=scontent.fsgn2-4.fna&oh=03_AdTwMqeyx_8lcqp09mOKsXlRojFzIL02Pvvb9Y3q1WDgZA&oe=65FA4050" alt="logo">
            </div>
            <br>
            <!-- Navigation -->
            <ul class="accordion ms-main-aside fs-14" id="side-nav-accordion">
                <!-- Dashboard -->
                <li class="menu-item">
                    <a href="mkt_dashboard"> <span><i class="fas fa-columns" style="width: 14px; height: 14px;"></i>Thống kê</span>
                    </a>
                </li>
                <!-- /Dashboard -->

                <!-- Product Begin -->
                <li class="menu-item">
                    <a href="#" class="has-chevron" data-toggle="collapse" data-target="#product" aria-expanded="false" aria-controls="product1"> <span><i class="fa fa-archive fs-16"></i>Quản lý sản phẩm</span>
                    </a>
                    <ul id="product" class="collapse" aria-labelledby="product" data-parent="#side-nav-accordion">
                        <li> <a href="mkt_productlist">Danh sách sản phẩm</a>
                        </li>  
                        <li> <a href="mkt_addproduct">Thêm sản phẩm</a>
                        </li>
                        <li> <a href="mkt_feedbacklist">Đánh giá sản phẩm</a>
                        </li>
                    </ul>
                </li>
                <!-- Product End -->

                <!-- News Begin -->
                <li class="menu-item">
                    <a href="#" class="has-chevron" data-toggle="collapse" data-target="#slider" aria-expanded="false" aria-controls="slider"> <span><i class="fas fa-newspaper"></i>Tin tức</span>
                    </a>
                    <ul id="slider" class="collapse" aria-labelledby="slider" data-parent="#side-nav-accordion">
                        <li> <a href="mkt_postlist">Danh sách bài đăng</a>
                        </li>
                        <li> <a href="mkt_postcreate"> Thêm bài đăng</a>
                        </li>
                        <li> <a href="mkt_sliderlist?cateNewsId=2" >Danh sách Slider</a>
                        </li>
                        <li> <a href="mkt_addslider?cateNewsId=2" >Thêm Slider</a>
                        </li>
                        <li> <a href="mkt_collectionlist">Danh sách bộ sưu tập</a>
                        </li>
                        <li> <a href="mkt_addcollection" >Thêm bộ sưu tập</a>
                        </li>

                    </ul>
                </li>
                <!-- News End -->

                <!-- Customer Begin -->
                <li class="menu-item">
                    <a href="mkt_cuslist"> <span><i class="fas fa-user-friends fs-16"></i>Danh sách khách hàng</span>
                    </a>
                </li>
                <!-- Customer End -->



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


            </ul>
        </aside>
        <!-- Sidebar Navigation Left End -->


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
                            <img class="ms-user-img ms-img-round float-right" src="${sessionScope.account.accAvarUrl}" alt="people">
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
                        <form action="mkt_dashboard" id="dashboard" method="post">
                            <div class="d-flex justify-content-between align-content-center mb-4">
                                <div class="d-flex justify-content-between align-items-center w-100"> 
                                    <div class="d-flex justify-content-start align-items-center w-50">
                                        <select class="form-control mx-3" name="cateproid" style="width: 30%" onchange="find_cateprode(this.value)">
                                            <c:forEach items="${cateProList}" var="catePro">
                                                <option value="${catePro.cateProId}" <c:if test="${catePro.cateProId == cateproid}">selected</c:if>>${catePro.cateProName}</option>
                                            </c:forEach>
                                        </select>
                                        <select class="form-control mx-3" name="cateprodeid" style="width: 30%" onchange="find_product()">
                                            <c:forEach items="${cateProDeList}" var="cateProDe">
                                                <option value="${cateProDe.cateProDeId}" <c:if test="${cateProDe.cateProDeId == cateprodeid}">selected</c:if>>${cateProDe.cateProDeName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <a class="m-0" href="mkt_feedbacklist">Xem chi tiết</a>
                                </div>
                            </div>
                        </form>
                        <canvas id="myChart" style="width: 100%;max-width:1000px"></canvas>
                        <p class="mt-4 text-center font-italic bold">Đánh giá trung bình của khách hàng theo sản phẩm</p>
                    </div>
                </div>
            </div>
        </main>



        <!-- SCRIPTS -->

        <script>
            function find_cateprode(cateproid){
            window.location.href = "mkt_dashboard?cateproid=" + cateproid;
            }
            function find_product(){
            document.getElementById('dashboard').submit();
            }

        </script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.1/chart.min.js" ></script>
        <script>
            var xValues = [
            <jsp:useBean class="com.team3.onlineshopping.dal.ProductDAO" id="pro_dao" />
            <c:forEach items="${chartList}" var="chart" varStatus="loop">
                <c:set var="productId" value="${Integer.parseInt(chart.key)}" />
                <c:set value="${pro_dao.getById(productId)}" var="product" />
            '${product.proName}'<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
            ];
            var yValues = [
            <c:forEach items="${chartList}" var="chart" varStatus="loop">
                ${chart.value}<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
            ];
            var barColors = [];
            <c:forEach items="${chartList}" var="chart" varStatus="loop">
                <c:choose>
                    <c:when test="${chart.value >= 4.5}">
            barColors.push('#349B30');
                    </c:when>
                    <c:when test="${chart.value < 4.5 && chart.value >= 3.5}">
            barColors.push('#8cbbc7');
                    </c:when>
                    <c:when test="${chart.value < 3.5 && chart.value >= 2.5}">
            barColors.push('#FD9A00');
                    </c:when>
                    <c:when test="${chart.value < 2.5 && chart.value >= 1.5}">
            barColors.push('#CE3806');
                    </c:when>
                    <c:when test="${chart.value < 1.5}">
            barColors.push('#FB0102');
                    </c:when>
                    <c:otherwise>
            barColors.push('#000000');
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            new Chart("myChart", {
            type: "bar",
                    data: {
                    labels: xValues,
                            datasets: [{
                            backgroundColor: barColors,
                                    data: yValues,
            <c:if test="${chartList.size() > 25 }">
                            barThickness: 15
            </c:if>
            <c:if test="${chartList.size() >= 12 && chartList.size() <= 25}">
                            barThickness: 35
            </c:if>
            <c:if test="${chartList.size() < 12}">
                            barThickness: 50 // Điều chỉnh chiều rộng của cột
            </c:if>
                            }]
                    },
                    options: {
                    scales: {
                    x: {
                    grid: {
                    display: false // Ẩn đường lưới trên trục x
                    }
                    },
                            y: {
                            beginAtZero: true, // Bắt đầu từ giá trị 0
                                    max: 5, // Giá trị tối đa trên trục y
                                    ticks: {
                                    stepSize: 1, // Khoảng cách giữa các giá trị trên trục y
                                    },
                            }
                    },
                            plugins: {
                            legend: {
                            display: false // Hiển thị chú thích
                            }
                            }
                    }
            });
        </script>

        <!-- Global Required Scripts Start -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

        <!-- Foodtech core JavaScript -->
        <script src="assets/js/framework.js"></script>
    </body>


</html>
