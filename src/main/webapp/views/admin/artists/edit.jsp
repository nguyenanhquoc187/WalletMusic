<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-artist"/>
<c:url var ="ArtistURL" value="/admin-artist"/>
<html>
<head>
  <title>Chỉnh sửa thông tin ca sĩ</title>
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
            <li class="active">Chỉnh sửa thông tin ca sĩ</li>
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
              <form action="<c:url value="api-admin-artist"/> " id="formSubmit" enctype="multipart/form-data">

                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Tên ca sĩ</label>
                  <div class="col-sm-9">
                    <input required type="text" class="form-control" id="name" name="name" value="${model.name}"/>
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
                  <label class="col-sm-3 control-label no-padding-right">Ngày sinh</label>
                  <div class="col-sm-9">
                    <input required type="date" class="form-control" id="birthDay" name="birthDay" value="${model.birthDay}"/>
                  </div>
                </div>
                <br/>
                <br/>

                <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right">Giới tính</label>
                  <div class="col-sm-9">
                    <div class="form-check">
                      <input class="form-check-input" type="radio" value="Nữ" name="gender" id="gender1" ${model.gender == 'Nữ' ? 'checked' : ''} >
                      <label class="form-check-label" for="gender1">
                        Nữ
                      </label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="radio" value="Nam" name="gender" id="gender2" ${model.gender == 'Nam' ? 'checked' : ''} >
                      <label class="form-check-label" for="gender2">
                        Nam
                      </label>
                    </div>
                  </div>
                </div>
                <br/>
                <br/>



                <div class="form-group">
                  <div class="col-sm-12">
                    <c:if test="${not empty model.id}">
                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật thông tin ca sĩ" id="btnAddOrUpdateNew"/>
                    </c:if>
                    <c:if test="${empty model.id}">
                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm ca sĩ" id="btnAddOrUpdateNew"/>
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



    $("#btnAddOrUpdateNew").click(function (event) {
        event.preventDefault();
        // Get form
        var form = $('#formSubmit')[0];

        // Create an FormData object
        var data = new FormData(form);
        var id = $('#id').val();
        if (id == "") {
          addArtist(data);
        } else {
          updateArtist(data);
        }


    });

    function addArtist(data) {
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
          window.location.href = "${ArtistURL}?type=edit&id="+result.id+"&message=insert_success";
        },
        error: function (e) {
          window.location.href = "${ArtistURL}?type=list&maxPageItem=5&page=1&message=error_system";
        }
      });
    }

    function updateArtist(data) {
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
          window.location.href = "${ArtistURL}?type=edit&id="+result.id+"&message=update_success";
        },
        error: function (e) {
          window.location.href = "${ArtistURL}?type=list&maxPageItem=5&page=1&message=error_system";
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
