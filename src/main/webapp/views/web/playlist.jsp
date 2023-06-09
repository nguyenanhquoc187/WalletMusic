
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

<%--  <link rel="icon" href="<c:url value="/template/web/assets/img/icon-home/wallet.png"/>" type="image/gif" sizes="16x16">--%>
<%--  <!-- reset css -->--%>
<%--  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">--%>
<%--  <!-- css grid để kết hợp chia khung và responsive -->--%>
<%--  <link rel="stylesheet" href="<c:url value="/template/web/assets/css/grid.css"/>">--%>
<%--  <!-- css mấy cái chung ban đầu -->--%>
<%--  <link rel="stylesheet" href="<c:url value="/template/web/assets/css/base.css"/>">--%>
<%--  <!-- css chính -->--%>
<%--  <link rel="stylesheet" href="<c:url value="/template/web/assets/css/main.css"/>">--%>
<%--  <!-- css để responsive trên các thiết bị -->--%>
<%--  <link rel="stylesheet" href="<c:url value="/template/web/assets/css/responsive.css"/>">--%>
<%--  <!-- nếu trình duyệt IE < 9 thì comment dưới sẽ thành code chạy dc, code scrip dước có chức năng để chạy dc media-query để responsive trên trình chuyệt thấp IE < 9 -->--%>
<%--  <!--[if lt IE 9]>--%>
<%--  <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.1/respond.js"></script>--%>
<%--  <![endif]-->--%>
<%--  <!-- dùng google font roboto -->--%>
<%--  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">--%>
<%--  <!-- icon fontawesome -->--%>
<%--  <link rel="stylesheet" href="<c:url value="/template/web/assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css"/>">--%>
</head>
<body>

<div class="main" style="background-color: #222;">
  <div class="grid">



    <div class="main-container">

      <div class="main-container-pertional js__container-panes active">

        <!-- option 2 -->
        <div style="display: block; margin-top: 100px;" class="option-playlist panes-item">
          <div class="option-all__playlist option-all__margin_bot">
            <div class="option-heading option-all__playlist-heading ">
              <h3 class="option-heading-name js__main-color">Playlist</h3>
              <div class="more-list ">
                <span class="js__main-color">Tất cả</span>
                <i class="fas fa-chevron-right js__main-color"></i>
              </div>
            </div>
            <ul class="option-all__playlist-list">
              <div class="row">
                <div class="col l-2-4 distance m-3 s-6 mobile-margin-bot-30px">
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
                          <a style="color: #fff;" href="<c:url value="/playlist?id=${item.id}"/>">
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


        </div>

      </div>

    </div>


  </div>
</div>

</body>

</html>