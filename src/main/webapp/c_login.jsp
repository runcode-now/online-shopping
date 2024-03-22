
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <title>Đăng nhập</title>
        <link rel="icon" type="image/x-icon" href="Image/Avatar/icon_default.png">
        <style>

            #logreg-forms{
                font-family: sans-serif;
                background-color: #fff;
                padding: 40px 0px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                width: 56%;
                display: flex;
                justify-content: center;
                position: relative;
                right: -20%;
                margin: 4rem 0;
                border-radius: 6px;
            }

            #logreg-forms .form-control{
                border: 1px solid;
                height: 2.4rem;
                width: 28rem;
                margin-bottom: 0.667rem;
                padding: 0px 0.6rem;
            }

            .form-check-label{
                display: block;
                font-size: 1rem;
                margin-bottom: 6px;
                color: #140C40;
                font-weight: 600;
                text-align: left;

            }

            .flex{
                display: flex;
            }

            .form-login-body{
                padding-bottom: 35px;
                padding-top: 35px;
            }

            .reme-fogot,
            .form-login-footer{
                display: flex;
                justify-content: space-between;
                text-align: center;
                align-items: center;
            }

            .form-switch{
                text-decoration: none;
            }

            .non-padding{
                padding: 0 !important;
            }

            .btn-submit{
                background-color: #FFF;
                height: 42px;
                padding: 7px 43px;
                border: 2px solid #006400;
                color: #006400;
                text-transform: capitalize;
                font-size: 16px;
                border-radius: 20px;
            }

            .social-login{
                display: flex;
                justify-content: center;
                width: 100%;
            }

            .btn--with-icon{
                text-decoration: none;
            }

            .btn{
                height: 3rem;
                width: 11rem;
                border: 0;
                border-radius: 4px;
                color: #fff;
                font-weight: 600;
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 10px;
            }

            #logreg-forms .social-login a{
                color: #fff;
                background-color: #dd4b39;
                border-color: rgba(0, 0, 0, 0.2);
                width: 100%;
                text-decoration: none;
            }

            #logreg-forms .social-login a:hover{
                background-color: #c23321;
                ;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <div id="logreg-forms">
                <form class="form-login" action="com_login" method="post">
                    <div class="form-login-header">
                        <h1 class="h3 mb-3 font-weight-normal" style="font-weight: bold; text-align: center; font-size: 2rem;">Đăng nhập</h1>
                        <p style="text-align: center">Nhập chi tiết đăng nhập để có quyền truy cập</p>
                    </div>
                    <c:set var="cookie" value="${pageContext.request.cookies}"/>
                    <div class="form-login-body">
                        <p class="form-check-label">Tên đăng nhập hoặc địa chỉ email</p>
                        <input name="email" type="text" value="${cookie.cuser.value}" class="form-control"
                               placeholder="Tên đăng nhập/email" required="" autofocus=""><br>
                        <p class="form-check-label">Mật khẩu</p>
                        <input name="pass" type="password" value="${cookie.cpass.value}" class="form-control"
                               placeholder="Nhập mật khẩu" required="">
                        <div class="reme-fogot">
                            <div class="flex"> 
                                <input name="rem" value="1" type="checkbox" ${(cookie.crem!=null?'checked':'')} class="form-check-input"
                                       id="exampleCheck1" />
                                <label class="form-check-label" for="exampleCheck1">&nbsp;Ghi nhớ đăng nhập</label>
                            </div>
                            <div>
                                <a href="com_resetpass" class="form-switch">Quên mật khẩu?</a>
                            </div>
                        </div>
                    </div>

                    <div class="form-login-footer">
                        <p style="margin: 0;">Bạn không có tài khoản?
                            <a href="com_register" class="non-padding form-switch"> Đăng ký</a>
                        </p>
                        <button class="btn-submit">Đăng nhập</button>
                    </div>
                    <p class="text-danger" style="color:#140C40">${msg}</p>

                    <p style="text-align:center" > Hoặc </p>

                    <div class="social-login">
                        <a class="btn google-btn" style="height: 2.5rem;" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/onlineshopping/cus_googlelogin&response_type=code&client_id=286912111404-bm0reb1muj96f9n55sm1tkadi6gq1c3v.apps.googleusercontent.com&approval_prompt=force">
                            <i class="fab fa-google-plus-g"></i>Google
                        </a>
                    </div>

                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    </body>

</html>

