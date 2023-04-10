
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
        <div style="background-color: #402E79;" class="profile-artist">
          <h1 style="font-size: 100px;" class="profile-item artist-name">${genresModel.name}</h1>

        </div>


        <div style="margin-top: 40px;" class="option-music panes-item active">
          <div class="option-heading option-all__song-heading">
            <h3 class="option-heading-name option-all__song-heading-left js__main-color">Bài Hát Nổi Bật</h3>

          </div>
          <div class="grid row">
            <ul style="overflow-y: auto; max-height: 400px;" class="option-music-list songs-list">

              <!-- songs-item-playing--active-onplay songs-item--active songs-item-playbtn--active -->
              <c:forEach var="item" items="${songModel.listResult}" varStatus="loop">
                <c:if test="${loop.index <= 10}">
                  <li class="songs-item js__song-item0" data-index="${index}" >
                    <div class="songs-item-left">
                      <div style="background-image: url(<c:url value ="${item.image}" />);"
                           class="songs-item-left-img js__songs-item-left-img-0">
                        <div class="songs-item-left-img-playbtn"><i class="fas fa-play"></i></div>
                        <div class="songs-item-left-img-playing-box">
                          <img class="songs-item-left-img-playing"
                               src="<c:url value ="/template/web/assets/img/songs/icon-playing.gif" />" alt="playing">
                        </div>
                      </div>

                      <div class="songs-item-left-body">
                        <a href="<c:url value ="/bai-hat?id=${item.id}" />">
                          <h3 class="songs-item-left-body-name js__main-color">${item.title}</h3>
                        </a>

                        <div style="display: flex;"  class="" id="artist-link">
                          <c:forEach var="artist" items="${item.artistList}">
                            <a style="margin-right: 5px;"  href="<c:url value ="/nghe-si?id=${artist.id}" />">
                              <span class="songs-item-left-body-singer">${artist.name}</span>
                            </a>
                          </c:forEach>

                        </div>
                      </div>
                    </div>
                    <div class="songs-item-center    ">
                      <c:if test="${empty item.album}">
                        <a href="<c:url value ="/bai-hat?id=${item.id}" />"><span>${item.title} (Single)</span></a>
                      </c:if>

                      <c:if test="${not empty item.album}">
                        <a href="<c:url value ="/album?id=${item.album.id}" />"><span>${item.album.name}</span></a>
                      </c:if>
                    </div>
                    <div class="songs-item-right  ">

                      <span class="songs-item-right-duration ">${item.timePlay}</span>
                      <span class="songs-item-right-more js__main-color"><i
                              class="fas fa-ellipsis-h"></i>
                          <input hidden value="${item.id}">
                      </span>
                    </div>
                    <audio src="<c:url value="${item.mediaUrl}" />" class="audio"></audio>
                  </li>
                </c:if>
              </c:forEach>

            </ul>
          </div>
        </div>
        <div style="margin-top: 40px;" class="option-singer">
          <div class="option-all__playlist option-all__margin_bot">
            <div class="option-heading option-all__playlist-heading ">
              <h3 class="option-heading-name js__main-color">Nghệ Sĩ Nổi Bật</h3>
            </div>
            <ul class="option-all__playlist-list">
              <div class="row">
                <c:forEach  var="item" items="${artistModel.listResult}" varStatus="loop">
                  <c:if test="${loop.index < 5}">
                    <div class="col l-2-4 m-4 s-6 mobile-margin-bot-30px">
                      <a style="color: var(--white-color);" href="<c:url value ="/nghe-si?id=${item.id}" />">
                        <li class="option-all__playlist-item option-all__playlist-item-margin_top">
                          <div class="option-all__playlist-item-img-wrapper option-all__playlist-item-img-wrapper-mv">
                            <div class="option-all__playlist-item-img-wrapper-action">
                              <!-- <i class="fas fa-times option-all__playlist-item-img-wrapper-action-icon1"></i> -->
                              <i class="fas fa-play option-all__playlist-item-img-wrapper-action-icon2"></i>
                              <!-- <i class="fas fa-ellipsis-h option-all__playlist-item-img-wrapper-action-icon3"></i> -->
                            </div>
                            <div class="option-all__playlist-item-img option-all__playlist-item-img-singer" style="background-image: url(<c:url value ="${item.image}" />);"></div>
                          </div>
                          <div class="option-all__playlist-item-content-singer">
                            <div class="option-all__playlist-item-content-singer-name1 js__main-color">${item.name}</div>

                          </div>
                        </li>
                      </a>
                    </div>
                  </c:if>
                </c:forEach>


              </div>
            </ul>
          </div>
        </div>
      </div>

    </div>


  </div>
</div>

</body>

</html>