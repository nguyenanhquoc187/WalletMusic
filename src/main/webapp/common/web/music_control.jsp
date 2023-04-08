<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>
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
                    <span class="music-control__progress-time-duration js__music-control__progress-time-duration js__main-color">04:27</span>
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
