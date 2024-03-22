<%-- 
    Document   : c_newpassword
    Created on : Jan 20, 2024, 11:05:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt mật khẩu mới</title>
    <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    

    <style>
        /* =================== GENERAL =============================*/
        html,
        body {
            height: 100%;
        }

        .flex-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .flex-column {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .frame {
            padding: 50px 40px;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            margin: auto;
        }

        .frame-btn {
            padding: 5px 15px;
            border-radius: 2px;
            overflow: hidden;
            margin: auto;
            border: none;
        }

        /* ============================= LOCAL ===========================  */
        .global {
            height: 100%;
        }


        .global__form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .global__form h2 {
            padding-bottom: 10px;
            border-bottom: 1px solid rgb(184, 181, 181);
            opacity: 90%;
        }

        .frame-btn--cancel {
            background-color: rgb(219, 210, 210);
            width: 30%;
            margin: 0px;
            height: 35px;
            text-decoration: none;
            color: rgb(17, 15, 15);
            text-align: center;
        }

        .frame-btn--search {
            background-color: #FF2020;
            color: white;
            width: 60%;
            margin: 0px;
            height: 35px;
        }
        .password{

        }
        .password__title{
            margin: 0px;
            margin-left: 2px;
            font-size: 15px;
            font-weight: 600;
        }

        .error {
            margin-top: -5px;
            font-size: 13px;
            color: red;
            margin-left: 5px;
        }
    </style>
</head>

<body>
    <div class="global flex-column">
        <form action="com_newpass" class="global__form frame" method="post">
            <h2>Đặt mật khẩu mới</h2>
            <p>Tạo mật khẩu mới dài ít nhất 6 ký tự. <br>Mật khẩu mạnh có sự kết hợp của các chữ cái, chữ số</p>
            <div class="password">
                <p class="password__title">Mật khẩu mới</p>
                <input type="password" class="form-control" placeholder="Nhập mật khẩu mới" 
                       name = "new_pass" style="font-size: 12px;">
            </div>
            <p class="error">${passErr}</p>
            
            <div class="password">
                <p class="password__title">Xác nhận mật khẩu mới</p>
                <input type="password" class="form-control" placeholder="Xác nhận mật khẩu mới" 
                       name = "cf_pass" style="font-size: 12px;">
            </div>
            <p class="error">${cfpassErr}</p>
            
            <div class="flex-row">
                <a href="#" class="frame-btn frame-btn--cancel">Đóng</a>
                <button type="submit" class="frame-btn frame-btn--search">Tiếp tục</button>
            </div>
        </form>
    </div>
</body>

</html>