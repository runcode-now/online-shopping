
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="main.css">   
        <title>Đăng ký tài khoản</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <style>
            #logreg-forms{
                font-family: sans-serif;
                background-color: #fff;
                padding: 15px 0px;
                box-shadow:0 0 10px rgba(0, 0, 0, 0.2);
                width: 50%;
                display: flex;
                justify-content: center;
                position: relative;
                right: -25%;
                margin: 2rem 0;
                border-radius: 6px;
            }

            .form-check-label{
                font-size: 1rem;
                margin-bottom: 6px;
                color: #140C40;
                font-weight: 600;
                text-align: left;
            }

            .form-control{
                border: 1px solid;
                border-radius: 6px;
                height: 2.4rem;
                width: 28rem;
                margin-bottom: 0.667rem;
                padding: 0px 0.6rem;
            }

            .form-signup-footer{
                display: flex;
                justify-content: space-between;
                padding-top: 1rem;
            }

            .btn-submit{
                background-color: #FF2020;
                height: 42px;
                padding: 10px 43px;
                border: 0;
                color: #fff;
                text-transform: capitalize;
                font-size: 16px;
                border-radius: 6px;
            }

            .form-switch{
                text-decoration: none;
                color: #FF2020;
            }
        </style>
    </head>

    <body>
        <div id="logreg-forms">
            <form class="form-signup" action="com_register" method="post" onsubmit="addCurrentDate()">
                <div>
                    <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Đăng ký</h1>
                    <p style="text-align: center">Tạo tài khoản của bạn để có được toàn quyền truy cập</p>
                </div>

                <div>
                    <p class="form-check-label">Họ và tên</p>
                    <input type="text" name="username" class="form-control" value="${param.username}" placeholder="Nhập họ tên"
                           required="" autofocus=""><br>
                    <p class="error" style="color: #FF2020">${requestScope.nameUserErr}</p>

                    <p class="form-check-label">Địa chỉ Email</p>
                    <input type="text" name="email" class="form-control" value="${param.emailUser}" placeholder="Nhập địa chỉ email"
                           required="" autofocus=""><br>  
                    <p class="error" style="color: #FF2020">${requestScope.emailUserErr}</p>

                    <p class="form-check-label">Số điện thoại</p>
                    <input type="text" name="phone" class="form-control" value="${param.phone}" placeholder="Nhập số điện thoại"
                           required="" autofocus=""><br>
                    <p class="error" style="color: #FF2020">${requestScope.phoneErr}</p>

                    <p class="form-check-label">Mật khẩu</p>
                    <input type="password" name="pass" class="form-control" value="${param.password}" placeholder="Nhập mật khẩu"
                           required="" autofocus=""><br>                
                    <p class="error" style="color: #FF2020">${requestScope.passwordErr}</p>


                    <p class="form-check-label">Nhập lại mật khẩu</p>
                    <input type="password" name="passAgain" class="form-control" value="${param.passwordAgain}" placeholder="Nhập lại mật khẩu"
                           required="" autofocus=""><br>  
                    <p class="error" style="color: #FF2020">${requestScope.passwordAgainErr}</p>
                </div>
                <input type="hidden"  name="currentDate" id="currentDate" value="">
                <div class="form-signup-footer">
                    <p>Bạn đã có tài khoản?<a href="com_login" class="form-switch"> Đăng nhập</a> ở đây</p>
                    <button class="btn-submit">Đăng ký</button>
                </div>                      
            </form>
        </div>
    </body>

    <script>
        function addCurrentDate() {
        // Lấy thời gian hiện tại
        var currentDate = new Date();
        var formattedDate = currentDate.toISOString(); // Chuyển đổi thành định dạng ISO để tránh vấn đề về múi giờ

        // Gán giá trị thời gian vào trường ẩn
        document.getElementById("currentDate").value = formattedDate;

        // Nếu bạn muốn chặn sự kiện submit tại đây, thì trả về false
        return true;
    }
    </script>
    
</html>
