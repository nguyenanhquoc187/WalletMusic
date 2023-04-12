<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-song"/>
<c:url var ="SongURL" value="/admin-song"/>
<html>
<head>
  <title>Chỉnh sửa bài hát</title>
</head>
<body>
    <div class="main-content">
      <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
          <script type="text/javascript">
            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
          </script>
          <ul class="breadcrumb">
            <li>
              <i class="ace-icon fa fa-home home-icon"></i>
              <a href="<c:url value="/admin-home"/>">Trang chủ</a>
            </li>
            <li class="active">Chỉnh sửa bài hát</li>
          </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
          <div class="row">
            <div class="col-xs-12">
              <c:if test="${not empty messageResponse}">
                <div class="alert alert-${alert}">
                    ${messageResponse}
                </div>
              </c:if>
              <form action="<c:url value="api-admin-song"/> " id="formSubmit" enctype="multipart/form-data">
                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                  <div class="col-sm-9">
                    <select class= "form-control select2" id="genresIdList" name="genresIdList" multiple>
                      <c:if test="${empty model.genresIdList}">
                        <option value="">Chọn thể loại bài hát</option>

                        <c:forEach var="item" items="${genres}">
                          <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                      </c:if>
                      <c:if test="${not empty model.genresIdList}">
                        <option value="">Chọn thể loại bài hát</option>

                        <c:forEach var="item" items="${genres}">
                          <option value="${item.id}"
                          <c:forEach var="genreId" items="${model.genresIdList}">
                                    <c:if test="${item.id == genreId}">selected="selected"</c:if>
                          </c:forEach> >
                            ${item.name}
                          </option>

                        </c:forEach>
                      </c:if>
                    </select>
                  </div>
                </div>
                <br/> <br/>
                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Nghệ sĩ</label>
                  <div class="col-sm-9">
                    <select required class="form-control select2" id="artistIdList" name="artistIdList" multiple>
                      <c:if test="${empty model.artistIdList}">
                        <option value="">Chọn nghệ sĩ</option>

                        <c:forEach var="item" items="${artist}">
                          <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                      </c:if>
                      <c:if test="${not empty model.artistIdList}">
                        <option value="">Chọn nghệ sĩ</option>

                        <c:forEach var="item" items="${artist}">
                          <option value="${item.id}"
                                  <c:forEach var="artistId" items="${model.artistIdList}">
                                    <c:if test="${item.id == artistId}">selected="selected"</c:if>
                                  </c:forEach> >
                              ${item.name}
                          </option>

                        </c:forEach>
                      </c:if>
                    </select>
                  </div>
                </div>
                <br/> <br/>
                <div  class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Album</label>
                  <div class="col-sm-9">
                    <select required class="form-control select2" id="albumId" name="albumId" >
                      <c:if test="${empty model.album}">
                        <option value="">Chọn album</option>
                        <c:forEach var="item" items="${album}">
                          <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                      </c:if>
                      <c:if test="${not empty model.album}">
                        <option value="">Chọn album</option>
                        <c:forEach var="item" items="${album}">
                          <option value="${item.id}"
                                    <c:if test="${item.id == model.albumId}">selected="selected"</c:if> >
                                    ${item.name}
                          </option>

                        </c:forEach>
                      </c:if>
                    </select>
                  </div>
                </div>
                <br/>
                <br/>
                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Tên bài hát</label>
                  <div class="col-sm-9">
                    <input required type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                  </div>
                </div>
                <br/>
                <br/>
                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Image</label>
                  <div class="col-sm-9">
                    <c:if test="${not empty model}">
                      <img style="width: 100px; border-radius: 50%; object-fit: cover;" src="<c:url value="${model.image}"/> " alt="">
                    </c:if>
                    <input style="margin-bottom: 20px" required type="file" accept="image/*" class="form-control" id="image" name="image" value=""/>
                  </div>
                </div>
                <br/>
                <br/>
                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">File audio</label>
                  <div class="col-sm-9">
                    <input style="margin-bottom: 20px" required type="file" accept="audio/*" class="form-control" id="mediaUrl" name="mediaUrl" value=""/>
                  </div>

                </div>
                <br/>
                <br/>

                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Lời bài hát</label>
                  <div class="col-sm-9">
                    <textarea rows="" cols="" id="lyrics" name="lyrics" style="width: 820px;height: 175px">${model.lyrics}</textarea>
                  </div>
                </div>
                <br/>
                <br/>
                <div class="form-group">
                  <div class="col-sm-12">
                    <c:if test="${not empty model.id}">
                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật thông tin bài hát" id="btnAddOrUpdateNew"/>
                    </c:if>
                    <c:if test="${empty model.id}">
                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bài hát" id="btnAddOrUpdateNew"/>
                    </c:if>
                  </div>
                </div>
                <input type="hidden" value="${model.id}" id="id" name="id"/>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
<script>

    $(document).ready(function() {
        $('.select2').select2({
            placeholder: 'Chọn một lựa chọn',
            allowClear: true
        });
    });


    $("#btnAddOrUpdateNew").click(function (event) {
        event.preventDefault();
        // Get form
        var form = $('#formSubmit')[0];

        // Create an FormData object
        var data = new FormData(form);
        var id = $('#id').val();
        if (id == "") {
          addSong(data);
        } else {
          updateSong(data);
        }


    });

    function addSong(data) {
      $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "${APIurl}",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (result) {
          window.location.href = "${SongURL}?type=edit&id="+result.id+"&message=insert_success";
        },
        error: function (e) {
          window.location.href = "${SongURL}?type=list&maxPageItem=5&page=1&message=error_system";
        }
      });
    }

    function updateSong(data) {
      $.ajax({
        type: "PUT",
        enctype: 'multipart/form-data',
        url: "${APIurl}",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (result) {
          window.location.href = "${SongURL}?type=edit&id="+result.id+"&message=update_success";
        },
        error: function (e) {
          window.location.href = "${SongURL}?type=list&maxPageItem=5&page=1&message=error_system";
        }
      });
    }

    const image = document.querySelector("img")
    const input = document.querySelector("input[type=file]")
    input.addEventListener("change", ()=> {
      image.src = URL.createObjectURL(input.files[0])
    });

</script>
</body>
</html>
