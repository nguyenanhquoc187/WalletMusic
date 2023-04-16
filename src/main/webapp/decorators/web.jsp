
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<c:url var="APIUserUrl" value="/api-user"/>
<c:url var ="PlaylistUrl" value="/playlist"/>
<c:url var="HomeUrl" value="/dang-ky"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Wallet Music</title>

  <link rel="icon" href="<c:url value="/template/web/assets/img/icon-home/wallet.png"/>" type="image/gif" sizes="16x16">
  <!-- reset css -->
  <link rel="stylesheet" href="<c:url value="/template/web/assets/css/normalize.min.css"/>">
<%--  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">--%>
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
  <link rel="stylesheet" href="<c:url value="/template/web/assets/css/font-roboto.css"/>">
<%--  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">--%>
  <!-- icon fontawesome -->
  <link rel="stylesheet" href="<c:url value="/template/web/assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css"/>">
</head>
<body>
<div class="main" style="background-color: #222;">
  <div class="grid">
    <!-- BEGIN SIDEBAR  & CREATE PLAYLIST -->
    <%@include file="/common/web/sidebar.jsp"%>
    <!-- END SIDEBAR -->
    <c:if test="${not empty USERMODEL}">
      <%@include file="/common/web/addSong2Playlist.jsp"%>
    </c:if>


    <!-- BEGIN CONTAINER -->
    <div class="main-container">
      <!-- header--active -->
      <%@include file="/common/web/header.jsp"%>

      <dec:body/>

    </div>
    <!-- END CONTAINER -->

    <!-- BEGIN MUSIC CONTROL -->
    <%@include file="/common/web/music_control.jsp" %>
    <!-- END MUSIC CONTROL -->


  </div>



</div>

  <c:if test="${not empty message}">
    <div class="alert alert-${alert}">
        ${message}
    </div>
  </c:if>

  <div class="div-alert"></div>

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
  <c:if test="${not empty USERMODEL}">
    <%--$('.header__user-img').src = ${USERMODEL.image}--%>
  </c:if>




</script>


</body>
</html>