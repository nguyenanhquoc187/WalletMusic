<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div style="background-image: linear-gradient(98deg, #C6A7FE, #9155FD 94%)" id="navbar" class="navbar navbar-default          ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Trang quản trị
                </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-right collapse navbar-collapse" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a style="background-color: #be9ef8" data-toggle="dropdown" href="#" class="dropdown-toggle">
                        Xin chào, ${USERMODEL.name}
                    </a>
                    <li class="light-blue dropdown-modal">
                        <a style="background-color: #be9ef8" href='<c:url value="/thoat?action=logout"/>'>
                            <i class="ace-icon fa fa-power-off"></i>
                            Thoát
                        </a>
                    </li>
                </li>
            </ul>
        </div>
    </div>
</div>
