<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-album"/>
<c:url var ="AlbumURL" value="/admin-album"/>
<html>
<head>
  <title>Chỉnh sửa thông tin album</title>
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
            <li class="active">Chỉnh sửa album</li>
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
              <form action="<c:url value="api-admin-album"/> " id="formSubmit" enctype="multipart/form-data">

                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Tên album</label>
                  <div class="col-sm-9">
                    <input required type="text" class="form-control" id="name" name="name" value="${model.name}"/>
                  </div>
                </div>
                <br/>
                <br/>
                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Ca sĩ phát hành</label>
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
                <br> <br>
                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Image</label>
                  <div class="col-sm-9">
                    <input required type="file" accept="image/*" class="form-control" id="image" name="image" value=""/>
                  </div>
                </div>
                <br/>
                <br/>
                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Ngày ra mắt</label>
                  <div class="col-sm-9">
                    <input required type="date" class="form-control" id="releaseDate" name="releaseDate" value="${model.releaseDate}"/>
                  </div>
                </div>
                <br/>
                <br/>


                <div class="form-group">
                  <div class="col-sm-12">
                    <c:if test="${not empty model.id}">
                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật thông tin album" id="btnAddOrUpdateNew"/>
                    </c:if>
                    <c:if test="${empty model.id}">
                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm album" id="btnAddOrUpdateNew"/>
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
          addAlbum(data);
        } else {
          updateAlbum(data);
        }


    });

    function addAlbum(data) {
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
          window.location.href = "${AlbumURL}?type=edit&id="+result.id+"&message=insert_success";
        },
        error: function (e) {
          window.location.href = "${AlbumURL}?type=list&maxPageItem=5&page=1&message=error_system";
        }
      });
    }

    function updateAlbum(data) {
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
          window.location.href = "${AlbumURL}?type=edit&id="+result.id+"&message=update_success";
        },
        error: function (e) {
          window.location.href = "${AlbumURL}?type=list&maxPageItem=5&page=1&message=error_system";
        }
      });
    }

</script>
</body>
</html>
