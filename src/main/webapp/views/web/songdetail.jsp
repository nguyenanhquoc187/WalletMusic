
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wallet Music</title>

    <link rel="icon" href="<c:url value="/template/web/assets/img/icon-home/wallet.png"/>" type="image/gif" sizes="16x16">
    <!-- reset css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- css grid để kết hợp chia khung và responsive -->
    <link rel="stylesheet" href="<c:url value="/template/web/assets/css/grid.css"/>">
    <!-- css mấy cái chung ban đầu -->
    <link rel="stylesheet" href="<c:url value="/template/web/assets/css/base.css"/>">
    <!-- css chính -->
    <link rel="stylesheet" href="<c:url value="/template/web/assets/css/main.css"/>">
    <!-- css để responsive trên các thiết bị -->
    <link rel="stylesheet" href="<c:url value="/template/web/assets/css/responsive.css"/>">
    <!-- nếu trình duyệt IE < 9 thì comment dưới sẽ thành code chạy dc, code scrip dước có chức năng để chạy dc media-query để responsive trên trình chuyệt thấp IE < 9 -->
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.1/respond.js"></script>
    <![endif]-->
    <!-- dùng google font roboto -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <!-- icon fontawesome -->
    <link rel="stylesheet" href="<c:url value="/template/web/assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css"/>">
</head>
<body>

<div class="main" style="background-color: #222;">
    <div class="grid">




        <div class="main-container">

            <!-- ------- playList --------- -->
            <div class="zing-playList">
                <div class="row">
                    <div class="col l-4">
                        <div class="zing-playList-left">
                            <div class="zing-playlist-img">
                                <div class="zing-playlist-img-rotate">
                                    <img style="width: 100%;" src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="">
                                </div>
                                <h1 style="font-size: 32px; margin: 10px 0 0 0;" class="playlist-name">Anh đã lạc vào</h1>
                                <h2 style="color: var(--white-color); font-weight: 200; filter: brightness(0.6);" class="song-artist">Green, Đại mèo remix</h2>
                                <div class="btn option-all__song-heading-right-playall-btn js__playall-0">
                                    Phát tất cả
                                </div>
                            </div>

                        </div>

                    </div>
                    <div class="col l-8">
                        <!-- song -->
                        <div class="option-all__song option-all__margin_bot">
                            <div class="option-heading option-all__song-heading ">
                                <div class="playlist__list-song-title">
                                    <h2 style="color: var(--white-color); filter: brightness(0.8); font-weight: 300;" class="option-heading-name option-all__song-heading-left js__main-color">Bài Hát</h2>
                                    <h3 style="color: var(--white-color); filter: brightness(0.8); font-weight: 300; position: relative; left: 20px;" class="option-heading-name option-all__song-heading-left js__main-color">Album</h3>

                                    <h3 style="color: var(--white-color); filter: brightness(0.8); font-weight: 300; " class="option-heading-name option-all__song-heading-left js__main-color">Thời gian</h3>
                                </div>

                            </div>
                            <div class="grid row">
                                <div class="col l-12 m-12 s-12">
                                    <div class="option-all__songs">
                                        <ul class="option-all__songs-list songs-list" style="height: 70px;">
                                            <!-- songs-item--active -->
                                            <li class="songs-item js__song-item0" data-index="${index}">
                                                <div class="songs-item-left">
                                                    <div style="background-image: url(<c:url value ="/template/web/assets/img/songs/0.webp" />);"
                                                         class="songs-item-left-img js__songs-item-left-img-0">
                                                        <div class="songs-item-left-img-playbtn"><i class="fas fa-play"></i></div>
                                                        <div class="songs-item-left-img-playing-box">
                                                            <img class="songs-item-left-img-playing"
                                                                 src="<c:url value ="/template/web/assets/img/songs/icon-playing.gif" />" alt="playing">
                                                        </div>
                                                    </div>

                                                    <div class="songs-item-left-body">
                                                        <a href="<c:url value ="/bai-hat" />">
                                                            <h3 class="songs-item-left-body-name js__main-color">Anh Đã Lạc Vào</h3>
                                                        </a>
                                                        <a  href="<c:url value ="/nghe-si" />">
                                                                <span class="songs-item-left-body-singer">Green, Đại Mèo
                                                                    Remix</span>
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="songs-item-center    ">
                                                    <a href="<c:url value ="/album" />"><span>Anh Đã Lạc Vào (Remix)</span></a>
                                                </div>
                                                <div class="songs-item-right  ">

                                                        <span class="songs-item-right-tym">
                                                            <i class="fas fa-heart songs-item-right-tym-first"></i>
                                                            <i class="far fa-heart songs-item-right-tym-last"></i>
                                                        </span>
                                                    <span class="songs-item-right-duration ">04:27</span>
                                                    <span class="songs-item-right-more js__main-color"><i
                                                            class="fas fa-ellipsis-h"></i></span>
                                                </div>
                                                <audio src="<c:url value="/template/web/assets/music/list-song/0.mp3" />" class="audio"></audio>
                                            </li>


                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="playlist-recommen" >
                            <h1 style="color: var(--white-color)">Bài hát gợi ý</h1>
                            <ul class="option-all__songs-list songs-list" style="height: 500px;">
                                <!-- songs-item--active -->
                                <c:forEach begin="1" end="6" var="i">
                                    <li class="songs-item js__song-item0" data-index="${index}">
                                        <div class="songs-item-left">
                                            <div style="background-image: url(<c:url value ="/template/web/assets/img/songs/${i}.webp" />);"
                                                 class="songs-item-left-img js__songs-item-left-img-0">
                                                <div class="songs-item-left-img-playbtn"><i class="fas fa-play"></i></div>
                                                <div class="songs-item-left-img-playing-box">
                                                    <img class="songs-item-left-img-playing"
                                                         src="<c:url value ="/template/web/assets/img/songs/icon-playing.gif" />" alt="playing">
                                                </div>
                                            </div>

                                            <div class="songs-item-left-body">
                                                <a href="<c:url value ="/bai-hat" />">
                                                    <h3 class="songs-item-left-body-name js__main-color">Anh Đã Lạc Vào</h3>
                                                </a>
                                                <a  href="<c:url value ="/nghe-si" />">
                                                    <span class="songs-item-left-body-singer">Green, Đại Mèo
                                                        Remix</span>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="songs-item-center    ">
                                            <a href="<c:url value ="/album" />"><span>Anh Đã Lạc Vào (Remix)</span></a>
                                        </div>
                                        <div class="songs-item-right  ">

                                            <span class="songs-item-right-tym">
                                                <i class="fas fa-heart songs-item-right-tym-first"></i>
                                                <i class="far fa-heart songs-item-right-tym-last"></i>
                                            </span>
                                            <span class="songs-item-right-duration ">04:27</span>
                                            <span class="songs-item-right-more js__main-color"><i
                                                    class="fas fa-ellipsis-h"></i></span>
                                        </div>
                                        <audio src="<c:url value="/template/web/assets/music/list-song/${i}.mp3" />" class="audio"></audio>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>

                    </div>
                </div>

                <div class="row">
                    <div class="col l-8">
                        <div class="lyric">
                            <h2>Lời bài hát</h2>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                            <p class="lyric-text">Đây là lời bài hát, đây là lời bài hát</p>
                        </div>
                    </div>
                    <div class="col l-4">
                        <div class="artist">
                            <img  class="artist-img" src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="">
                            <div class="artist-name">
                                <h3>Nghệ sĩ</h3>
                                <h2>Green</h2>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
</div>

</body>

</html>