<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>
<div class="header-wrapper">
    <div class="header">
        <div class="header__undo  ">
            <i class="fas fa-arrow-left hover js__toast"></i>
            <i class="fas fa-arrow-right hover js__toast header__undo--disble"></i>
        </div>
        <div class="header__width-search js__backgroundColor">
            <i class="fas fa-search header__width-search-icon "></i>

            <input placeholder="Nhập tên bài hát, nghệ sĩ . . ." type="text" class="header__width-search-input">
            <div  class="header__width-search-sub js__suggest-keywords">
                <div class="overlay-search" ></div>
                <span class="header__width-search-sub-header js__main-color">Đề xuất cho bạn</span>
                <ul class="header__width-search-sub-list ">

                    <c:forEach items="${songSuggest.listResult}" var="item">
                        <li class="header__width-search-sub-item">
                            <a  href="<c:url value ="/tim-kiem?keyword=${item.title}" />" class="header__width-search-sub-item-link">
                                <i class="fas fa-arrows-alt-h "></i>
                                <span class="">${item.title}</span>
                            </a>
                        </li>
                    </c:forEach>



                </ul>
            </div>

            <div style="display: none;" class="header__width-search-sub js__related-keywords">
                <div class="overlay-search js_overlay-search" ></div>
                <span class="header__width-search-sub-header js__main-color">Từ khoá liên quan</span>
                <ul class="header__width-search-sub-list js-search">
                </ul>
            </div>

        </div>
        <div class="header__right">

            <!-- header__setting--active -->
            <div style="display: ${not empty USERMODEL ? "none" : "block"}"   class="header__login">
                    <a class="header__login-btn" href="#">
                        Đăng nhập
                    </a>
                    <div  class="form-login">
                        <div id="overlay-login" class=""></div>
                        <div class="create-content">
                            <button class="close-login">
                                <i class="fas fa-times"></i>
                            </button>
                            <h4 class="create-content__playlist-title"></h4>
                            <form action="<c:url value='/dang-nhap'/>" method="post">
                                <input name="username" required type="text" class="create__name-input" placeholder="Username" value="">
                                <input name="password" required style="margin-top: 20px;" type="password" class="create__name-input" placeholder="Password" value="">
                                <input type="hidden" value="login" name="action"/>
                                <button type="submit" class="btn-create" >
                                    Đăng nhập
                                </button>

                            </form>
                        </div>
                    </div>
                </div>

<%--            <div ${not empty USERMODEL ? style="display: none"}class="header__logout">--%>
<%--                <a href="#">--%>
<%--                    Đăng xuất--%>
<%--                </a>--%>
<%--            </div>--%>

            <div style="display: ${not empty USERMODEL ? "none" : "block"}"  class="header__register">
                    <a class="header__register-btn" href="#">
                        Đăng ký
                    </a>
                    <div  class="form-register">
                        <div id="overlay-register" class=""></div>
                        <div class="create-content">
                            <button class="close-register">
                                <i class="fas fa-times"></i>
                            </button>
                            <h4 class="create-content__playlist-title"></h4>
                            <form id="form-register" action="" method="post"   >
                                <input  type="text" class="create__name-input" name="name" placeholder="Họ và tên" value="">
                                <input  style="margin-top: 20px;" type="text" class="create__name-input" name="username" placeholder="Tên đăng nhập" value="">
                                <input  style="margin-top: 20px;" type="password" class="create__name-input" name="password" placeholder="Mật khẩu" value="">
                                <input  style="margin-top: 20px;" type="tel" class="create__name-input" name="phoneNumber" placeholder="Số điện thoại" pattern="[0-9]{1,}" value="">
                                <input  style="margin-top: 20px;" type="email" class="create__name-input" name="email" placeholder="Email" value="">
                                <input  style="margin-top: 20px;" type="date" class="create__name-input" name="birthDay" placeholder="Ngày sinh" value="">
                                <span class="title">Giới tính</span>
                                <div class="register-gender">
                                    <label style="margin-left: 10px;"  for="gender1">Nữ</label>
                                    <input   type="radio"  id="gender1"  name="gender" value="Nữ">
                                    <label style="margin-left: 10px;"  for="gender2">Nam</label>
                                    <input  type="radio" id="gender2"  name="gender" value="Nam">
                                </div>


                                <button id="btn-register" type="submit" class="btn-create" >
                                    Đăng ký
                                </button>
                            </form>
                        </div>
                    </div>
                </div>

            <div class="header__setting  js__backgroundColor">
                <i class="fas fa-cog header__setting-icon"></i>
                <ul class="header__setting-list">
                    <li class="header__setting-item">
                        <i class="fas fa-info-circle"></i>
                        Giới thiệu
                    </li>
                    <li class="header__setting-item">
                        <i class="far fa-flag"></i>
                        Góp ý
                    </li>
                    <li class="header__setting-item">
                        <i class="fas fa-phone-alt"></i>
                        Liên hệ
                    </li>
                    <li class="header__setting-item">
                        <i class="fab fa-adversal"></i>
                        Quảng cáo
                    </li>
                    <li class="header__setting-item">
                        <i class="fas fa-notes-medical"></i>
                        Thoả thuận sử dụng
                    </li>
                    <li class="header__setting-item">
                        <i class="fas fa-shield-alt"></i>
                        Chính sách bảo mật
                    </li>

                </ul>


            </div>

                <div style="display: ${empty USERMODEL ? "none" : "block"}"   class="header__user">
                    <!-- <a href=""> -->
                    <img src="<c:url value="/template/web/assets/img/header/user/default-user-avatar.jpg"/>" alt="user" class="header__user-img">
                    <!-- </a> -->
                    <ul class="header__user-setting-list">
                        <a href="">
                            <li class="header__user-setting-item">
                                <i class="fas fa-user-tie"></i>
                                Thay đổi thông tin và avatar
                            </li>
                        </a>

                        <a href="<c:url value="/thoat?action=logout" />">
                            <li class="header__user-setting-item">

                                <i class="fas fa-sign-out-alt"></i>
                                Đăng xuất

                            </li>
                        </a>

                    </ul>
                </div>
        </div>
    </div>
</div>