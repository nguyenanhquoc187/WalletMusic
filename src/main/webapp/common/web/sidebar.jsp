<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>

<!-- BEGIN SIDEBAR -->
<div class="main-sidebar">
    <div class="sidebar__home">
        <a href="<c:url value ="/trang-chu" />">
            <div class="sidebar__logo"></div>
        </a>

        <a class="sidebar__name" href="<c:url value ="/trang-chu" />">Wallet Music</a>
    </div>
    <!-- <div class="sidebar__name">Music</div> -->
    <div class="sidebar__persional">
        <ul class="sidebar__list">
            <!-- sidebar__item--active -->
            <li>
                <a href="<c:url value="/ca-nhan"/>" class="sidebar__item js__sidebar-tabs js__main-color " >
                    <i class="far fa-play-circle"></i>
                    Cá Nhân
                </a>
            </li>
            <li>
                <a href="<c:url value ="/trang-chu" />" class="sidebar__item js__sidebar-tabs js__main-color sidebar__item--active">
                    <i class="fas fa-compact-disc"></i>
                    Khám Phá
                </a>
            </li>
            <li>
                <a href="<c:url value ="/xep-hang" />" class="sidebar__item js__sidebar-tabs js__main-color">
                    <i class="fas fa-chart-line"></i>
                    Bảng xếp hạng
                </a>
            </li>
            <li class="sidebar__item--has-box">
                <div class="sidebar__item js__main-color sidebar__item-genres">
                    <i class="fab fa-buromobelexperte"></i>
                    Thể loại

                    <ul class="genres-list">
                        <li class="genres-item">
                            <a href="<c:url value ="/the-loai" />">Nhạc trẻ</a>
                        </li>
                        <li class="genres-item">
                            <a href="<c:url value ="/the-loai" />">Nhạc trẻ</a>
                        </li>
                        <li class="genres-item">
                            <a href="<c:url value ="/the-loai" />">Nhạc trẻ</a>
                        </li>
                        <li class="genres-item">
                            <a href="<c:url value ="/the-loai" />">Nhạc trẻ</a>
                        </li>
                        <li class="genres-item">
                            <a href="<c:url value ="/the-loai" />">Nhạc trẻ</a>
                        </li>
                        <li class="genres-item">
                            <a href="<c:url value ="/the-loai" />">Nhạc trẻ</a>
                        </li>
                    </ul>

                </div>


            </li>
        </ul>
    </div>
    <div class="sidebar__line"></div>
    <div class="sidebar__library">
        <div class="sidebar__library-bot">
            <div class="sidebar__library-bot-heading js__main-color">
                Thư viện
                <div class="sidebar__library-bot-heading-option">
                    <i class="fas fa-pencil-alt"></i>
                </div>
            </div>
            <ul class="sidebar__list">
                <a href="<c:url value ="/playlist" />">
                    <li class="sidebar__library-bot-item sidebar__item  js__toast">
                        <i class="far fa-file-audio"></i>
                        Playlist
                    </li>
                </a>
                <a href="<c:url value ="/ca-nhan" />">
                    <li class="sidebar__library-bot-item sidebar__item  ">
                        <i class="fas fa-clock"></i>
                        Gần đây
                    </li>
                </a>
            </ul>

        </div>
    </div>
    <div class="sidebar__add-playlist">
        <i class="fas fa-plus js__main-color"></i>
        <span class="js__main-color">Tạo playlist mới</span>
    </div>

</div>
<!-- END SIDEBAR -->

<!-- CREATE PLAYLIST -->
    <div class="create-playlist">
        <div id="overlay" class=""></div>
        <div class="create-content">
            <button class="create-close">
                <i class="fas fa-times"></i>
            </button>
            <h3 class="create-content__playlist-title">Tạo playlist mới</h3>
            <form id="form-create_playlist"  action="">
                <input class="create__name-input" name="name" placeholder="Nhập tên playlist" value="">
                <button id="btn-create-playlist" class="btn-create" type="submit">
                    Tạo mới
                </button>
                <input name="userId" hidden value="${USERMODEL.id}">
            </form>
        </div>
    </div>
<!-- END CREATE PLAYLIST -->