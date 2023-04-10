
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

    <!-- BEGIN CONTAINER -->
    <div class="main-container">


      <div class="main-container-discover js__container-panes ">
        <!-- part slider -->
        <div class="container-discover__slider">
          <div class="container-discover__slider-item container-discover__slider-item-first">
            <img src="<c:url value="/template/web/assets/img/slider-discover/1.jpg"/>" alt="anh" class="container-discover__slider-item-img">
          </div>
          <div class="container-discover__slider-item container-discover__slider-item-second">
            <img src="<c:url value="/template/web/assets/img/slider-discover/2.jpg"/>" alt="anh" class="container-discover__slider-item-img">
          </div>
          <div class="container-discover__slider-item container-discover__slider-item-third">
            <img src="<c:url value="/template/web/assets/img/slider-discover/3.jpg"/>" alt="anh" class="container-discover__slider-item-img">
          </div>
          <div class="container-discover__slider-item container-discover__slider-item-four">
            <img src="<c:url value="/template/web/assets/img/slider-discover/4.jpg"/>" alt="anh" class="container-discover__slider-item-img">
          </div>
          <div class="container-discover__slider-left  js__container-discover__slider-left">
            <i class="fas fa-chevron-left js__main-color"></i>
          </div>
          <div class="container-discover__slider-right  js__container-discover__slider-right">
            <i class="fas fa-chevron-right js__main-color"></i>
          </div>
        </div>
        <!-- part playlist -->
        <c:if test="${not empty USERMODEL}">
          <div class="container-discover__playlist option-all__playlist option-all__margin_bot">
            <div class="option-heading option-all__playlist-heading">
              <h3 class="option-heading-name white-color js__main-color">Playlist</h3>
              <div class="more-list ">
                <span class="js__main-color">Tất cả</span>
                <i class="fas fa-chevron-right js__main-color"></i>
              </div>
            </div>
            <ul class="option-all__playlist-list">
              <div class="row">
                <div class="col l-2-4 distance ">
                  <li style="min-height: 215px;" class="option-all__playlist-item0">
                    <i class="fas fa-plus"></i>
                    <span>Tạo playlist mới</span>
                  </li>
                </div>

                <c:forEach var="item" items="${playlistModel.listResult}">
                  <div class="col l-2-4 distance">
                    <li class="option-all__playlist-item">
                      <div  class="option-all__playlist-item-img-wrapper">
                        <div class="option-all__playlist-item-img-wrapper-action">
                          <a class="deletePlaylist" style="color: #fff" href="">
                            <i class="fas fa-times option-all__playlist-item-img-wrapper-action-icon1"></i>
                            <input class="playlistId" hidden value="${item.id}">
                          </a>
                          <a style="color: #fff;" href="<c:url value="/playlist?id=${item.id}"/> ">
                            <i class="fas fa-play option-all__playlist-item-img-wrapper-action-icon2"></i>
                          </a>
                          <i class="fas fa-ellipsis-h option-all__playlist-item-img-wrapper-action-icon3"></i>
                        </div>
                        <div class="option-all__playlist-item-img" style="background-image: url(<c:url value="${item.image}"/>);"></div>
                      </div>
                      <div class="option-all__playlist-item-content">
                        <a href="playlistdetail.jsp"><div style="color: var(--white-color);" class="option-all__playlist-item-content-name1 js__main-color">${item.name}</div></a>
                      </div>
                    </li>
                  </div>
                </c:forEach>


              </div>
            </ul>
          </div>
        </c:if>


        <div class="option-all__playlist option-all__margin_bot">
          <div class="option-heading option-all__playlist-heading ">
            <h3 class="option-heading-name js__main-color">Album</h3>
          </div>
          <ul class="option-all__playlist-list">
            <div class="row">
              <c:forEach var="item" items="${albumModel.listResult}">
                <div class="col l-2-4 distance">
                  <div class="option-all__playlist-item">
                    <a href="<c:url value ="/album?id=${item.id}" />">
                      <div class="option-all__playlist-item-img-wrapper">
                        <div class="option-all__playlist-item-img-wrapper-action">

                          <!-- <a style="color: #fff;" href="<c:url value ="/album" />"> -->
                          <i style="color: var(--white-color);"
                             class="fas fa-play option-all__playlist-item-img-wrapper-action-icon2"></i>
                          <!-- </a> -->

                        </div>
                        <div class="option-all__playlist-item-img"
                             style="background-image: url(<c:url value="${item.image}" />);"></div>
                      </div>
                    </a>
                    <a style="color: var(--white-color);" href="<c:url value ="/album?id=${item.id}" />">
                      <div class="option-all__playlist-item-content">
                        <div class="option-all__playlist-item-content-name1 js__main-color">
                          ${item.name}</div>
                      </div>
                    </a>
                  </div>
                </div>
              </c:forEach>

            </div>
          </ul>
        </div>



        <div style="margin-left: 0;" class="option-singer">
          <div class="option-all__playlist option-all__margin_bot">
            <div class="option-heading option-all__playlist-heading ">
              <h3 class="option-heading-name js__main-color">Nghệ Sĩ Nổi Bật</h3>
            </div>
            <ul class="option-all__playlist-list">
              <div class="row">
                <c:forEach var="item" items="${artistModel.listResult}">
                  <div class="col l-2-4 m-4 s-6 ">
                    <a style="color: var(--white-color);" href="<c:url value ="/nghe-si?id=${item.id}" />">
                      <li
                              class="option-all__playlist-item option-all__playlist-item-margin_top  ">
                        <div
                                class="option-all__playlist-item-img-wrapper option-all__playlist-item-img-wrapper-mv">
                          <div class="option-all__playlist-item-img-wrapper-action">
                            <!-- <i class="fas fa-times option-all__playlist-item-img-wrapper-action-icon1"></i> -->
                            <i
                                    class="fas fa-play option-all__playlist-item-img-wrapper-action-icon2"></i>
                            <!-- <i class="fas fa-ellipsis-h option-all__playlist-item-img-wrapper-action-icon3"></i> -->
                          </div>
                          <div class="option-all__playlist-item-img option-all__playlist-item-img-singer"
                               style="background-image: url(<c:url value="${item.image}" />);">
                          </div>
                        </div>
                        <div class="option-all__playlist-item-content-singer">
                          <div
                                  class="option-all__playlist-item-content-singer-name1 js__main-color">
                            ${item.name}</div>

                        </div>
                      </li>
                    </a>
                  </div>
                </c:forEach>

              </div>
            </ul>
          </div>
        </div>
      </div>

    </div>
    <!-- END CONTAINER -->

  </div>



</div>

<!-- <div id="toast">
    <div class="toast__item">
        <i class="fa-solid fa-circle-exclamation"></i>
        <span>Chức năng này đang phát triển, bạn vui lòng thử lại sau !</span>
    </div>
</div> -->


<%--    <div class="alert alert-success">--%>
<%--        Xoá thành công--%>
<%--    </div>--%>
<%--    <div class="alert alert-error">--%>
<%--        Xoá thành công--%>
<%--    </div> --%>

</body>
</html>