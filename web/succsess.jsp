<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="homepage.css">
</head>
<body>
<embed src="music/Михаил Круг - Жиган Лимон.mp3" autostart="true" hidden="true" loop="true" width="300" height="65" align="bottom"> </embed>
<header  class="header-class">
    <a href="#header"></a>
    <div style="background-color:ghostwhite">
        <nav class="navbar bg-body-tertiary"  style="background-size: 100%100vw; margin-left: 20%; margin-right: 20%; background-color:black;">
            <div class="container-fluid">
                <p><img src="img/logo.png" alt="logo" width="150px"></p>
                <form class="d-flex" role="search" action= SearchServlet method = get>
                    <input type="text" name="searchtrack" class="form-control" placeholder="Поиск треков" style="font-size:12px; border-radius: 0;" aria-label="Example text with button addon" aria-describedby="button-addon1">
                    <input type="image" name="submitbutton" src="img/lupa.png" alt="Кнопка «input»" width="30px">
                </form>
                <p><input type="image" src="img/heart.png" width="20px" height="20px" style="margin-top: 15px;"></p>
                <p><input type="image" src="img/friends.png" width="20px" height="20px" style="margin-top: 15px;"></p>
                <p><input type="image" src="img/user.png" width="20px" height="20px" style="margin-top: 15px;"></p>
            </div>
        </nav>
    </div>
</header>

<section class = "mainpage" >
    <div style="visibility: hidden;" class="simple-audio-player" id="simp" data-config='{"shide_top":false,"shide_btm":false,"auto_load":false}'>
        <div class="simp-playlist">
            <ul>
                <li><span class="simp-source" data-src="music/Coconuts - Silver Lights.mp3">Silver Lights</span><span class="simp-desc">Coconuts</span></li>
                <li><span class="simp-source" data-src="music\Eirik Suhrke - A New Morning.mp3">A New Morning</span><span class="simp-desc">Eirik Suhrke</span></li>
                <li><span class="simp-source" data-src="C:\Users\Professional\Desktop\FRONT\music\El Huervo - Crush.mp3">Crush</span><span class="simp-desc">El Huervo</span></li>
                <li class="simp-active"><span class="simp-source" data-src="music\El Huervo - Daisuke.mp3">Daisuke</span><span class="simp-desc">El Huervo</span></li>
                <li><span class="simp-source" data-src="music\El Huervo - Turf.mp3">Turf</span><span class="simp-desc">El Huervo</span></li>
                <li><span class="simp-source" data-src="music\Jasper Byrne - Hotline.mp3">Hotline</span><span class="simp-desc">Jasper Byrne</span></li>
                <li><span class="simp-source" data-src="music\Jasper Byrne - Miami.mp3">Miami</span><span class="simp-desc">Jasper Byrne</span></li>
                <li><span class="simp-source" data-src="music\M_O_O_N - Crystals.mp3">Crystals</span><span class="simp-desc">M_O_O_N </span></li>
                <li><span class="simp-source" data-src="music\M_O_O_N - Hydrogen.mp3">Hydrogen</span><span class="simp-desc">M_O_O_N</span></li>
            </ul>
        </div>
    </div>
</section>

<footer style="background-color: rgba(0, 0, 0, 0.250); padding-top: 30px;">
    <div class="container text-center">
        <div class="row justify-content-md-center">
            <div class="col col-lg-3" style="text-align: left;">
                <p class="fw-bolder">Новости</p>
                <div style="font-size: 12px">
                    Подпишитесь на наши новости и узнавайте
                    первыми о самом интересном, что происходит в
                    увлекательном мире музыки.
                </div>
                <div class="container" >
                    <form class="form-inline" role="form">
                        <div class="input-group mb-3" style="margin-top: 15px; margin-left: -13px;">
                            <input type="text" class="form-control" placeholder="Ваш E-mail адрес" style="font-size:12px; border-radius: 0;" aria-label="Example text with button addon" aria-describedby="button-addon1">
                            <button class="btn btn-outline-secondary" type="button" id="button-addon1" style="font-size:12px; border-radius: 0">Подписаться</button>
                        </div>

                    </form>
                </div>
            </div>
            <div class="col col-lg-2" style="text-align: left;">
                <p class="fw-bolder" >Полезная информация</p>
                <div class="footer-text">
                    Наши контакты<br>
                    Сотрудничество<br>
                </div>
            </div>
            <div class="col col-lg-2" style="text-align: left;">
                <p class="fw-bolder">Платная подписка</p>
                <div class="footer-text">
                    Оплата<br>
                    Политика конфиденциальности<br>
                    Преимущества подписки<br>
                </div>
            </div>
            <p style="margin-bottom: 30px;"></p>
        </div>
    </div>


    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.779)">
        <p style="color: rgba(240, 255, 255, 0.42);">Copyright © 2023 | Outloud Team </p>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
