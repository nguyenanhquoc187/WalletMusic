
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

<%--    <link rel="icon" href="<c:url value="/template/web/assets/img/icon-home/wallet.png"/>" type="image/gif" sizes="16x16">--%>
<%--    <!-- reset css -->--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">--%>
<%--    <!-- css grid để kết hợp chia khung và responsive -->--%>
<%--    <link rel="stylesheet" href="<c:url value="/template/web/assets/css/grid.css"/>">--%>
<%--    <!-- css mấy cái chung ban đầu -->--%>
<%--    <link rel="stylesheet" href="<c:url value="/template/web/assets/css/base.css"/>">--%>
<%--    <!-- css chính -->--%>
<%--    <link rel="stylesheet" href="<c:url value="/template/web/assets/css/main.css"/>">--%>
<%--    <!-- css để responsive trên các thiết bị -->--%>
<%--    <link rel="stylesheet" href="<c:url value="/template/web/assets/css/responsive.css"/>">--%>
<%--    <!-- nếu trình duyệt IE < 9 thì comment dưới sẽ thành code chạy dc, code scrip dước có chức năng để chạy dc media-query để responsive trên trình chuyệt thấp IE < 9 -->--%>
<%--    <!--[if lt IE 9]>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.1/respond.js"></script>--%>
<%--    <![endif]-->--%>
<%--    <!-- dùng google font roboto -->--%>
<%--    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">--%>
<%--    <!-- icon fontawesome -->--%>
<%--    <link rel="stylesheet" href="<c:url value="/template/web/assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css"/>">--%>
</head>
<body>

<div class="main" style="background-color: #222;">
    <div class="grid">
        <div class="main-container">

            <!-- ------- playList --------- -->
            <div class="zing-playList">
                <div class="row">
                    <div class="col l-4">
                        <div class="zing-playList-left">
                            <div class="zing-playlist-img">
                                <div class="zing-playlist-img-rotate">
                                    <img style="width: 320px;" src="<c:url value ="${model.image}" />" alt="">
                                </div>
                                <h1 class="playlist-name">${model.name}</h1>
<%--                                <div class="btn option-all__song-heading-right-playall-btn js__playall-0">--%>
<%--                                    Phát tất cả--%>
<%--                                </div>--%>
                            </div>

                        </div>

                    </div>
                    <div class="col l-8">
                        <!-- song -->
                        <div class="option-all__song option-all__margin_bot">
                            <div class="option-heading option-all__song-heading ">
                                <div class="playlist__list-song-title">
                                    <h2 style="color: var(--white-color);" class="option-heading-name option-all__song-heading-left js__main-color">Bài Hát</h2>
                                    <h3 style="color: var(--white-color); position: relative; left: 21px;" class="option-heading-name option-all__song-heading-left js__main-color">Album</h3>

                                    <h3 style="color: var(--white-color); " class="option-heading-name option-all__song-heading-left js__main-color">Thời gian</h3>
                                </div>

                            </div>
                            <div class="grid row">
                                <div class="col l-12 m-12 s-12">
                                    <div class="option-all__songs">
                                        <ul class="option-all__songs-list songs-list" style="max-height: 320px;">
                                            <!-- songs-item--active -->
                                            <c:forEach var="item" items="${songModel.listResult}">
                                                <li class="songs-item js__song-item0" data-index="${index}">
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
                                                            <div style="display: flex;"  class="" >
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
                                                        <span  class="songs-item-right-delete js__main-color">
                                                            <i class="fas fa-trash"></i>
                                                            <input hidden value="${item.id}">
                                                        </span>
                                                    </div>
                                                    <audio src="<c:url value="${item.mediaUrl}" />" class="audio"></audio>
                                                </li>
                                            </c:forEach>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="playlist-recommen" >
                            <h1 style="color: var(--white-color)">Bài hát gợi ý</h1>
                            <ul class="option-all__songs-list songs-list" style="height: 500px;">
                                <!-- songs-item--active -->
                                <c:forEach var="item" items="${songSuggest.listResult}">
                                    <li class="songs-item js__song-item0" data-index="${index}">
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
                                                <div style="display: flex;"  class="" >
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
                                            <span  class="songs-item-right-more js__main-color">
                                                            <i class="fas fa-ellipsis-h"></i>
                                                            <input hidden value="${item.id}">
                                                        </span>
                                        </div>
                                        <audio src="<c:url value="${item.mediaUrl}" />" class="audio"></audio>
                                    </li>
                                </c:forEach>


                            </ul>
                        </div>

                    </div>
                </div>
            </div>

        </div>

    </div>
</div>

<script>
    if (document.querySelector('.songs-item-right-delete') !== null) {

        document.querySelectorAll('.songs-item-right-delete').forEach((item) => {
            item.onclick = function() {
                if (confirm("Xoá bài hát này khỏi playlist?") == true) {
                    let songId = item.getElementsByTagName("input")[0].value;
                    let href = window.location.href;
                    let playlistId = href.substring(href.search('id')+3);
                    let url = "http://localhost:8080/WalletMusic_war_exploded/api-playlistInc";
                    url = url + "?playlistId=" + playlistId + "&" + "songId=" + songId;
                    var alert = document.querySelector('.div-alert');
                    fetch(url, {
                        method: 'DELETE',
                    })
                        .then(response => response.json()) // chuyển đổi response sang dạng JSON
                        .then(data => {

                            const htmlString = `
                                        <div class="alert alert-success">
                                            Xoá thành công
                                        </div>
                                    `;
                            alert.innerHTML = htmlString;
                            var delayInMilliseconds = 600; //1 second
                            setTimeout(function() {
                                window.location.href = href;
                            }, delayInMilliseconds);


                        })
                        .catch(error => {

                            const htmlString = `
                                        <div class="alert alert-error">
                                            Lỗi hệ thống
                                        </div>
                                    `;
                            alert.innerHTML = htmlString;
                        });
                }


            }


        });



    }
</script>

</body>

</html>