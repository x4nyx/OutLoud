<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<style>
    html {
        background-color:#214c84;
        background-blend-mode:overlay;
        display:flex;
        align-items:center;
        justify-content:center;
        background-repeat:no-repeat;
        background-size:cover;
        height:100%;
    }

    body {
        background-color:transparent;
    }

    .registration-cssave{
        padding:50px 0;
    }

    .registration-cssave form {
        max-width:800px;
        padding:50px 70px;
        border-radius:10px;
        box-shadow:4px 4px 15px rgba(0, 0, 0, 0.2);
        background-color:#fff;
    }

    .registration-cssave form h3 {
        font-weight:bold;
        margin-bottom:30px;
        margin-left: 80px;
    }

    .registration-cssave .item {
        border-radius:10px;
        margin-bottom:25px;
        padding:10px 20px;
    }

    .registration-cssave .create-account {
        border-radius:30px;
        padding:10px 20px;
        font-size:18px;
        font-weight:bold;
        background-color:#3f93ff;
        border:none;
        color:white;
        margin-top:20px;
        margin-left: 15px;
    }

    @media (max-width: 576px) {
        .registration-cssave form {
            padding:50px 20px;
        }
    }                                                                                                                                                                                                                                                                                                                        .no-pointerevents .form-3 p:nth-child(1):before {
                                                                                                                                                                                                                                                                                                                                 display: none;
                                                                                                                                                                                                                                                                                                                             }
</style>
<div class="registration-cssave">
    <form action= LoginServlet method = post>
        <h3 class="text-center">Вход</h3>
        <div class="form-group">
            <input class="form-control item" type="text" name="username" maxlength="15" minlength="4" pattern="^[a-zA-Z0-9_.-]*$" id="username" placeholder="Логин" required>
        </div>
        <div class="form-group">
            <input class="form-control item" type="password" name="Пароль" minlength="6" id="password" placeholder="Пароль" required>
        </div>
        <div class="form-group">
            <button class="btn btn-primary btn-block create-account" type="submit">Вход в аккаунт</button>
        </div>
    </form>
</div>

</body>
</html>
