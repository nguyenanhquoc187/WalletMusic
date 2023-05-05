<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-song" />
<c:url var="SongURL" value="/admin-song" />
<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
              pageEncoding="UTF-8"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Danh sách bài hát</title>
</head>

<body>
<div class="main-content">


    <div class="main-content-inner">
      <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
          <li><i class="ace-icon fa fa-home home-icon"></i> <a
                  href='<c:url value = '/admin-home'></c:url>'>Trang chủ</a></li>
        </ul>
        <!-- /.breadcrumb -->
      </div>
<%--      </form>--%>

      <form action="<c:url value='/admin-song'/>" id="formSubmit"
            method="get">
      <div class="page-content">
        <div class="row">
          <div class="col-xs-12">
            <label>Tìm kiếm theo: </label>
            <select name="searchField" style="width: 150px; " class="form-select" aria-label="Default select example">
              <option value="title" disabled selected>Chọn tìm kiếm theo</option>

              <option value="title" ${model.searchField == 'title' ? "selected" : ""} >Tên bài hát</option>
              <option value="artist" ${model.searchField == 'artist' ? "selected" : ""}>Tên nghệ sĩ</option>
              <option value="album" ${model.searchField == 'album' ? "selected" : ""}>Tên album</option>
            </select>
              <input  type="text" placeholder ="Tìm kiếm ..." id="search" class="" name = "search" value="${model.search}">

              <button style="background-color: #be9ef8 !important; border-color: #be9ef8" id="btnSearch" class="btn btn-outline-secondary" name="btnSearch" type="button">Tìm kiếm</button>

            <c:if test="${not empty messageResponse}">
              <div class="alert alert-${alert}">${messageResponse}</div>
            </c:if>
            <div class="widget-box table-filter">
              <div class="table-btn-controls">
                <div class="pull-right tableTools-container">
                  <div class="dt-buttons btn-overlap btn-group">
                    <a style="margin-left: 5px" flag="info"
                       class="btn btn-white btn-info btn-bold"
                       data-toggle="tooltip" title='Thêm bài hát'
                       href='<c:url value="/admin-song?type=edit"/>'> <span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>Thêm
											</span>
                    </a>

                    <button id="btnDelete" class="btn btn-white btn-warning btn-bold">
                      <i class="ace-icon fa fa-trash-o bigger-120 orange"></i>
                      Xoá
                    </button>
<%--                    <button id="btnDelete" type="button"--%>
<%--                            class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"--%>
<%--                            data-toggle="tooltip" title='Xóa bài hats'>--%>
<%--												<span> <i class="fa fa-trash-o bigger-110 pink"></i>--%>
<%--												</span>--%>
<%--                    </button>--%>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-xs-12">
                <div class="table-responsive">
                  <table class="table table-bordered">
                    <thead>
                    <tr>
                      <th><input type="checkbox" id="checkAll"></th>
                      <th>Hình ảnh</th>
                      <th>Tên bài hát</th>
                      <th>Lời bài hát</th>
                      <th>Tổng lượt nghe</th>
                      <th>Nghệ sĩ</th>
                      <th>Album</th>
                      <%--													<th>Thể loại</th>--%>
                      <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.listResult}">
                      <tr>
                        <td><input type="checkbox" id="checkbox_${item.id}"
                                   value="${item.id}"></td>
                        <td><img style="width: 60px; height: 60px" src="<c:url value="${item.image}?<%= System.currentTimeMillis() %>"/> " alt=""> </td>
                        <td style="max-width: 100px">${item.title}</td>
                        <td  style="max-width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${item.lyrics}</td>
                        <td style="max-width: 20px">${item.countListen}</td>
                        <td>
                          <c:forEach var ="artist" items = "${item.artistList}">
                                ${artist.name}.
                          </c:forEach>
                        </td>
                        <td>${empty item.album.name ? "Single" : item.album.name}</td>
                        <td><c:url var="editURL" value="/admin-song">
                          <c:param name="type" value="edit" />
                          <c:param name="id" value="${item.id}" />
                        </c:url>
<%--                          <a class="btn btn-info">--%>
<%--                            <i class="ace-icon fa fa-pencil"></i>--%>
<%--                          </a>--%>

                          <a style="background-color: #be9ef8 !important; border-color: #be9ef8" class="btn btn-info"
                                    data-toggle="tooltip" title="Cập nhật bài hát"
                                    href='${editURL}'>
                            <i class="ace-icon fa fa-pencil"></i>
                          </a>
                        </td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                  <ul class="pagination" id="pagination"></ul>

                  <input type="hidden" value="" id="page" name="page" /> <input
                        type="hidden" value="" id="maxPageItem" name="maxPageItem" />
                  <input type="hidden" value="" id="sortName" name="sortName" />
                  <input type="hidden" value="" id="sortBy" name="sortBy" />
                  <input type="hidden" value="" id="type" name="type" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      </form>
    </div>

</div>
<!-- /.main-content -->
<script type="text/javascript">
  var totalPages = ${model.totalPage};
  var currentPage = ${model.page};
  var limit = 5;
  $(function() {
    window.pagObj = $('#pagination').twbsPagination({
      totalPages : ${model.totalPage},
      visiblePages : 10,
      startPage : currentPage,
      onPageClick : function(event, page) {
        if (currentPage != page) {
          $('#maxPageItem').val(limit);
          $('#page').val(page);
          $('#sortName').val('count_listen');
          $('#sortBy').val('desc');
          $('#type').val('list');
          $('#searchField').val('');
          $('#formSubmit').submit();
        }
      }
    });
  });

  $('#btnSearch').click(function (event) {
    event.preventDefault();
    $('#maxPageItem').val(limit);
    $('#page').val(1);
    $('#sortName').val('count_listen');
    $('#sortBy').val('desc');
    $('#type').val('list');
    $('#formSubmit').submit();

  });

  $("#btnDelete").click(function() {
    var data = {};
    var ids = $('tbody input[type=checkbox]:checked').map(function() {
      return $(this).val();
    }).get();
    data['ids'] = ids;
    deleteSong(data);
  });

  function deleteSong(data) {
    $
            .ajax({
              url : '${APIurl}',
              type : 'DELETE',
              contentType : 'application/json',
              data : JSON.stringify(data),
              success : function(result) {
                window.location.href = "${SongURL}?type=list&maxPageItem=5&page=1&message=delete_success";
              },
              error : function(error) {
                window.location.href = "${SongURL}?type=list&maxPageItem=5&page=1&message=error_system";
              }
            });
  }
</script>
</body>

</html>