package com.walletmusic.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class MessageUtil {
	
	public static void showMessage(HttpServletRequest request) {
		if (request.getParameter("message") != null) {
			String messageResponse = "";
			String alert = "";
			String message = request.getParameter("message");
			if (message.equals("insert_success")) {
				messageResponse = "Thêm thành công";
				alert = "success";
			} else if (message.equals("update_success")) {
				messageResponse = "Cập nhật thành công";
				alert = "success";
			} else if (message.equals("delete_success")) {
				messageResponse = "Xoá thành công";
				alert = "success";
			} else if (message.equals("error_system")) {
				messageResponse = "Lỗi hệ thống";
				alert = "danger";
			} else if (message.equals("error_album")) {
				messageResponse = "Lỗi hệ thống(có thể do chưa xoá các bài hát thuộc album đó)";
				alert = "danger";
			}
			request.setAttribute("messageResponse", messageResponse);
			request.setAttribute("alert", alert);
		}
	}

	public static String getMessageLogin(String message) {
		Map<String,String> mapMessage = new HashMap<>();
		mapMessage.put("username_password_invalid","Tên đăng nhập hoặc mật khẩu không đúng");
		mapMessage.put("login_success","Đăng nhập thành công");
		mapMessage.put("not_login","Chưa đăng nhập");
		mapMessage.put("not_permission","Không được phép");
		mapMessage.put("username_exist","Tên đăng nhập đã tồn tại");
		mapMessage.put("register_success","Đăng ký thành công");
		mapMessage.put("create_success","Tạo mới thành công");
		mapMessage.put("create_error","Lỗi hệ thống");
		mapMessage.put("delete_success","Xoá thành công");
		mapMessage.put("delete_error","Lỗi hệ thống");
		return mapMessage.get(message);
	}
}
