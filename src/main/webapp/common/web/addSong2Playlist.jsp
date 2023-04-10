<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>

<div class="add-playlist ">
  <div id="overlay-add-playlist" class=""></div>
  <div class="create-content">
    <button class="add-close">
      <i class="fas fa-times"></i>
    </button>
    <h3 style="margin-top: 30px;" class="create-content__playlist-title">Thêm bài hát vào playlist</h3>
    <ul class="playlist-list">
      <c:forEach items="${playlistModel.listResult}" var="item">
        <form class="form__add-song">
          <li class="playlist-item">${item.name}</li>
          <span style="color: var(--white-color);" class="header__setting-line"></span>
          <input hidden value="${item.id}">
        </form>
      </c:forEach>

    </ul>
  </div>
</div>