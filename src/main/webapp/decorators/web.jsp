
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<c:url var="APIUserUrl" value="/api-user"/>
<c:url var ="PlaylistUrl" value="/playlist"/>
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
  <link rel="stylesheet" href="<c:url value="/template/web/assets/css/playlist.css"/>">

<%--  <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.1/respond.js"></script>--%>
<%--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
<%--  <script type='text/javascript' src='<c:url value="/template/admin/js/jquery-2.2.3.min.js" />'></script>--%>
<%--  <script src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js' />"></script>--%>

  <!-- dùng google font roboto -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
  <!-- icon fontawesome -->
  <link rel="stylesheet" href="<c:url value="/template/web/assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css"/>">
</head>
<body>
<div class="main" style="background-color: #222;">
  <div class="grid">
    <!-- BEGIN SIDEBAR  & CREATE PLAYLIST -->
    <%@include file="/common/web/sidebar.jsp"%>
    <!-- END SIDEBAR -->

    <%@include file="/common/web/addSong2Playlist.jsp"%>

    <!-- BEGIN CONTAINER -->
    <div class="main-container">
      <!-- header--active -->
      <%@include file="/common/web/header.jsp"%>

      <dec:body/>

    </div>
    <!-- END CONTAINER -->



    <!-- BEGIN MUSIC CONTROL -->
    <div class="main-music-control">
      <div class="row">
        <div class="col l-3 m-3 s-8">
          <div class="music-control__left">
            <div class="music-control__left-img-box">
              <div class="music-control__left-img" style="background-image: url(<c:url value="/template/web/assets/img/songs/0.webp" />);"></div>

            </div>

            <div class="music-control__left-content">
              <span class="music-control__left-content-song js__main-color">Cưới luôn được không</span>
              <span class="music-control__left-content-singer ">Út nhị Cover</span>
            </div>
            <div class="music-control__left-action  ">
              <!-- music-control__left-action-tym-box-active -->
              <div class="music-control__left-action-tym-box">
                <i class="fas fa-heart music-control__left-action-tym"></i>
                <i class="far fa-heart music-control__left-action-tym-non"></i>
              </div>
              <i class="fas fa-ellipsis-h music-control__left-action-option js__main-color js__toast"></i>
            </div>
          </div>
        </div>
        <div class="col l-6 m-7 s-4 rs__main-music-control-flex-1">
          <div class="music-control__center">
            <div class="music-control__center-action music-control__icon">
              <!-- music-control__icon-random--active -->
              <i class="fas fa-random music-control__icon1 js__music-control__icon1 js__main-color "></i>
              <i class="fas fa-caret-left music-control__icon2 js__music-control__icon2 js__main-color"></i>
              <!-- music-control__icon-play--active -->
              <div class="music-control__icon-play js__music-control__icon-play">
                <i class="fas fa-play music-control__icon3 js__main-color js__border"></i>
                <i class="fas fa-pause music-control__icon33 js__main-color js__border"></i>
              </div>
              <i class="fas fa-caret-right music-control__icon4 js__music-control__icon4 js__main-color"></i>
              <!-- music-control__icon-repeat--active -->
              <i class="fas fa-redo music-control__icon5 js__music-control__icon5  js__main-color"></i>
            </div>
            <div class="music-control__progress ">
              <span class="music-control__progress-time-start js__music-control__progress-time-start js__main-color">00:00</span>
              <input id="progress" class="music-control__progress-input" type="range" value="0" step="1" min="0" max="100">
              <!-- <div class="music-control__progress-update">
                  <div class="music-control__progress-update-timeline"></div>
              </div> -->
              <span class="music-control__progress-time-duration js__music-control__progress-time-duration js__main-color">00:00</span>
            </div>
            <audio id="audio" src="/WalletMusic_war_exploded/template/web/assets/music/list-song/0.mp3"></audio>
          </div>
        </div>
        <div class="col l-3 m-2 s-0">
          <div class="music-control__right">
            <i class="music-control__right-icon1  js__main-color js__toast fas fa-photo-video"></i>
            <i class="music-control__right-icon2  js__main-color js__toast fas fa-microphone"></i>
            <i class="music-control__right-icon3  js__main-color js__toast far fa-square"></i>
            <!-- music-control__right--active -->
            <div class="music-control__right-volume-icon">
              <i class="music-control__right-icon4 js__main-color fas fa-volume-down"></i>
              <i class="music-control__right-icon5 js__main-color fas fa-volume-mute"></i>
            </div>
            <div class="music-control__volume-progress">
              <input id="progress1" class="music-control__volume-input" type="range" value="100" step="1" min="0" max="100">
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- END MUSIC CONTROL -->
  </div>



</div>

  <c:if test="${not empty message}">
    <div class="alert alert-${alert}">
        ${message}
    </div>
  </c:if>

<!-- <div id="toast">
    <div class="toast__item">
        <i class="fa-solid fa-circle-exclamation"></i>
        <span>Chức năng này đang phát triển, bạn vui lòng thử lại sau !</span>
    </div>
</div> -->
<!-- <div class="alert alert-success">
    Xoá thành công
</div>
<div class="alert alert-error">
    Xoá thành công
</div> -->
<script src="<c:url value="/template/web/assets/javascript/main.js"/>"></script>
<script src="<c:url value="/template/web/assets/javascript/discover.js"/>"></script>
<script>

  $('#btn-create-playlist').onclick = function (e) {
    e.preventDefault();
    var formData = new FormData($('#form-create_playlist'));
    const data = {};

    for (let [key, value] of formData.entries()) {
      data[key] = value;
    }
    addPlaylist(data);
  }

  $('#btn-register').onclick = function (e) {
    e.preventDefault();
    var formData = new FormData($('#form-register'));
    const data = {};

    for (let [key, value] of formData.entries()) {
      data[key] = value;
    }
    addUser(data);
  };

  $$('.deletePlaylist').forEach((item) => {
    item.onclick = function (e) {
      e.preventDefault();
      var playlistId = item.getElementsByClassName("playlistId")[0].value;
      const data = {'id' : playlistId}
      if (confirm("Xoá Playlist?") == true) deletePlaylist(data);

    }
  });
  


  async function addUser(data) {
    try {
      const response = await fetch("http://localhost:8080/WalletMusic_war_exploded/api-user", {
        method: "POST", // or 'PUT'
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      const result = await response.json();
      window.location.href = "${HomeUrl}?action=register&message=register_success&alert=success";
    } catch (error) {
      window.location.href = "${HomeUrl}?action=register&message=username_exist&alert=error";
    }
  }

  async function addPlaylist(data) {
    try {
      const response = await fetch("http://localhost:8080/WalletMusic_war_exploded/api-playlist", {
        method: "POST", // or 'PUT'
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      const result = await response.json();
      window.location.href = "${PlaylistUrl}?action=create&message=create_success&alert=success";
    } catch (error) {
      window.location.href = "${PlaylistUrl}?action=create&message=create_error&alert=error";
    }
  }

  async function deletePlaylist(data) {
    try {
      const response = await fetch("http://localhost:8080/WalletMusic_war_exploded/api-playlist", {
        method: "DELETE", // or 'PUT'
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      const result = await response.json();
      window.location.href = "${PlaylistUrl}?action=delete&message=delete_success&alert=success";
    } catch (error) {
      window.location.href = "${PlaylistUrl}?action=delete&message=delete_error&alert=error";
    }
  }



</script>


</body>
</html>