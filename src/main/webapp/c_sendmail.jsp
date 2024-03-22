<%-- 
    Document   : c_sendmail
    Created on : Jan 21, 2024, 1:08:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thiết lập lại mật khẩu</title>
    <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
    <link href='https://fonts.googleapis.com/css?family=Jost' rel='stylesheet'>
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

        .global__form h3 {
            padding-bottom: 10px;
            border-bottom: 1px solid rgb(184, 181, 181);
            opacity: 90%;
        }

        .frame-btn--cancel,
        .frame-btn--login {
            margin: 0px;
            height: 35px;
            text-decoration: none;
            text-align: center;
        }

        .frame-btn--cancel {
            background-color: rgb(219, 210, 210);
            width: 30%;
            color: rgb(17, 15, 15);

        }

        .frame-btn--login {
            background-color: #FF2020;
            color: white;
            width: 60%;
        }

        .send-code {
            font-size: 14px;
            border: none;
            background-color: white;
            color: rgb(46, 46, 218);
            padding: 0;
            margin-top: 1rem;
        }

        .send-code:hover {
            text-decoration: underline;
        }
    </style>

</head>

<body>
    <div class="global flex-column">
        <form action="com_resetpass" class="global__form frame" method="post">
            <h3>Thiết lập lại mật khẩu</h3>
            <p>Vui lòng kiểm tra email để đặt mật khẩu mới.<br>
            <input type="hidden" name="email" value="${email}" />
            <input type="submit" class="send-code" value="Gửi lại tin nhắn" />
            <div class="flex-row">
                <a href="com_login" class="frame-btn frame-btn--cancel">Đóng</a>
                <a href="com_login" class="frame-btn frame-btn--login">Đăng nhập</a>
            </div>
        </form>
    </div>
</body>

</html>
