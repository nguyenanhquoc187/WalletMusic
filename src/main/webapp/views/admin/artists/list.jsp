<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-artist" />
<c:url var="ArtistURL" value="/admin-artist" />
<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
              pageEncoding="UTF-8"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Danh sách ca sĩ</title>
</head>

<body>
<div class="main-content">
  <form action="<c:url value='/admin-artist'/>" id="formSubmit"
        method="get">
    <div class="main-content-inner">
      <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
          <li><i class="ace-icon fa fa-home home-icon"></i> <a
                  href='<c:url value = '/admin-home'></c:url>'>Trang chủ</a></li>
        </ul>
        <!-- /.breadcrumb -->
      </div>
      <div class="page-content">
        <div class="row">
          <div class="col-xs-12">
              <input type="text" placeholder ="Tìm kiếm ..." id="search" class="" name = "search" value="${model.search}">

            <button  style="background-color: #be9ef8 !important; border-color: #be9ef8" id="btnSearch" class="btn btn-outline-secondary" type="button">Tìm kiếm</button>



            <c:if test="${not empty messageResponse}">
              <div class="alert alert-${alert}">${messageResponse}</div>
            </c:if>
            <div class="widget-box table-filter">
              <div class="table-btn-controls">
                <div class="pull-right tableTools-container">
                  <div class="dt-buttons btn-overlap btn-group">
                    <a flag="info"
                       class="btn btn-white btn-info btn-bold"
                       data-toggle="tooltip" title='Thêm ca sĩ'
                       href='<c:url value="/admin-artist?type=edit"/>'> <span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>Thêm
											</span>
                    </a>

                    <button id="btnDelete" class="btn btn-white btn-warning btn-bold">
                      <i class="ace-icon fa fa-trash-o bigger-120 orange"></i>
                      Xoá
                    </button>
<%--                    <button id="btnDelete" type="button"--%>
<%--                            class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"--%>
<%--                            data-toggle="tooltip" title='Xóa ca sĩ'>--%>
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
                      <th>Tên ca sĩ</th>
                      <th>Ngày sinh</th>
                      <th>Giới tính</th>
                      <th>Tổng số bài hát</th>
                      <th>Tổng số album</th>
                      <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${model.listResult}">
                      <tr>
                        <td><input type="checkbox" id="checkbox_${item.id}"
                                   value="${item.id}"></td>
                        <td><img style="width: 60px; height: 60px" src="<c:url value="${item.image}"/> " alt=""> </td>
                        <td style="max-width: 100px">${item.name}</td>
                        <td  style="max-width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${item.birthDay}</td>
                        <td style="max-width: 20px">${item.gender}</td>
                        <td>
                            ${item.totalSong}
                        </td>
                        <td>
                            ${item.totalAlbum}
                        </td>

                        <td><c:url var="editURL" value="/admin-artist">
                          <c:param name="type" value="edit" />
                          <c:param name="id" value="${item.id}" />
                        </c:url>


                          <a style="background-color: #be9ef8 !important; border-color: #be9ef8" class="btn btn-info"
                                    data-toggle="tooltip" title="Cập nhật thông tin ca sĩ"
                                    href='${editURL}'><i class="fa fa-pencil-square-o"
                                                         aria-hidden="true"></i> </a></td>
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
    </div>
  </form>
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
          $('#sortName').val('name');
          $('#sortBy').val('asc');
          $('#type').val('list');
          // $('#search').val('');
          $('#formSubmit').submit();
        }
      }
    });
  });

  $('#btnSearch').click(function (event) {
    event.preventDefault();
    $('#maxPageItem').val(limit);
    $('#page').val(1);
    $('#sortName').val('name');
    $('#sortBy').val('asc');
    $('#type').val('list');
    $('#formSubmit').submit();

  });

  $("#btnDelete").click(function() {
    var data = {};
    var ids = $('tbody input[type=checkbox]:checked').map(function() {
      return $(this).val();
    }).get();
    data['ids'] = ids;
    deleteArtist(data);
  });

  function deleteArtist(data) {
    $
            .ajax({
              url : '${APIurl}',
              type : 'DELETE',
              contentType : 'application/json',
              data : JSON.stringify(data),
              success : function(result) {
                window.location.href = "${ArtistURL}?type=list&maxPageItem=5&page=1&message=delete_success";
              },
              error : function(error) {
                window.location.href = "${ArtistURL}?type=list&maxPageItem=5&page=1&message=error_system";
              }
            });
  }
</script>
</body>

</html>