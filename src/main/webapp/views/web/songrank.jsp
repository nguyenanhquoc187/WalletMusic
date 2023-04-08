
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

            <div class="main-container-zingchart js__container-panes ">
                <div class="zingchart__headding js__main-color">
                    Bảng xếp hạng
                </div>
                <ul class="zingchart__list js__zingchart__list">

                    <c:forEach begin="0" end="9" var="i">
                        <li class="songs-item js__song-item0" data-index="${index}">
                            <div class="songs-item-left">
                                <span class="zingchart__item-left-stt zingchart__item-<c:choose><c:when test="${i==0}">first</c:when><c:when test="${i==1}">second</c:when><c:when test="${i==2}">third</c:when></c:choose>">${i+1}</span>
                                <span class="zingchart__item-left-line js__main-color">-</span>
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


                    <div class="zingchart__more-rank">
                        <c:forEach begin="10" end="15" var="i">
                            <li class="songs-item js__song-item0" data-index="${index}">
                                <div class="songs-item-left">
                                    <span class="zingchart__item-left-stt zingchart__item-stt">${i+1}</span>
                                    <span class="zingchart__item-left-line js__main-color">-</span>
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


                    </div>


                </ul>
                <div class="zingchart__100more">
                    <span class="zingchart__100more-body js__main-color js__border js__zingchart__100more">Xem top 100</span>
                </div>
                <div class="zingchart-week__headding zingchart__headding js__main-color">Bảng Xếp Hạng Tuần</div>
                <div class="row">
                    <div class="col l-4 laptop-l-6 m-12 s-12">
                        <div class="zingchart-week__item-wrapper js__backgroundColor">
                            <span class="zingchart-week__headding js__main-color">Nhạc trẻ</span>
                            <ul class="zingchart-week__list">
                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                            </ul>
                            <div class="zingchart__100more zingchart-week__100more">
                                <a href="<c:url value ="/the-loai" />?nhac-tre"><span class="zingchart__100more-body js__main-color js__border">Xem tất cả</span></a>
                            </div>
                        </div>
                    </div>
                    <div class="col l-4 laptop-l-6 m-12 s-12">
                        <div class="zingchart-week__item-wrapper js__backgroundColor">
                            <span class="zingchart-week__headding js__main-color">Nhạc thiếu nhi</span>
                            <ul class="zingchart-week__list">
                                <a style="cursor: pointer;" href="/<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                            </ul>
                            <div class="zingchart__100more zingchart-week__100more">
                                <a href="<c:url value ="/the-loai" />?nhac-thieu-nhi"><span class="zingchart__100more-body js__main-color js__border">Xem tất cả</span></a>
                            </div>
                        </div>
                    </div>

                    <div class="col l-4 laptop-l-6 m-12 s-12">
                        <div class="zingchart-week__item-wrapper js__backgroundColor">
                            <span class="zingchart-week__headding js__main-color">Nhạc trữ tình</span>
                            <ul class="zingchart-week__list">
                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                                <a style="cursor: pointer;" href="<c:url value ="/bai-hat" />">
                                    <li class="zingchart-week__item">
                                        <div class="zingchart-week__item-left">
                                            <span class="zingchart__item-left-stt zingchart-week__item-left-stt">1</span>
                                            <span class="zingchart__item-left-line zingchart-week__item-left-line">-</span>
                                        </div>
                                        <div class="zingchart-week__item-center">
                                            <img src="<c:url value ="/template/web/assets/img/songs/0.webp" />" alt="anh" class="zingchart-week__item-center-img">
                                            <div class="zingchart-week__item-center-content">
                                                <span class="js__main-color zingchart-week__item-center-content-name">Tình Yêu Ngủ Quên</span>
                                                <span class="js__main-color zingchart-week__item-center-content-singer">Hoàng Tôn, LyHan, Orinn Remix</span>
                                            </div>
                                        </div>
                                        <div class="zingchart-week__item-right  js__main-color">05:08</div>
                                    </li>
                                </a>

                            </ul>
                            <div class="zingchart__100more zingchart-week__100more">
                                <a href="/<c:url value ="/the-loai" />?nhac-tru-tinh"><span class="zingchart__100more-body js__main-color js__border">Xem tất cả</span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>

<script src="<c:url value="/template/web/assets/javascript/songrank.js"/>"></script>
</body>

</html>