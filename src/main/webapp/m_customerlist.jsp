<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.team3.onlineshopping.model.Account" %>
<%@ page import="com.team3.onlineshopping.model.Customer" %>
<%@ page import="com.team3.onlineshopping.dal.AccountDAO" %>
<%@ page import="com.team3.onlineshopping.dal.CustomerDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Danh sách khách hàng</title>
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
        <!-- Page Specific CSS (Slick Slider.css) -->
        <link href="assets/css/slick.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <!-- Foodtech styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="favicon.ico">
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

            .ms-panel-body .show__title {
                flex: 1;
                margin: 0px;
                margin-left: 1rem;
            }

            .ms-panel-body .show__select {
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

            /*            .select-container{
                            display: block;
                            width: 100%;
                            height: calc(2.25rem + 2px);
                            padding: 0.375rem 0.75rem;
                            font-size: 1rem;
                            line-height: 1.5;
                            color: #fff;
                            background-color: #50B95C;
                             background-color: #EF3F32; 
                            border: none;
                            background-clip: padding-box;
                            border-radius: 0.25rem;
                            transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
                        }
                        .select-container:focus{
                            box-shadow: 0 0 0 0.1rem rgba(0,123,255,.25);
                        }*/

            .select__option {
                font-size: 14px;
                padding: 10px 0;
                transform: background-color .3s;
            }


            .edit1 {
                text-align: center;
                padding-right: 0px
            }
            .edit1__action:hover{
                cursor: pointer;
            }

            /* =================== PAGE ============================= */
            .page{
                display: flex;
                justify-content: end;
                gap: 7px;
                margin-top: 20px;
            }
            .page__number, .page__number--chosen{
                width: 35px;
                height: 35px;
                border: 1px solid #dee2e6;
                padding: 10px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .page__number p{
                margin: 0;
                font-size: 14px;
                color: rgb(102, 102, 102);
            }
            .page__number:hover{
                background-color: #E9ECEF;
            }
            /* ======= CHOSEN ================= */
            .page__number--chosen{
                background-color: #FF8306;
            }
            .page__number--chosen p{
                margin: 0;
                font-size: 14px;
                color: #fff;
            }

            .deactive{
                color: red;
                font-weight: bold;
            }
            .active{
                color: green;
                font-weight: bold;
            }


            /*================= pop up ================ */
            #toast {
                position: fixed;
                top: 80px;
                right: 35px;
                z-index: 9999; /* giá trị z-index để đảm bảo nó hiển thị phía trên các phần khác */
            }

            .toast {
                max-width: 350px;
                max-height: 55px;
                display: flex;
                align-items: center;
                gap: 20px;
                background-color: #fff;
                padding: 10px 10px;
                border-radius: 2px;
                border-left: 4px solid #47d864;
                box-shadow: 0 5px 8px rgba(0, 0, 0, 0.08);
                animation: slideInLeft ease .3s, fadeOut linear 1s 3s forwards;
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
                color: #229138;
            }
            .hide {
                display: none;
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
                <div class="row">
                    <div class="col-md-12">
                        <div id="toast">
                            <div class="hide" id="toast_class">
                                <div class="toast__icon">
                                    <div class="fas fa-check-circle"></div>
                                </div>
                                <div class="toast__body">
                                    <p class="toast__body--msg" id="messege_popup" style="margin: 0">Đăng kí tài khoản thành công</p>
                                </div>
                            </div>
                        </div>

                        <div class="ms-panel">
                            <form action="mkt_cuslist" id="user_list" method="post">
                                <div class="ms-panel-header" style="border-bottom: 0;">
                                    <h6 style="color: #FF8306;">Danh sách khách hàng</h6>
                                </div>
                                <div class="ms-panel-header filter" style="border-bottom: 0; padding-bottom: 0;">
                                    <p class="filter__title" style="font-size: 16px;">Bộ lọc</p>
                                    <div class="filter__body">
                                        <div class="row">
                                            <div class="common mb-4 col-md-6">
                                                <p class="common__title">Tên/Email/Số điện thoại</p>
                                                <input type="text" class="form-control" name="textSearch" oninput="movePage(1)"
                                                       id="textSearch" value="${param.textSearch}" placeholder="Nhập tên/email/số điện thoại"/>
                                            </div>


                                            <div class="common mb-4 col-md-3">
                                                <p class="common__title">Số tiền đã mua từ</p>
                                                <input type="text" class="form-control" id="numBegin" oninput="validateInput(this, movePage)"
                                                       name="numBegin" value="${param.numBegin}" />
                                            </div>

                                            <div class="common mb-4 col-md-3">
                                                <p class="common__title">Số tiền đã mua đến</p>
                                                <input type="text" class="form-control" id="numEnd" oninput="validateInput(this, movePage)"
                                                       name="numEnd" value="${param.numEnd}" />
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="common mb-4 col-md-3">
                                                <p class="common__title">Trạng thái</p>
                                                <select name="statusFilter" id="statusFilter" class="form-control table-user__body--select" onchange="movePage(1)">
                                                    <option value="" class="select__option select__option">Tất cả</option>
                                                    <option value="off" class="select__option select__option--disactive">Dừng
                                                        hoạt động</option>
                                                    <option value="on" class="select__option select__option--active">Đang hoạt
                                                        động</option>
                                                </select>
                                            </div>
                                            <div class="common mb-4 col-md-3">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="ms-panel-body">
                                    <div class="table-responsive">
                                        <div class="show">
                                            <p class="show__title">Hiển thị</p>
                                            <select name="quantityAccount" id="quantityAccount" class="form-control show__select" onchange="movePage(1)">
                                                <option value="10" <c:if test="${quantity == 10}">selected</c:if>>10 tài khoản</option>
                                                <option value="20" <c:if test="${quantity == 20}">selected</c:if>>20 tài khoản</option>
                                                <option value="50" <c:if test="${quantity == 50}">selected</c:if>>50 tài khoản</option>
                                                <option value="0" <c:if test="${quantity == 0}">selected</c:if>>Tất cả</option>
                                                </select>
                                            </div>

                                            <div id="data">
                                                <table class="table-user" >
                                                    <tr class="table-user__header">
                                                        <th style="width: 5%;">STT</th>
                                                        <th style="width: 15%;">Tên</th>
                                                        <th style="width: 20%;">Email</th>
                                                        <th style="width: 15%;">Số điện thoại</th>
                                                        <th style="width: 18%;">Trạng thái</th>
                                                        <th style="width: 17%;">Tổng tiền đã mua</th>
                                                        <th style="width: 10%;">Xem chi tiết</th>
                                                    </tr>

                                                <%
                                                    int index = 1;
                                                    ArrayList<Account> accList = (ArrayList<Account>) request.getAttribute("accList");
                                                    for (Account acc : accList) {       %>
                                                <tr class="table-user__body" id="row_<%=acc.getAccId()%>">
                                                    <td><%= index++%></td>
                                                    <td><%= acc.getAccName()%></td>
                                                    <td><%= acc.getAccEmail()%></td>
                                                    <td><%= acc.getAccPhone()%></td>
                                                    <td>
                                                        <%   
                                                                    if(acc.getAccStatus().equals("off")){   %>
                                                        <p class="deactive" style="margin: 0">Dừng hoạt động</p>
                                                        <%
                                                                    }else{  %>
                                                        <p class="active" style="margin: 0">Đang hoạt động</p>
                                                        <%  } 
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%   
                                                            CustomerDAO cus_dao = new CustomerDAO(); 
                                                            Customer cus = cus_dao.getByAccountId(acc.getAccId());  
                                                            double totalPurchase = cus.getCusTotalPurchase();
                                                                    
                                                            NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
                                                            String formattedTotalPurchase = nf.format(totalPurchase);   %>
                                                        <%=formattedTotalPurchase%>
                                                    </td>
                                                    <td class="edit1">
                                                        <a href="mkt_cusdetails?accId=<%=acc.getAccId()%>" target="_blank"><i 
                                                                class="far fa-eye fa-sm edit1__action" style="color: #b1a9a9;"></i></a>
                                                    </td>
                                                </tr>
                                                <%        
                                                    }
                                                
                                                %>
                                            </table>

                                            <div class="page" id="page">
                                                <c:forEach begin="1" end="${numberPage}" var="i">
                                                    <c:if test="${page == i}">
                                                        <a href="#" class="page__number--chosen" onclick="movePage(${i})"><p>${i}</p></a>
                                                            </c:if>

                                                    <c:if test="${page != i}">
                                                        <a href="#" class="page__number" onclick="movePage(${i})"><p>${i}</p></a>
                                                            </c:if>
                                                        </c:forEach>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>



        <!-- SCRIPTS -->
        <script>
            function validateInput(input, callback) {
                // Remove non-numeric and non-comma characters
                var numericValue = input.value.replace(/[^0-9,]/g, '');

                // Remove leading zeros
                numericValue = numericValue.replace(/^0+/, '');

                // Remove commas before parsing
                numericValue = numericValue.replace(/,/g, '');

                // Ensure it is a non-negative number and limit to 100 billion
                var floatValue = parseFloat(numericValue);
                var finalValue = Math.min(Math.max(0, isNaN(floatValue) ? 0 : floatValue), 100000000000);

                // Format the number with commas
                var formattedValue = finalValue.toLocaleString('en-US', {maximumFractionDigits: 2});

                // Set the formatted value to the input
                if (formattedValue == 0)
                    input.value = "";
                else
                    input.value = formattedValue;
                // Call the callback function (movePage in this case) with the argument (1)
                if (typeof callback === 'function') {
                    callback(1, finalValue);
                }
            }

            function movePage(page) {
//                document.getElementById('user_list').submit();
                event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>

                var textSearch = document.getElementById('textSearch').value;
                var numBegin = document.getElementById('numBegin').value.replace(/[^0-9]/g, '');
                ;
                var numEnd = document.getElementById('numEnd').value.replace(/[^0-9]/g, '');
                var statusFilter = document.getElementById('statusFilter').value;
                var quantityAccount = document.getElementById('quantityAccount').value;



                $.ajax({
                    url: "/onlineshopping/mkt_cuslist",
                    type: 'POST',
                    data: {// send data      
                        option: 'split',
                        textSearch: textSearch,
                        numBegin: numBegin,
                        numEnd: numEnd,
                        statusFilter: statusFilter,
                        quantityAccount: quantityAccount,
                        page: page
                    },
                    success: function (data) {

                        console.log(data);
                        // Xóa hết nội dung trong phần tử có id là "data"
                        $("#data").empty();

                        // Thêm dữ liệu HTML mới vào phần tử có id là "data"
                        $("#data").append(data);
                    }
                });

                // Lấy tham chiếu đến tất cả các thẻ <a> trong phần tử có id là "page"
                var pageLinks = $("#page a");
                // Lặp qua tất cả các thẻ <a> để xóa lớp "page__number--chosen" khỏi thẻ hiện tại
                pageLinks.removeClass("page__number--chosen").addClass("page__number");

                // Thêm lớp "page__number--chosen" cho thẻ <a> được bấm
                pageLinks.eq(page - 1).addClass("page__number--chosen").removeClass("page__number");
            }
        </script>

        <!-- SCRIPTS -->
        <!-- Global Required Scripts Start -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/perfect-scrollbar.js">
        </script>
        <script src="assets/js/jquery-ui.min.js">
        </script>
        <!-- Global Required Scripts End -->
        <!-- Page Specific Scripts Start -->

        <script src="assets/js/widgets.js"></script>
        <script src="assets/js/clients.js"></script>
        <script src="assets/js/d3.v3.min.js">
        </script>
        <script src="assets/js/topojson.v1.min.js">
        </script>
        <script src="assets/js/datatables.min.js">
        </script>
        <script src="assets/js/data-tables.js">
        </script>
        <!-- Page Specific Scripts Finish -->
        <!-- Foodtech core JavaScript -->
        <script src="assets/js/framework.js"></script>
        <!-- Settings -->
        <script src="assets/js/settings.js"></script>

    </body>



</html>
