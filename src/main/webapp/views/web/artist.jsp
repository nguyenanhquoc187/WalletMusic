
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
      <div class="main-container-pertional js__container-panes active">
        <div class="profile-artist">
          <img src="<c:url value ="/template/web/assets/img/singer/1.webp" />" alt="" class="profile-item">
          <h1 class="profile-item artist-name">Green</h1>

        </div>


        <div style="margin-top: 40px;" class="option-music panes-item active">
          <div class="option-heading option-all__song-heading">
            <h3 class="option-heading-name option-all__song-heading-left js__main-color">Bài Hát Nổi Bật</h3>

          </div>
          <div class="grid row">
            <ul style="overflow-y: auto; max-height: 400px;" class="option-music-list songs-list">

              <!-- songs-item-playing--active-onplay songs-item--active songs-item-playbtn--active -->
              <c:forEach begin="0" end="4" var="i">
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
        <div style="margin-top: 40px;" class="option-playlist">
          <div class="option-all__playlist option-all__margin_bot">
            <div class="option-heading option-all__playlist-heading ">
              <h3 class="option-heading-name js__main-color">Album</h3>
            </div>
            <ul class="option-all__playlist-list">
              <div class="row">
                <c:forEach begin="0" end="4">
                  <div class="col l-2-4 distance">
                    <div class="option-all__playlist-item">
                      <a href="<c:url value ="/album" />">
                        <div class="option-all__playlist-item-img-wrapper">
                          <div class="option-all__playlist-item-img-wrapper-action">

                            <!-- <a style="color: #fff;" href="<c:url value ="/album" />"> -->
                            <i style="color: var(--white-color);"
                               class="fas fa-play option-all__playlist-item-img-wrapper-action-icon2"></i>
                            <!-- </a> -->

                          </div>
                          <div class="option-all__playlist-item-img"
                               style="background-image: url(<c:url value ="/template/web/assets/img/playlist/1.webp" />);"></div>
                        </div>
                      </a>
                      <a style="color: var(--white-color);" href="<c:url value ="/album" />">
                        <div class="option-all__playlist-item-content">
                          <div class="option-all__playlist-item-content-name1 js__main-color">
                            Replay</div>
                        </div>
                      </a>
                    </div>
                  </div>
                </c:forEach>


              </div>
            </ul>
          </div>


        </div>
      </div>

    </div

  </div>
</div>
</body>

</html>